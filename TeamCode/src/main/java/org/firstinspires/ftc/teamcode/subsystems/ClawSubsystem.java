package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawSubsystem {

    private Servo clawServo;

    boolean clawToggle;

    public ClawSubsystem (HardwareMap hwMap) {
        clawServo = hwMap.get(Servo.class, "clawServo");
        clawToggle = false;

        clawServo.setDirection(Servo.Direction.FORWARD);
    }

    public void operate (Gamepad gamepad) {
        if (gamepad.a && !clawToggle) {
            clawToggle = true;
        }
        else if (gamepad.a && clawToggle) {
            clawToggle = false;
        }

        if (clawToggle) {
            clawServo.setPosition(1);
        }
        else {
            clawServo.setPosition(0);
        }
    }

    public void autoOperate () {
        if (clawToggle) {
            clawServo.setPosition(1);
            clawToggle = false;
        }
        else {
            clawServo.setPosition(0);
            clawToggle = true;
        }
    }

    public void shutdown() {
        clawServo.setPosition(0);
    }
}
