package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.CascadeLiftSubsystem;

public class CascadeLiftCommand {
    private CascadeLiftSubsystem m_CascadeLiftSubsystem;
    private Gamepad gamepad;

    public CascadeLiftCommand (CascadeLiftSubsystem cascadeLiftSubsystem, Gamepad gamepad){
        m_CascadeLiftSubsystem = cascadeLiftSubsystem;
        this.gamepad = gamepad;
    }

    public void cascadePower () {
        m_CascadeLiftSubsystem.operate(gamepad, Constants.CascadeLiftConstants.kLiftUpPower, Constants.CascadeLiftConstants.kLiftDownPower);
    }

    public void autoCascadeLift (LinearOpMode linearOpMode, double speed, double distance, String direction) {
        m_CascadeLiftSubsystem.autoOperate(linearOpMode, speed, distance, direction);
    }

    public void shutdown() {
        m_CascadeLiftSubsystem.shutdown();
    }
}

// Completely experimental command structure, relatively redundant so might remove.