package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.subsystems.MecanumSubsystem;

public class TurnDriveCommand {
    private MecanumSubsystem m_MecanumSubsystem;
    private PIDController turnDrivePID;

    public TurnDriveCommand (MecanumSubsystem mecanumSubsystem) {
        m_MecanumSubsystem = mecanumSubsystem;
        turnDrivePID = new PIDController(Constants.EncoderDriveConstants.kEncoderDriveP);
    }

    public void operate (int angle) {

        double currentAngle = m_MecanumSubsystem.getAngle();
        int targetAngle = (int)(angle + currentAngle);
        double powerOutput;

        while (currentAngle < targetAngle - 1 || currentAngle > targetAngle + 1) {
            if (currentAngle < targetAngle - 1) {
                powerOutput = turnDrivePID.PIDOutput(currentAngle, targetAngle, Constants.EncoderDriveConstants.kEncoderDriveMin, Constants.EncoderDriveConstants.kEncoderDriveMax);
                m_MecanumSubsystem.autoPower(powerOutput, -powerOutput);
            }
            else if (currentAngle > targetAngle + 1) {
                powerOutput = turnDrivePID.PIDOutput(currentAngle, targetAngle, Constants.EncoderDriveConstants.kEncoderDriveMin, Constants.EncoderDriveConstants.kEncoderDriveMax);
                m_MecanumSubsystem.autoPower(-powerOutput, powerOutput);
            }
        }
        m_MecanumSubsystem.shutdown();
    }

    public void shutdown() {
        m_MecanumSubsystem.shutdown();
    }
}

