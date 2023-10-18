package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

public class AprilTagsVisionSubsystem {
    private AprilTagProcessor aprilTagsProcessor;
    private VisionPortal visionPortal;
    List<AprilTagDetection> currentDetections;
    private WebcamName camera;

    public AprilTagsVisionSubsystem(HardwareMap hwMap, Telemetry telemetry) {
        camera = hwMap.get(WebcamName.class, "camera");
        aprilTagsProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagOutline(true)
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                .setTagLibrary(Constants.AprilTagsConstants.kAprilTagsItems.getCenterStageTagLibrary())
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)
                .build();
        visionPortal = new VisionPortal.Builder()
                .build();

        visionPortal.setActiveCamera(camera);
    }

    public AprilTagDetection getAprilTag (int ID) {
        int desiredID = ID;
        for (AprilTagDetection detection : currentDetections) {
            if (detection.id == desiredID) {
                return detection;
            }
        }
        return null;
    }

    public void updateDetections () {
        currentDetections = aprilTagsProcessor.getDetections();
    }

    public double[] getAprilTagDistance(int ID) {
        int desiredID = ID;

        double[] position = new double[3];
        AprilTagDetection detection = getAprilTag(desiredID);
            if (detection.metadata != null) {
                position[0] = detection.ftcPose.x;
                position[1] = detection.ftcPose.y; // Finds the distance from the camera outwards to the april tag
                position[2] = detection.ftcPose.z;
            }
        return position;
    }

    // double[] god = getAprilTagDistance();
    // god[0]

    private void telemetryAprilTag(Telemetry telemetry) {

        List<AprilTagDetection> currentDetections = aprilTagsProcessor.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
            } else {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }   // end for() loop

        // Add "key" information to telemetry
        telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
    }   // end method telemetryAprilTag()

}
