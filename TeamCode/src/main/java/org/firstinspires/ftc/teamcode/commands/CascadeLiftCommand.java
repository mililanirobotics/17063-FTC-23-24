package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.CascadeLiftSubsystem;

public class CascadeLiftCommand {
    private CascadeLiftSubsystem m_cascadeLiftSubsystem;
    private Gamepad gamepad;

    public CascadeLiftCommand (CascadeLiftSubsystem cascadeLiftSubsystem, Gamepad gamepad){
        m_cascadeLiftSubsystem = cascadeLiftSubsystem;
        this.gamepad = gamepad;
    }

    public void cascadePower () {
        m_cascadeLiftSubsystem.operate(gamepad, Constants.CascadeLiftConstants.kLiftUpPower, Constants.CascadeLiftConstants.kLiftDownPower);
    }

    public void shutdown() {
        m_cascadeLiftSubsystem.shutdown();
    }
}

// Completely experimental command structure, relatively redundant so might remove.