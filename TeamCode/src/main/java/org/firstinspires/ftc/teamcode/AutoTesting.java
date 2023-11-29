package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.commands.AutonomusDriveCommand;
import org.firstinspires.ftc.teamcode.commands.EncoderDriveCommand;
import org.firstinspires.ftc.teamcode.commands.TurnDriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.AprilTagsVisionSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumSubsystem;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;


@Autonomous(name="AutoTesting", group="Linear OpMode")

public class AutoTesting extends LinearOpMode {
    MecanumSubsystem mecanumSubsystem;
    AprilTagsVisionSubsystem aprilTagsVisionSubsystem;
    EncoderDriveCommand encoderDriveCommand;
    AutonomusDriveCommand autonomusDriveCommand;
    TurnDriveCommand turnDriveCommand;

    String ID;
    final ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        aprilTagsVisionSubsystem = new AprilTagsVisionSubsystem(this.hardwareMap, telemetry);
        mecanumSubsystem = new MecanumSubsystem(this.hardwareMap, telemetry);

        encoderDriveCommand = new EncoderDriveCommand(mecanumSubsystem);
        autonomusDriveCommand = new AutonomusDriveCommand(mecanumSubsystem, aprilTagsVisionSubsystem);
        turnDriveCommand = new TurnDriveCommand(mecanumSubsystem);

        waitForStart();

        // Testing encoder drive
        encoderDriveCommand.operate(15, "Forwards");

        // Testing turn drive
        turnDriveCommand.operate(-90);
        turnDriveCommand.operate(90);

//         Testing code to get the correct aprilTag ID
//        detection = aprilTagsVisionSubsystem.getAprilTag(1);
//        telemetry.addData("ID ", detection.id);
//        telemetry.update();
//
//         aprilTagsVisionSubsystem.findAprilTag(this, telemetry, 1, runtime, 5);
//        aprilTagsVisionSubsystem.getAprilTagDistance(this, 1, runtime, 5, telemetry);
//        autonomusDriveCommand.aprilTagMovement(this, 2);
    }
}
