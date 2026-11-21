
package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.Flywheel;
import org.firstinspires.ftc.teamcode.mechanisms.Hood;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.limelightShooting;
import org.firstinspires.ftc.teamcode.mechanisms.transfer;

@TeleOp(name="Flywheel_TestV2", group="Linear OpMode")
public class Launcher_TestV3 extends LinearOpMode {

    Flywheel flywheel = new Flywheel();

    transfer Transfer = new transfer();

    Intake intake = new Intake();

    Hood hood = new Hood();

    limelightShooting limelightShooting = new limelightShooting();

    double RPM = 0;

    double hoodangle;

    String teamcolor = "red";
    public ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {

        intake.init(hardwareMap);

        Transfer.init(hardwareMap);

        flywheel.init(hardwareMap);

        limelightShooting.init(hardwareMap);

        runtime.startTime();

        limelightShooting.init(hardwareMap);

        waitForStart();



        while (opModeIsActive()) {

            if (gamepad1.right_bumper) {
                intake.runIntake();
                Transfer.runTransfer();
            } else if (gamepad1.left_bumper) {
                intake.runOuttake();
                Transfer.runTransferback();
            } else {
                intake.stopIntake();
                Transfer.stopTransfer();
            }


            if (gamepad1.a) {
                teamcolor = "red";
            } else if (gamepad1.b) {
                teamcolor = "blue";
            }

            if (gamepad1.dpad_up && runtime.seconds() > 0.1 ) {
                RPM = RPM + 10;
                runtime.reset();
            } else if (gamepad1.dpad_down && runtime.seconds() > 0.1) {
                RPM = RPM - 10;
                runtime.reset();
            }

            if (gamepad1.dpad_right && runtime.seconds() > 0.1 ) {
                hoodangle = hoodangle + 0.003;
                runtime.reset();
            } else if (gamepad1.dpad_right && runtime.seconds() > 0.1) {
                hoodangle = hoodangle - 0.003;
                runtime.reset();
            }

            if(gamepad1.left_trigger > 0.2){
                flywheel.runflywheel(RPM);
                hood.aimhoodtest(teamcolor, hoodangle);
            } else {
                flywheel.stopflywheel();
            }

            if(gamepad1.a) {
                hood.reset();
            }
            telemetry.addLine("hood reset is a button");
            telemetry.addLine("Dpad right to increase angle, Dpad left to decrease angle");
            telemetry.addLine("Dpad up to increase RPM, Dpad down to decrease RPM");
            telemetry.addLine("Press a to switch to red, Press b to switch to blue");
            telemetry.addData("TeamColor", teamcolor);
            telemetry.addData("Distance", limelightShooting.getDistanceFromTage(teamcolor));
            telemetry.addData("rpm", flywheel.flywheel.getVelocity() * (60.0/28.0));
            telemetry.addData("our RPM %", RPM);
            telemetry.setMsTransmissionInterval(50);
            telemetry.update();
        }
    }}
