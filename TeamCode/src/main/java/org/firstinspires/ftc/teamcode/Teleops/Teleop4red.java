/* This is the base Teleop program for mecanum drive.
 */
package org.firstinspires.ftc.teamcode.Teleops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.AutoAlign;
import org.firstinspires.ftc.teamcode.mechanisms.Flywheel;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.limelightShooting;
import org.firstinspires.ftc.teamcode.mechanisms.Transfer;
import org.firstinspires.ftc.teamcode.mechanisms.AutoAlign;

@TeleOp(name="FieldCentricTeleOp4 for red", group="Linear OpMode")
public class Teleop4red extends LinearOpMode {

    AutoAlign autoAlign = new AutoAlign();

    MecanumDrive drivetrain = new MecanumDrive();

    Flywheel flywheel = new Flywheel();

    Transfer transfer = new Transfer();

    Intake intake = new Intake();

    limelightShooting limelightShooting = new limelightShooting();

    String teamcolor = "red";

    @Override
    public void runOpMode() {
        //Initializes the motors in our Method aka function
        drivetrain.init(hardwareMap, 2);

        intake.init(hardwareMap);

        transfer.init(hardwareMap);

        flywheel.init(hardwareMap);

        limelightShooting.init(hardwareMap);

        autoAlign.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {


            if (gamepad1.right_bumper) {
                intake.runOuttake();
                transfer.runTransferBack();
            } else if (gamepad1.left_bumper) {
                intake.runIntake();
            } else {
                intake.stopIntake();
            }

            if(gamepad1.left_trigger > 0.2){
                flywheel.runflywheelwithcamerared();
                autoAlign.Align(teamcolor);
            } else {
                flywheel.stopflywheel();
                autoAlign.stopAlign();
            }

            //Only runs transfer when RPM is within range
            if (gamepad1.left_trigger > 0.2 && Math.abs(flywheel.RPM - flywheel.target) <= 100) {
                transfer.runTransfer();
            } else {
                transfer.stopTransfer();
            }

            //runs our movement method with our gamepad parameters
            drivetrain.Drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);



            telemetry.addData("Distance", limelightShooting.getDistanceFromTage(teamcolor));
            telemetry.addData("target", flywheel.target);
            telemetry.addData("trigger",gamepad1.left_trigger);
            telemetry.addData("rpm", flywheel.RPM);
            telemetry.addData("power %", flywheel.flywheel.getPower() * 100);
            telemetry.update();
        }
    }
}