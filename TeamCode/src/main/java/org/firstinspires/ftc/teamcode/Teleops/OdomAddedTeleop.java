package org.firstinspires.ftc.teamcode.Teleops;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp(name="OdomAddedTeleop", group="Linear OpMode")
public class OdomAddedTeleop extends LinearOpMode {

    MecanumDrive drivetrain = new MecanumDrive();
    Follower follower;

    private boolean isHolding = false;

    @Override
    public void runOpMode() {
        // Initializes the motors in our mechanism
        drivetrain.init(hardwareMap);

        // Initializes the Pedro Pathing follower
        follower = Constants.create(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            // Right trigger pressed: lock position in place using Pedro Pathing follower
            if (gamepad1.right_trigger > 0.2) {
                if (!isHolding) {
                    follower.hold(follower.pose());
                    isHolding = true;
                }
                follower.update();
            } else {
                if (isHolding) {
                    follower.stop();
                    isHolding = false;
                }
                // Manual teleop drive when trigger is released
                drivetrain.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            }
            telemetry.addData("Lock Triggered:", isHolding);
            telemetry.addData("Pose:", follower.pose());
            telemetry.update();
        }
    }
}
