/* This is the base Teleop program for mecanum drive.
 */
package org.firstinspires.ftc.teamcode.Teleops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Flywheel;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.MechanumDrive;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;

@TeleOp(name="FieldCentricTeleOp2 with intake", group="Linear OpMode")
public class FieldCentricTeleop3 extends LinearOpMode {

   MechanumDrive drivetrain = new MechanumDrive();
   Flywheel flywheel = new Flywheel();
   Intake intake = new Intake();

    @Override
    public void runOpMode() {
        //Initializes the motors in our Method aka function
        drivetrain.init(hardwareMap);

        intake.init(hardwareMap);

        flywheel.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            //runs our movement method with our gamepad parameters
            drivetrain.FieldCentricDrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            if (gamepad2.right_bumper) {
                intake.runIntake();
            } else if (gamepad2.left_bumper) {
                intake.runOuttake();
            } else {
                intake.stopIntake();
            }

            if(gamepad2.left_trigger > 0.2){
                flywheel.runflywheel();
            } else {
                flywheel.stopflywheel();
            }
        }
    }
}