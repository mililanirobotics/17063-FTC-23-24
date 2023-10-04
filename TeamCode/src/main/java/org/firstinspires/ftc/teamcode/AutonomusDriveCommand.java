package org.firstinspires.ftc.teamcode;


public class AutonomusDriveCommand {
    private MecanumSubsystem mecanumSubsystem;
    private TensorFlowVisionSubsystem vision;
    private AprilTagsVision aprilTagsVision;

    public AutonomusDriveCommand(MecanumSubsystem mecanumSubsystem, TensorFlowVisionSubsystem vision, AprilTagsVision aprilTagsVision) {
        this.mecanumSubsystem = mecanumSubsystem;
        this.vision = vision;
        this.aprilTagsVision = aprilTagsVision;
    }

    // Input targetDistance as the maximum distance of the camera from the apriltag
    public void aprilTagMovement (double targetDistance, double powerAmount) {
        double[] aprilTagsPositions = aprilTagsVision.getAprilTagDistance();
        if (aprilTagsPositions[1] > targetDistance) {
            mecanumSubsystem.autoDrivePower(powerAmount);
        }
        else {
            mecanumSubsystem.autoDrivePower(0);
        }
    }

    public void TeamPropMovement () {
        
    }
}
