package org.firstinspires.ftc.teamcode.commands;



import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.subsystems.AprilTagsVisionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TensorFlowVisionSubsystem;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AutonomusDriveCommand {
    private MecanumSubsystem m_MecanumSubsystem;
    private TensorFlowVisionSubsystem tensorFlowVision;
    private AprilTagsVisionSubsystem aprilTagsVision;
    private PIDController autoPID;
    private int desiredID;
    private final ElapsedTime runtime = new ElapsedTime();


    public AutonomusDriveCommand(MecanumSubsystem mecanumSubsystem, TensorFlowVisionSubsystem tensorFlowVisionvision, AprilTagsVisionSubsystem aprilTagsVision) {
        m_MecanumSubsystem = mecanumSubsystem;
        this.tensorFlowVision = tensorFlowVisionvision;
        this.aprilTagsVision = aprilTagsVision;
        autoPID = new PIDController(Constants.AprilTagsConstants.kAprilTagP);
    }

    // Alignment for camera to be in front
    public void aprilTagAlignment(LinearOpMode linearOpMode, int ID) {

        double[] aprilTagPositions = aprilTagsVision.getAprilTagDistance(linearOpMode, ID, runtime, 5, telemetry);
        double powerOutput = autoPID.PIDOutput(aprilTagPositions[0], 0, Constants.AprilTagsConstants.kAprilTagMin, Constants.AprilTagsConstants.kAprilTagMax);

        if (aprilTagPositions[0] > 1) {
            m_MecanumSubsystem.autoDrivePower(powerOutput, "Left");
        }
        else if (aprilTagPositions[0] < 1) {
            m_MecanumSubsystem.autoDrivePower(powerOutput, "right");
        }
        else {
            m_MecanumSubsystem.shutdown();
        }
    }

    // Input targetDistance as the maximum distance of the camera from the apriltag
    public void aprilTagMovement (LinearOpMode linearOpMode, int ID) {
        double[] aprilTagsPositions = aprilTagsVision.getAprilTagDistance(linearOpMode, ID, runtime, 5, telemetry);
        double powerOutput = autoPID.PIDOutput(aprilTagsPositions[1], Constants.AprilTagsConstants.kAprilTagTargetDistance, Constants.AprilTagsConstants.kAprilTagMin, Constants.AprilTagsConstants.kAprilTagMax);


        if (aprilTagsPositions[1] > Constants.AprilTagsConstants.kAprilTagTargetDistance) {
            m_MecanumSubsystem.autoDrivePower(powerOutput, "Forwards");
        }
        else {
            m_MecanumSubsystem.shutdown();
        }
    }

    public void TeamPropMovement (double targetLength, double targetWidth) {
        double[] teamPropDimensions = tensorFlowVision.teamPropDimensions();
        double powerOutput = autoPID.PIDOutput(teamPropDimensions[0], targetLength, Constants.TeamPropConstants.kTeamPropMin, Constants.TeamPropConstants.kTeamPropMax);

        if (teamPropDimensions[0] < targetLength && teamPropDimensions[1] < targetWidth) {
            m_MecanumSubsystem.autoDrivePower(powerOutput, "Forwards");
        }
        else {
            m_MecanumSubsystem.shutdown();
        }
    }
}
