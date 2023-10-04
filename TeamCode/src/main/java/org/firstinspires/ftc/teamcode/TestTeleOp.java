package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.Telemetry;

//import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name="TestTeleOp")

public class TestTeleOp extends OpMode
{
    // Declare OpMode members.
    private MecanumSubsystem mecanumSubsystem;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    public void init() {
        mecanumSubsystem = new MecanumSubsystem(this, telemetry);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    public void loop() {
        mecanumSubsystem.operate(gamepad1);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    public void stop() {
        mecanumSubsystem.shutdown();
    }
}

