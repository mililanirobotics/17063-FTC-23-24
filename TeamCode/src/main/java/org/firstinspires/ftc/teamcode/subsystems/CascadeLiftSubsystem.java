package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.teamcode.Constants;

public class CascadeLiftSubsystem {
    private DcMotor leftLiftMotor;
    private DcMotor rightLiftMotor;

    public CascadeLiftSubsystem(HardwareMap hwMap) {
        leftLiftMotor = hwMap.get(DcMotor.class, "leftLiftMotor");
        rightLiftMotor = hwMap.get(DcMotor.class, "rightLiftMotor");

        leftLiftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightLiftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void operate (Gamepad gamepad, double liftUpPower, double liftDownPower) {

        if (gamepad.left_bumper) {
            leftLiftMotor.setPower(liftUpPower);
            rightLiftMotor.setPower(liftUpPower);
        }
        else if (gamepad.right_bumper) {
            leftLiftMotor.setPower(-liftDownPower);
            rightLiftMotor.setPower(-liftDownPower);
        }
        else {
            leftLiftMotor.setPower(0);
            rightLiftMotor.setPower(0);
        }
    }

    public void autoOperate(LinearOpMode linearOpMode, double speed, double distance, String direction, Telemetry telemetry){
        int motorTarget = (int)(distance * Constants.CascadeLiftConstants.kCOUNTS_PER_INCH);

        leftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if (direction.equals("up")){
            leftLiftMotor.setTargetPosition(motorTarget);
            rightLiftMotor.setTargetPosition(motorTarget);
        }
        else {
            leftLiftMotor.setTargetPosition(-motorTarget);
            rightLiftMotor.setTargetPosition(-motorTarget);
        }

        leftLiftMotor.setPower(speed);
        rightLiftMotor.setPower(speed);
        leftLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightLiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (linearOpMode.opModeIsActive() && leftLiftMotor.isBusy() && rightLiftMotor.isBusy()){

        }

        leftLiftMotor.setPower(0);
        rightLiftMotor.setPower(0);

        leftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    public void shutdown () {
        leftLiftMotor.setPower(0);
        rightLiftMotor.setPower(0);
    }
}
