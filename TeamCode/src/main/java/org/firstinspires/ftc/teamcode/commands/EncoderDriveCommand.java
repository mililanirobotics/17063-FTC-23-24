package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.subsystems.MecanumSubsystem;

public class EncoderDriveCommand {
    MecanumSubsystem m_MecanumSubsystem;
    PIDController encoderPID;
    int motorTarget;
    double powerOutput;

    public EncoderDriveCommand (MecanumSubsystem mecanumSubsystem) {
        m_MecanumSubsystem = mecanumSubsystem;
        encoderPID = new PIDController(Constants.EncoderDriveConstants.kEncoderDriveP);
    }

    // Runs the mecanum drive to reach target encoder positions
    public void operate (double distance, String direction) {
        motorTarget = m_MecanumSubsystem.encoderCountsTarget(distance);
        m_MecanumSubsystem.setEncoderTarget(motorTarget, direction);

        while (m_MecanumSubsystem.isBusyCheck() == true) {
            powerOutput = encoderPID.PIDOutput(m_MecanumSubsystem.encoderReading()[0], motorTarget, Constants.EncoderDriveConstants.kEncoderDriveMin, Constants.EncoderDriveConstants.kEncoderDriveMax);
            m_MecanumSubsystem.autoPower(powerOutput, powerOutput);
        }

        m_MecanumSubsystem.shutdown();
    }

    public void shutdown() {
        m_MecanumSubsystem.shutdown();
    }
}
