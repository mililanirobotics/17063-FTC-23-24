package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.AprilTagsVisionSubsystem;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;


@Autonomous(name="Camera Testing", group="Linear OpMode")

public class TestingAutoCamera extends LinearOpMode {
    AprilTagsVisionSubsystem aprilTagsVisionSubsystem;
    AprilTagDetection detection;
    String ID;
    final ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        aprilTagsVisionSubsystem = new AprilTagsVisionSubsystem(this.hardwareMap, telemetry);

        waitForStart();



        // Testing code to get the correct aprilTag ID
//        detection = aprilTagsVisionSubsystem.getAprilTag(1);
//        telemetry.addData("ID ", detection.id);
//        telemetry.update();

        // aprilTagsVisionSubsystem.findAprilTag(this, telemetry, 1, runtime, 5);
        aprilTagsVisionSubsystem.getAprilTagDistance(this, 1, runtime, 5, telemetry);
    }
}
