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

    public EncoderDriveCommand (MecanumSubsystem mecanumSubsystem) {
        m_MecanumSubsystem = mecanumSubsystem;
        encoderPID = new PIDController(Constants.EncoderDriveConstants.kEncoderDriveP);
    }

    public void encoderDriveOperate (double distance, String direction) {
        int motorTarget = (int)(distance * Constants.EncoderDriveConstants.kCOUNTS_PER_INCH);

        double powerOutput = encoderPID.PIDOutput(m_MecanumSubsystem.encoderReading(), motorTarget, Constants.EncoderDriveConstants.kEncoderDriveMin, Constants.EncoderDriveConstants.kEncoderDriveMax);

        m_MecanumSubsystem.autoDrivePower(powerOutput, direction);
    }

    public void shutdown() {
        m_MecanumSubsystem.shutdown();
    }
}
