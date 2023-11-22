package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class RollerIntakeSubsystem {
    DcMotorEx rollerIntakeMotor;

    public RollerIntakeSubsystem (HardwareMap hwMap) {
        rollerIntakeMotor = hwMap.get(DcMotorEx.class, "rollerIntakeMotor");

        rollerIntakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void operate(Gamepad gamepad) {
        if (gamepad.left_trigger > Constants.RollerIntakeConstants.kDeadband) {
            rollerIntakeMotor.setPower(gamepad.left_trigger * 0.5);
        }
        else if (gamepad.right_trigger > Constants.RollerIntakeConstants.kDeadband) {
            rollerIntakeMotor.setPower(gamepad.right_trigger * 0.5);
        }
        else {
            rollerIntakeMotor.setPower(0);
        }
    }

    public void autoOperate(LinearOpMode linearOpMode, double speed, double distance, String direction) {
        int motorTarget = (int)(distance * Constants.CascadeLiftConstants.kCOUNTS_PER_INCH);

        rollerIntakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if (direction.equals("Intake")){
            rollerIntakeMotor.setTargetPosition(motorTarget);
        }
        else if (direction.equals("Score")){
            rollerIntakeMotor.setTargetPosition(-motorTarget);
        }

        rollerIntakeMotor.setPower(speed);
        rollerIntakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (linearOpMode.opModeIsActive() && rollerIntakeMotor.isBusy()){

        }

        rollerIntakeMotor.setPower(0);

        rollerIntakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void shutdown() {
        rollerIntakeMotor.setPower(0);
    }
}
