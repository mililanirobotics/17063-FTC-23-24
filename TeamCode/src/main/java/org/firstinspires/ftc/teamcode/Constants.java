package org.firstinspires.ftc.teamcode;

import android.graphics.Canvas;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.opencv.core.Mat;

import java.util.ArrayList;


public class Constants {
    public static class MecanumConstants {
        public static final DcMotorSimple.Direction kLeftFrontDirection = DcMotorSimple.Direction.REVERSE;
        public static final DcMotorSimple.Direction kLeftBackDirection = DcMotorSimple.Direction.REVERSE;
        public static final DcMotorSimple.Direction kRightFrontDirection = DcMotorSimple.Direction.FORWARD;
        public static final DcMotorSimple.Direction kRightBackDirection = DcMotorSimple.Direction.FORWARD;
        public static final double kAutoPower = 0.25;
    }

    public static class EncoderDriveConstants {
//        public static final double kPULSES_PER_ROTATION = 28;
//        public static final double kCOUNTS_PER_ROTATION = kPULSES_PER_ROTATION * 4;
        public static final double kCOUNTS_PER_ROTATION = 537.7;
        public static final double kDRIVE_GEAR_REDUCTION = 1.5625;
        public static final double kWHEEL_DIAMETER = 3.77953;
        public static final double kWHEEL_CIRCUMFERENCE = Math.PI * kWHEEL_DIAMETER;
        public static final double kCOUNTS_PER_INCH = (kCOUNTS_PER_ROTATION * kDRIVE_GEAR_REDUCTION) / kWHEEL_CIRCUMFERENCE;

        public static final double kEncoderDriveP = 0.2;
        public static final double kEncoderDriveMin = 0.2;
        public static final double kEncoderDriveMax = 0.5;
    }

    public static class TurnDriveConstants {


        public static final double kTurnDriveP = 0.2;
        public static final double kTurnDriveMin = 0.2;
        public static final double kTurnDriveMax = 0.6;
    }

    public static class AprilTagsConstants {
        public static final AprilTagGameDatabase kAprilTagsItems = new AprilTagGameDatabase();

        public static final double kAprilTagTargetDistance = 80; // Control target distance for the robot to arrive at the backdrop in ???

        public static final double kAprilTagP = 0.4; // Control P value for the PID Controller
        public static final double kAprilTagMin = 0.5; // Control Minimum value for the PID Controller
        public static final double kAprilTagMax = 0.6; // Control Maximum value for the PID Controller
    }

    public static class TeamPropConstants {
        public static final double kTeamPropTargetLengthMin = 70; // Minimum target length for the team prop by pixels
        public static final double kTeamPropTargetLengthMax = 90; // Maximum target length for the team prop by pixels
        public static final double kTeamPropTargetLeftMin = 20; // Minimum Target coordinates of the image's left side
        public static final double kTeamPropTargetLeftMax = 28; // Maximum Target coordinates of the image's left side
        public static final double kTeamPropTargetRightMin = 20; // Minimum Target coordinates of the image's right side
        public static final double kTeamPropTargetRightMax = 28; // Maximum Target coordinates of the image's right side

        public static final double kTeamPropMin = 0.1; // Control Minimum value for the PID Controller
        public static final double kTeamPropMax = 0.4; // Control Maximum value for the PID Controller
    }

    public static class CascadeLiftConstants {
        public static final double kLiftUpPower = 1;
        public static final double kLiftDownPower = -0.2;
        public static final double kLiftAutoPower = 0.7;

        public static final double kAutoHeight = 7; // The height to reach the bottom of the backdrop scoring area.

        public static final double kCOUNTS_PER_ROTATION = 28; // Conversion factor for encouder counts per motor rotation
        public static final double kDRIVE_GEAR_REDUCTION = 0; // The Gear Ratio needed for the lift motor, needs to change
        public static final double kCOUNTS_PER_INCH = kCOUNTS_PER_ROTATION * kDRIVE_GEAR_REDUCTION;
    }

    public static class RollerIntakeConstants {
        public static final double kDeadband = 0.3;
        public static final double kRollerAutoSpeed = 0.3;
        public static final double kRollerDistance = 3; // In inches
    }
}
