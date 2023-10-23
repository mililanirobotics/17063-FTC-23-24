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

    @Override
    public void runOpMode() {
        aprilTagsVisionSubsystem = new AprilTagsVisionSubsystem(this.hardwareMap, telemetry);

        detection = aprilTagsVisionSubsystem.getAprilTag(4);


        telemetry.addData("ID: ", detection.id);
        telemetry.addData("Y-Pos", detection.ftcPose.y);

        waitForStart();
    }
}
