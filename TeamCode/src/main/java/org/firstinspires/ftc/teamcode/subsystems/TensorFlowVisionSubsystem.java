package org.firstinspires.ftc.teamcode.subsystems;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.List;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;


public class TensorFlowVisionSubsystem {

    private WebcamName camera;
    private TfodProcessor tfodProcessor;
    private VisionPortal visionPortal;
    private static final String TFOD_MODEL_FILE = "/sdcard/FIRST/tflitemodels/"; //Change into the actual tensorflow module file
    private static final String[] LABELS = {
            "TeamProp"
    };


    public TensorFlowVisionSubsystem(LinearOpMode linearOpMode, Telemetry telemetry) {
        camera = linearOpMode.hardwareMap.get(WebcamName.class, "camera");

        tfodProcessor = new TfodProcessor.Builder() // Chain formatting for Tensorflow builder
                .setMaxNumRecognitions(10) // Max. number of recognitions the network will return
                .setModelInputSize(300) // Sets the size of pixels the camera will read the object in
                .setUseObjectTracker(true) // Whether to use the object tracker
                .setTrackerMaxOverlap((float) 0.2) // Max. % of box overlapped by another box at recognition time
                .setTrackerMinSize(16) // Min. size of object that the object tracker will track
                .setIsModelTensorFlow2(true)
                .build();// Create a TFOD Processor by calling build()

        visionPortal = new VisionPortal.Builder()
                .setCamera(camera)
                .setCameraResolution(new Size(640, 480))
                .enableLiveView(true)
                .addProcessor(tfodProcessor)
                .build();
    }

    public Recognition detectTeamProp (Telemetry telemetry) {
        Recognition teamProp;
        List<Recognition> updatedRecognitions = tfodProcessor.getRecognitions();

        for (Recognition recognition : updatedRecognitions) {
            if (recognition.getLabel() == LABELS[0]) {
                teamProp = recognition;

                telemetry.addData("Image Recognized: ", teamProp.getLabel());
                telemetry.update();
                return teamProp;
            }
        }
        return null;
    }

    public float[] teamPropDimensions(Telemetry telemetry) {
        float[] teamPropDimensions = new float[4];


        while (detectTeamProp(telemetry).getLabel() != LABELS[0]) {
            if (detectTeamProp(telemetry).getLabel() == LABELS[0]) {
                Recognition teamProp = detectTeamProp(telemetry);

                teamPropDimensions[0] = (teamProp.getLeft() + teamProp.getRight()) / 2; // Gets Length Dimensions
                teamPropDimensions[1] = (teamProp.getTop() + teamProp.getBottom()) / 2; // Gets Width Dimensions
                teamPropDimensions[2] = teamProp.getLeft(); // Gets the coordinate of the left side of the Image Dimensions
                teamPropDimensions[3] = teamProp.getRight(); // Gets the coordinate of the right side of the Image Dimensions
            }
        }
        return teamPropDimensions;
    }

    public void shutdown() {
        camera.close();
    }
}