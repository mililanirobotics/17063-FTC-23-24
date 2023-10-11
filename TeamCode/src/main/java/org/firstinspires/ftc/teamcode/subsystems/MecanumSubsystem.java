package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class MecanumSubsystem {
    private DcMotorEx leftFrontDrive;
    private DcMotorEx rightFrontDrive;
    private DcMotorEx leftBackDrive;
    private DcMotorEx rightBackDrive;

    private BNO055IMU imu;
    private final BNO055IMU.Parameters parameters;

    private boolean slowModeOn;

    public MecanumSubsystem(HardwareMap hwMap, Telemetry telemetry) {
        //Initializing Motors
        leftFrontDrive = hwMap.get(DcMotorEx.class, "leftFrontDrive");
        rightFrontDrive = hwMap.get(DcMotorEx.class, "rightFrontDrive");
        leftBackDrive = hwMap.get(DcMotorEx.class, "leftBackDrive");
        rightBackDrive = hwMap.get(DcMotorEx.class, "rightBackDrive");

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFrontDrive.setDirection(Constants.MecanumConstants.kLeftFrontDirection);
        leftBackDrive.setDirection(Constants.MecanumConstants.kLeftBackDirection);
        rightFrontDrive.setDirection(Constants.MecanumConstants.kRightFrontDirection);
        rightBackDrive.setDirection(Constants.MecanumConstants.kRightBackDirection);

        imu = hwMap.get(BNO055IMU.class, "imu");
        parameters = new BNO055IMU.Parameters();
        imu.initialize(parameters);

        telemetry.addData("Status", "Mecanum Drive Initializes");
        telemetry.update();
    }

    public void operate(Gamepad gamepad) {
        double y = -gamepad.left_stick_y;
        double x = gamepad.left_stick_x;
        double rx = gamepad.right_stick_x;
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        if (gamepad.left_bumper && !slowModeOn) {
            slowModeOn = true;
        }
        else if (gamepad.left_bumper && slowModeOn){
            slowModeOn = false;
        }

        if (slowModeOn) {
            leftFrontDrive.setPower((y + x + rx) / denominator * 0.5);
            rightFrontDrive.setPower((y - x - rx) / denominator * 0.5);
            leftBackDrive.setPower((y - x + rx) / denominator * 0.5);
            rightBackDrive.setPower((y + x - rx) / denominator * 0.5);
        }
        else {
            leftFrontDrive.setPower((y + x + rx) / denominator);
            rightFrontDrive.setPower((y - x - rx) / denominator);
            leftBackDrive.setPower((y - x + rx) / denominator);
            rightBackDrive.setPower((y + x - rx) / denominator);
        }
    }

    public void autoDrivePower (double power, String direction) {

        if (direction.equals("Forwards")) {
            leftFrontDrive.setPower(power);
            rightFrontDrive.setPower(power);
            leftBackDrive.setPower(power);
            rightBackDrive.setPower(power);
        }
        else if (direction.equals("Backwards")) {
            leftFrontDrive.setPower(-power);
            rightFrontDrive.setPower(-power);
            leftBackDrive.setPower(-power);
            rightBackDrive.setPower(-power);
        }
        else if (direction.equals("Left")) {
            leftFrontDrive.setPower(-power);
            rightFrontDrive.setPower(power);
            leftBackDrive.setPower(power);
            rightBackDrive.setPower(-power);
        }
        else if (direction.equals("Right")) {
            leftFrontDrive.setPower(power);
            rightFrontDrive.setPower(-power);
            leftBackDrive.setPower(-power);
            rightBackDrive.setPower(power);
        }
    }

    public void shutdown() {
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightBackDrive.setPower(0);
    }
}