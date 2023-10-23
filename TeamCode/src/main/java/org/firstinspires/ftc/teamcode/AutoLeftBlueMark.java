package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.AutonomusDriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.MecanumSubsystem;

@Autonomous (name="AutoLeftBlueMark", group="Linear OpMode")
public class AutoLeftBlueMark extends LinearOpMode {
    private MecanumSubsystem mecanumSubsystem;

    private AutonomusDriveCommand autonomusDriveCommand;

    @Override
    public void runOpMode() {
        mecanumSubsystem = new MecanumSubsystem(this.hardwareMap, telemetry);


        waitForStart();

        mecanumSubsystem.autoDrivePower(0.2, "Forwards");
    }
}