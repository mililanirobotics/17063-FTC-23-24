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
import com.qualcomm.robotcore.util.ElapsedTime;

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
                .setDrawTagID(true)
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                .setTagLibrary(Constants.AprilTagsConstants.kAprilTagsItems.getCenterStageTagLibrary())
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)
                .build();
        visionPortal = new VisionPortal.Builder()
                .setCamera(camera)
                .addProcessor(aprilTagsProcessor)
                .setStreamFormat(VisionPortal.StreamFormat.YUY2)
                .build();

    }

    // Method should return the detection of desired AprilTag ID.
    public AprilTagDetection getAprilTag (int ID) {
        List<AprilTagDetection> detections = updateDetections();
        AprilTagDetection aprilTag;

        for (AprilTagDetection detection : detections) {
            if (detection.id == ID) {
                aprilTag = detection;
                return aprilTag;
            }
        }
        return null;
    }

    // Testing method to get the april tag ID, may not be used for actual competition code.
    public String findAprilTag(LinearOpMode linearOpMode, Telemetry telemetry, int ID, ElapsedTime elapsedTime, int timeoutSec) {
        String AprilTagID = "No image";
        AprilTagDetection aprilTag;

        while (linearOpMode.opModeIsActive() && AprilTagID.equals("No image") && elapsedTime.seconds() < timeoutSec) {
            if (getAprilTag(ID) != null) {
                if (getAprilTag(ID).id == ID) {
                    aprilTag = getAprilTag(ID);
                    AprilTagID = Integer.toString(aprilTag.id);
                    telemetry.addData("ID ", AprilTagID);
                    telemetry.update();
                }
                else {
                    AprilTagID = "No ID Found";
                    telemetry.addData("ID ", AprilTagID);
                    telemetry.update();
                }
            }
        }
        return AprilTagID;
    }

    public List<AprilTagDetection> updateDetections () {
        currentDetections = aprilTagsProcessor.getDetections();
        return currentDetections;
    }

    // Active method for
    public double[] getAprilTagDistance(LinearOpMode linearOpMode, int ID, ElapsedTime elapsedTime, int timeoutSec, Telemetry telemetry) {
        AprilTagDetection detection;
        double[] position = new double[3];
        position[0] = 0;

        while (linearOpMode.opModeIsActive() && position[0] == 0 && elapsedTime.seconds() < timeoutSec) {
            if (getAprilTag(ID) != null) {
                detection = getAprilTag(ID);
                position[0] = detection.ftcPose.x;
                position[1] = detection.ftcPose.y; // Finds the distance from the camera outwards to the april tag
                position[2] = detection.ftcPose.z;

//                telemetry.addData("Distance ", position[1]);
//                telemetry.addData("Horizontal Offset ", position[0]);
//                telemetry.update();
            }
        }
        return position;
    }

    // Due to lack of accessibility for the detection of April Tags through other methods, this method may be deprecated and integrated elsewhere in the getAprilTagDistance method
    public void telemetryAprilTag(int ID, Telemetry telemetry) {
        telemetry.addData("# AprilTags Detected", currentDetections.size());
        AprilTagDetection detection = getAprilTag(ID);

        if (detection != null) {
            telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id));
            telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
        } else {
            telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
            telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
        }
    // Add "key" information to telemetry
        telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
        telemetry.update();
    }

}
