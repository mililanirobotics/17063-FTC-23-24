package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;

public class CascadeLift {
    private DcMotor leftLiftMotor;
    private DcMotor rightLiftMotor;

    public CascadeLift(OpMode opMode) {
        leftLiftMotor = opMode.hardwareMap.get(DcMotor.class, "leftLiftMotor");
        rightLiftMotor = opMode.hardwareMap.get(DcMotor.class, "rightLiftMotor");
    }
}
