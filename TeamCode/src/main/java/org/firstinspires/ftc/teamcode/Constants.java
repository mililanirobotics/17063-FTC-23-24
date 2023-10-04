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
    }

    public static class AprilTagsConstants {
        public static final AprilTagGameDatabase kAprilTagsItems = new AprilTagGameDatabase();
    }
}
