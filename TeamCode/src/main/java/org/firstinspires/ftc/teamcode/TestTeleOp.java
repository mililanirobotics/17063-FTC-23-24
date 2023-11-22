package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//import org.firstinspires.ftc.teamcode.commands.CascadeLiftCommand;
//import org.firstinspires.ftc.teamcode.subsystems.CascadeLiftSubsystem;
//import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumSubsystem;
//import org.firstinspires.ftc.teamcode.subsystems.RollerIntakeSubsystem;

//import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name="TestTeleOp")

public class TestTeleOp extends OpMode
{
    // Declare OpMode members.
    private MecanumSubsystem mecanumSubsystem;
//    private CascadeLiftSubsystem cascadeLiftSubsystem;
//    private ClawSubsystem clawSubsystem;
//    private RollerIntakeSubsystem rollerIntakeSubsystem;

//    private CascadeLiftCommand liftOperate;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    public void init() {
        mecanumSubsystem = new MecanumSubsystem(this.hardwareMap, telemetry);
//        cascadeLiftSubsystem = new CascadeLiftSubsystem(this.hardwareMap);
//        clawSubsystem = new ClawSubsystem(this.hardwareMap);
//        rollerIntakeSubsystem = new RollerIntakeSubsystem(this.hardwareMap);

//        liftOperate = new CascadeLiftCommand(cascadeLiftSubsystem, gamepad2);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    public void loop() {
        mecanumSubsystem.operate(gamepad1);
//        clawSubsystem.operate(gamepad2);
//        rollerIntakeSubsystem.operate(gamepad2);
//        liftOperate.cascadePower();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    public void stop() {
        mecanumSubsystem.shutdown();
//        clawSubsystem.shutdown();
//        rollerIntakeSubsystem.shutdown();
//        liftOperate.shutdown();
    }
}

