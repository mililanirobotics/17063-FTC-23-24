package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.AprilTagsVisionSubsystem;


@Autonomous(name="Camera Testing", group="Linear OpMode")

public class TestingAutoCamera extends LinearOpMode {
    AprilTagsVisionSubsystem aprilTagsVisionSubsystem;

    @Override
    public void runOpMode() {

        aprilTagsVisionSubsystem = new AprilTagsVisionSubsystem(this.hardwareMap, telemetry);
    }
}
