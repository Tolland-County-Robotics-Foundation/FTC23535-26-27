
package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mechanisms.Flywheel;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.transfer;

@TeleOp(name="Flywheel_Test", group="Linear OpMode")
public class Launcher_TestV2 extends LinearOpMode {

    Flywheel flywheel = new Flywheel();

    transfer Transfer = new transfer();

    Intake intake = new Intake();


    @Override
    public void runOpMode() {

        intake.init(hardwareMap);

        Transfer.init(hardwareMap);

        flywheel.init(hardwareMap);


        waitForStart();


        while (opModeIsActive()) {

            if(gamepad1.left_trigger > 0.2){
                flywheel.runflywheel();
            } else {
                flywheel.stopflywheel();
            }

            telemetry.addData("trigger",gamepad1.left_trigger);
            telemetry.addData("rpm", flywheel.flywheel.getVelocity() * (60.0/28.0));
            telemetry.addData("power %", flywheel.flywheel.getPower() * 100);
            telemetry.update();
        }
    }}
