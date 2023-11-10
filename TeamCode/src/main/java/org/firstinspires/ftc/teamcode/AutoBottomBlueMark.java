package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.AutonomusDriveCommand;
import org.firstinspires.ftc.teamcode.commands.EncoderDriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.AprilTagsVisionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.CascadeLiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.RollerIntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TensorFlowVisionSubsystem;

@Autonomous (name="AutoBottomBlueMark", group="Linear OpMode")
public class AutoBottomBlueMark extends LinearOpMode {
    private MecanumSubsystem mecanumSubsystem;
    private RollerIntakeSubsystem rollerIntakeSubsystem;
    private CascadeLiftSubsystem cascadeLiftSubsystem;
    private ClawSubsystem clawSubsystem;
    private TensorFlowVisionSubsystem tensorFlowVisionSubsystem;
    private AprilTagsVisionSubsystem aprilTagsVisionSubsystem;

    private AutonomusDriveCommand autonomusDriveCommand;
    private EncoderDriveCommand encoderDriveCommand;

    private float[] teamPropDimensions;
    private int desiredID;

    @Override
    public void runOpMode() {
        mecanumSubsystem = new MecanumSubsystem(this.hardwareMap, telemetry);
        rollerIntakeSubsystem = new RollerIntakeSubsystem(this.hardwareMap);
        cascadeLiftSubsystem = new CascadeLiftSubsystem(this.hardwareMap);
        clawSubsystem = new ClawSubsystem(this.hardwareMap);
        tensorFlowVisionSubsystem = new TensorFlowVisionSubsystem(this, telemetry);
        aprilTagsVisionSubsystem = new AprilTagsVisionSubsystem(this.hardwareMap, telemetry);

        encoderDriveCommand = new EncoderDriveCommand(mecanumSubsystem);
        autonomusDriveCommand = new AutonomusDriveCommand(mecanumSubsystem, aprilTagsVisionSubsystem);
        waitForStart();
        // Code to run after autonomous starts

        encoderDriveCommand.encoderDriveOperate(28, "Backwards");

        // Detect the TeamProp and get the dimensions of the image and coordinates of the left and right sides of the image
        while (teamPropDimensions == null) {
            if (tensorFlowVisionSubsystem.detectTeamProp(telemetry) != null) {
                teamPropDimensions = tensorFlowVisionSubsystem.teamPropDimensions(telemetry);
            }
        }

        // Decide which Spike Mark to travel towards
        if (teamPropDimensions[3] > Constants.TeamPropConstants.kTeamPropTargetLeftMin || teamPropDimensions[4] < Constants.TeamPropConstants.kTeamPropTargetLeftMax) {
            // Left Spike Mark Pathing
            desiredID = 1;

            // Drive towards Spike Mark & Scoring
            encoderDriveCommand.turnDriveOperate(90);
            encoderDriveCommand.encoderDriveOperate(6, "Forwards");

            rollerIntakeSubsystem.autoOperate(this, Constants.RollerIntakeConstants.kRollerAutoSpeed, Constants.RollerIntakeConstants.kRollerDistance, "Score");

            encoderDriveCommand.encoderDriveOperate(6, "Backwards");
            encoderDriveCommand.turnDriveOperate(-90);
        }
        else if (teamPropDimensions[0] > Constants.TeamPropConstants.kTeamPropTargetLengthMin || teamPropDimensions[0] < Constants.TeamPropConstants.kTeamPropTargetLengthMax) {
            // Middle Spike Mark Pathing
            desiredID = 2;

            // Spike Mark Scoring
            encoderDriveCommand.turnDriveOperate(-180);

            rollerIntakeSubsystem.autoOperate(this, Constants.RollerIntakeConstants.kRollerAutoSpeed, Constants.RollerIntakeConstants.kRollerDistance, "Score");

            encoderDriveCommand.turnDriveOperate(180);
        }
        else if (teamPropDimensions[4] > Constants.TeamPropConstants.kTeamPropTargetRightMin || teamPropDimensions[4] < Constants.TeamPropConstants.kTeamPropTargetRightMax) {
            // Right Spike Mark Pathing
            desiredID = 3;

            // Drive towards Spike Mark & Scoring
            encoderDriveCommand.turnDriveOperate(-90);
            encoderDriveCommand.encoderDriveOperate(6, "Forwards");

            rollerIntakeSubsystem.autoOperate(this, Constants.RollerIntakeConstants.kRollerAutoSpeed, Constants.RollerIntakeConstants.kRollerDistance, "Score");

            encoderDriveCommand.encoderDriveOperate(6, "Backwards");
            encoderDriveCommand.turnDriveOperate(90);
        }

        // Drive towards Backdrop
        encoderDriveCommand.encoderDriveOperate(26, "Forwards");
        encoderDriveCommand.encoderDriveOperate(72, "Right");
        encoderDriveCommand.encoderDriveOperate(24, "Backwards");

        encoderDriveCommand.turnDriveOperate(-90);

        // Aligning and moving towards Backdrop AprilTags
        autonomusDriveCommand.aprilTagAlignment(this, desiredID);
        autonomusDriveCommand.aprilTagMovement(this, desiredID);

        // Scoring on Backdrop
        cascadeLiftSubsystem.autoOperate(this, Constants.CascadeLiftConstants.kLiftAutoPower, Constants.CascadeLiftConstants.kAutoHeight, "Up");
        clawSubsystem.autoOperate("Open");
        cascadeLiftSubsystem.autoOperate(this, Constants.CascadeLiftConstants.kLiftAutoPower, Constants.CascadeLiftConstants.kAutoHeight, "Down");

        // Move into parking
        encoderDriveCommand.encoderDriveOperate(16, "Left");

        // Shutdown
        mecanumSubsystem.shutdown();
        cascadeLiftSubsystem.shutdown();
        clawSubsystem.shutdown();
        tensorFlowVisionSubsystem.shutdown();
    }
}
