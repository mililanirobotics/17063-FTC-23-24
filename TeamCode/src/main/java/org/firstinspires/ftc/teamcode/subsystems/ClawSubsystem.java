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

    // Tele-Op Method for controlling the claw
    public void operate (Gamepad gamepad) {
        if (gamepad.a && !clawToggle) {
            clawToggle = true;
        }
        else if (gamepad.a && clawToggle) {
            clawToggle = false;
        }

        if (clawToggle) {
            // Close position for the claw
            clawServo.setPosition(1);
        }
        else {
            // Open position for the claw
            clawServo.setPosition(0.55);
        }
    }

    public void autoOperate (String direction) {
        if (direction == "Close") {
            clawServo.setPosition(1);
        }
        else if (direction == "Open") {
            clawServo.setPosition(0.55);
        }
    }

    public void shutdown() {
        clawServo.setPosition(0);
    }
}
