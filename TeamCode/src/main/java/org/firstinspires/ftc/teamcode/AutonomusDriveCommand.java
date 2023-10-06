package org.firstinspires.ftc.teamcode;


import org.firstinspires.ftc.robotcore.external.Const;

public class AutonomusDriveCommand {
    private MecanumSubsystem mecanumSubsystem;
    private TensorFlowVisionSubsystem vision;
    private AprilTagsVision aprilTagsVision;
    private PIDController autoPID;

    public AutonomusDriveCommand(MecanumSubsystem mecanumSubsystem, TensorFlowVisionSubsystem vision, AprilTagsVision aprilTagsVision) {
        this.mecanumSubsystem = mecanumSubsystem;
        this.vision = vision;
        this.aprilTagsVision = aprilTagsVision;
        autoPID = new PIDController(Constants.AprilTagsConstants.kAprilTagP);
    }

    // Alignment for camera to be in front
    public void aprilTagAlignment() {
        double[] aprilTagPositions = aprilTagsVision.getAprilTagDistance();
        double power = autoPID.PIDOutput(aprilTagPositions[0], 0, Constants.AprilTagsConstants.kAprilTagMin, Constants.AprilTagsConstants.kAprilTagMax);

        if (aprilTagPositions[0] > 1) {
            mecanumSubsystem.autoDrivePower(power, "Left");
        }
    }

    // Input targetDistance as the maximum distance of the camera from the apriltag
    public void aprilTagMovement (double targetDistance, double powerAmount) {
        double[] aprilTagsPositions = aprilTagsVision.getAprilTagDistance();

        if (aprilTagsPositions[1] > targetDistance) {
            mecanumSubsystem.autoDrivePower(powerAmount, "Forwards");
        }
        else {
            mecanumSubsystem.shutdown();
        }
    }



    public void TeamPropMovement () {

    }
}
