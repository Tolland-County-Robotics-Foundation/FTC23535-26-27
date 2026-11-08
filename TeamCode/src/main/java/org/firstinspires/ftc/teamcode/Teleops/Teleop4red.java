/* This is the base Teleop program for mecanum drive.
 */
package org.firstinspires.ftc.teamcode.Teleops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Flywheel;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.MechanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.transfer;

@TeleOp(name="FieldCentricTeleOp4 for red", group="Linear OpMode")
public class Teleop4red extends LinearOpMode {

    MechanumDrive drivetrain = new MechanumDrive();

    Flywheel flywheel = new Flywheel();

    transfer Transfer = new transfer();

    Intake intake = new Intake();

    @Override
    public void runOpMode() {
        //Initializes the motors in our Method aka function
        drivetrain.init(hardwareMap, 2);

        intake.init(hardwareMap);

        Transfer.init(hardwareMap);

        flywheel.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {

            //runs our movement method with our gamepad parameters
            drivetrain.FieldCentricDrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            if (gamepad1.right_bumper) {
                intake.runIntake();
            } else if (gamepad1.left_bumper) {
                intake.runOuttake();
                Transfer.runTransferback();
            } else {
                intake.stopIntake();
            }

            if(gamepad1.left_trigger > 0.2){
                flywheel.runflywheelwhithcamrared();
            } else {
                flywheel.stopflywheel();
            }

            if (gamepad1.left_trigger > 0.2 && Math.abs(flywheel.RPM - flywheel.target) <= 100) {
                Transfer.runTransfer();
            } else {
                Transfer.stopTransfer();
            }




            telemetry.addData("target", flywheel.target);
            telemetry.addData("trigger",gamepad1.left_trigger);
            telemetry.addData("rpm", flywheel.RPM);
            telemetry.addData("power %", flywheel.flywheel.getPower() * 100);
            telemetry.update();
        }
    }
}