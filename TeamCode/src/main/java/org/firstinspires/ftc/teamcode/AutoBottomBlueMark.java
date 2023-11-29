package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.AutonomusDriveCommand;
import org.firstinspires.ftc.teamcode.commands.EncoderDriveCommand;
import org.firstinspires.ftc.teamcode.commands.TurnDriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.AprilTagsVisionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumSubsystem;
//import org.firstinspires.ftc.teamcode.subsystems.CascadeLiftSubsystem;
//import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
//import org.firstinspires.ftc.teamcode.subsystems.RollerIntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TensorFlowVisionSubsystem;

@Autonomous (name="AutoBottomBlueMark", group="Linear OpMode")
public class AutoBottomBlueMark extends LinearOpMode {
    private MecanumSubsystem mecanumSubsystem;
//    private RollerIntakeSubsystem rollerIntakeSubsystem;
//    private CascadeLiftSubsystem cascadeLiftSubsystem;
//    private ClawSubsystem clawSubsystem;
    private TensorFlowVisionSubsystem tensorFlowVisionSubsystem;
    private AprilTagsVisionSubsystem aprilTagsVisionSubsystem;

    private AutonomusDriveCommand autonomusDriveCommand;
    private EncoderDriveCommand encoderDriveCommand;
    private TurnDriveCommand turnDriveCommand;

    private float[] teamPropDimensions;
    private int desiredID;

    @Override
    public void runOpMode() {
        mecanumSubsystem = new MecanumSubsystem(this.hardwareMap, telemetry);
//        rollerIntakeSubsystem = new RollerIntakeSubsystem(this.hardwareMap);
//        cascadeLiftSubsystem = new CascadeLiftSubsystem(this.hardwareMap);
//        clawSubsystem = new ClawSubsystem(this.hardwareMap);
        tensorFlowVisionSubsystem = new TensorFlowVisionSubsystem(this, telemetry);
        aprilTagsVisionSubsystem = new AprilTagsVisionSubsystem(this.hardwareMap, telemetry);

        encoderDriveCommand = new EncoderDriveCommand(mecanumSubsystem);
        autonomusDriveCommand = new AutonomusDriveCommand(mecanumSubsystem, aprilTagsVisionSubsystem);
        waitForStart();

        /* Backup code */
//        encoderDriveCommand.operate(1, "Backwards");
//        encoderDriveCommand.operate(26, "Left");

        // Code to run after autonomous starts

        // Detect the TeamProp and get the dimensions of the image and coordinates of the left and right sides of the image
        while (teamPropDimensions == null) {
            if (tensorFlowVisionSubsystem.detectTeamProp(telemetry) != null) {
                teamPropDimensions = tensorFlowVisionSubsystem.teamPropDimensions(telemetry);
            }
        }

        encoderDriveCommand.operate(8, "Backwards");


//        // Decide which Spike Mark to travel towards
//        if (teamPropDimensions[3] > Constants.TeamPropConstants.kTeamPropTargetLeftMin || teamPropDimensions[4] < Constants.TeamPropConstants.kTeamPropTargetLeftMax) {
//            // Left Spike Mark Pathing
//            desiredID = 1;
//
//            // Drive towards Spike Mark & Scoring
//            turnDriveCommand.operate(-90);
//            encoderDriveCommand.operate(6, "Forwards");
//
//            rollerIntakeSubsystem.autoOperate(this, Constants.RollerIntakeConstants.kRollerAutoSpeed, Constants.RollerIntakeConstants.kRollerDistance, "Score");
//
//            encoderDriveCommand.operate(6, "Backwards");
//            turnDriveCommand.operate(90);
//        }
//        else if (teamPropDimensions[0] > Constants.TeamPropConstants.kTeamPropTargetLengthMin || teamPropDimensions[0] < Constants.TeamPropConstants.kTeamPropTargetLengthMax) {
//            // Middle Spike Mark Pathing
//            desiredID = 2;
//
//            // Spike Mark Scoring
//            turnDriveCommand.operate(-180);
//
//            rollerIntakeSubsystem.autoOperate(this, Constants.RollerIntakeConstants.kRollerAutoSpeed, Constants.RollerIntakeConstants.kRollerDistance, "Score");
//
//            turnDriveCommand.operate(180);
//        }
//        else if (teamPropDimensions[4] > Constants.TeamPropConstants.kTeamPropTargetRightMin || teamPropDimensions[4] < Constants.TeamPropConstants.kTeamPropTargetRightMax) {
//            // Right Spike Mark Pathing
//            desiredID = 3;
//
//            // Drive towards Spike Mark & Scoring
//            turnDriveCommand.operate(90);
//            encoderDriveCommand.operate(6, "Forwards");
//
//            rollerIntakeSubsystem.autoOperate(this, Constants.RollerIntakeConstants.kRollerAutoSpeed, Constants.RollerIntakeConstants.kRollerDistance, "Score");
//
//            encoderDriveCommand.operate(6, "Backwards");
//            turnDriveCommand.operate(-90);
//        }
//        else {
//            encoderDriveCommand.operate(96, "Right");
//            stop();
//        }
//
//        // Drive towards Backdrop
//        encoderDriveCommand.operate(26, "Forwards");
//        encoderDriveCommand.operate(72, "Right");
//        encoderDriveCommand.operate(24, "Backwards");
//
//        turnDriveCommand.operate(90);
//
//        // Aligning and moving towards Backdrop AprilTags
//        autonomusDriveCommand.aprilTagAlignment(this, desiredID);
//        autonomusDriveCommand.aprilTagMovement(this, desiredID);
//
//        // Scoring on Backdrop
//        cascadeLiftSubsystem.autoOperate(this, Constants.CascadeLiftConstants.kLiftAutoPower, Constants.CascadeLiftConstants.kAutoHeight, "Up");
//        clawSubsystem.autoOperate("Open");
//        cascadeLiftSubsystem.autoOperate(this, Constants.CascadeLiftConstants.kLiftAutoPower, Constants.CascadeLiftConstants.kAutoHeight, "Down");
//
//        // Move into parking
//        encoderDriveCommand.operate(16, "Left");

        // Shutdown
        mecanumSubsystem.shutdown();
//        cascadeLiftSubsystem.shutdown();
//        clawSubsystem.shutdown();
        tensorFlowVisionSubsystem.shutdown();
    }
}
