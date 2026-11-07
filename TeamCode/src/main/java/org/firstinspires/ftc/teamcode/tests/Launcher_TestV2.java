
package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.Flywheel;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.limelightShooting;
import org.firstinspires.ftc.teamcode.mechanisms.transfer;

@TeleOp(name="Flywheel_TestV2", group="Linear OpMode")
public class Launcher_TestV2 extends LinearOpMode {

    Flywheel flywheel = new Flywheel();

    transfer Transfer = new transfer();

    Intake intake = new Intake();

    limelightShooting limelightShooting = new limelightShooting();

    double RPM = 0;

    String teamcolor = "red";
    public ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {

        intake.init(hardwareMap);

        Transfer.init(hardwareMap);

        flywheel.init(hardwareMap);

        flywheel.initlimelight(hardwareMap);

        limelightShooting.init(hardwareMap);

        runtime.startTime();

        waitForStart();



        while (opModeIsActive()) {

            if (gamepad1.a) {
                teamcolor = "red";
            } else if (gamepad1.b) {
                teamcolor = "blue";
            }

            if (gamepad1.dpad_up && runtime.seconds() > 0.1 ) {
                RPM = RPM + 1;
                runtime.reset();
            } else if (gamepad1.dpad_down && runtime.seconds() > 0.1) {
                RPM = RPM - 1;
                runtime.reset();
            }

            if(gamepad1.left_trigger > 0.2){
                flywheel.runflywheel(RPM);
            } else {
                flywheel.stopflywheel();
            }

            telemetry.addLine("Dpad up to increase RPM, Dpad down to decrease RPM");
            telemetry.addLine("Press a to switch to red, Press b to switch to blue");
            telemetry.addData("TeamColor", teamcolor);
            telemetry.addData("Distance", limelightShooting.getDistanceFromTage("red"));
            telemetry.addData("rpm", flywheel.flywheel.getVelocity() * (60.0/28.0));
            telemetry.addData("power %", flywheel.flywheel.getPower() * 100);
            telemetry.update();
        }
    }}
