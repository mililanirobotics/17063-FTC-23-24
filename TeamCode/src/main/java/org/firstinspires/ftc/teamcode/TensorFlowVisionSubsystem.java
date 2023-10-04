package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
                .build();
    }

    public String operate(LinearOpMode linearOpMode, Telemetry telemetry) {
        String teamPropImage = "No Image";

        while (linearOpMode.opModeIsActive() && teamPropImage == "No Image") {
            if (tfodProcessor.getRecognitions() == null) {
                List<Recognition> updatedRecognitions = tfodProcessor.getRecognitions();
                if (updatedRecognitions != null) {
                    for (Recognition recognition : updatedRecognitions) {
                        teamPropImage = recognition.getLabel();

                        telemetry.addData("Image Recognized: ", teamPropImage);
                        telemetry.update();
                    }
                }
            }
        }
        return teamPropImage;
    }

    public double[] teamPropDimensions() {
        double[] teamPropDimensions = new double[2];
        List<Recognition> currentRecognitions = tfodProcessor.getRecognitions();
        for (Recognition recognition : currentRecognitions) {
            teamPropDimensions[0] = (recognition.getLeft() + recognition.getRight()) / 2;
            teamPropDimensions[1] = (recognition.getTop() + recognition.getBottom()) / 2;
        }

        return teamPropDimensions;
    }

    public void shutdown() {

    }
}