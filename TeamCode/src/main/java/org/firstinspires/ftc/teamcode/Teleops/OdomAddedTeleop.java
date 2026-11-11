package org.firstinspires.ftc.teamcode.Teleops;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp(name="OdomAddedTeleop", group="Linear OpMode")
public class OdomAddedTeleop extends LinearOpMode {

    MecanumDrive drivetrain = new MecanumDrive();
    Follower follower;
    private final PoseFactory pf = PoseFactory.degrees();
    private boolean isHolding = false;



    @Override
    public void runOpMode() {
        // Initializes the motors in our mechanism
        drivetrain.init(hardwareMap, 1);

        // Initializes the Pedro Pathing follower
        follower = Constants.create(hardwareMap);

        // Set starting position (X, Y, Heading in degrees)
        follower.setPose(pf.of(58.8, 8.8, 90));

        waitForStart();

        while (opModeIsActive()) {
            // Always update follower localization so odometry stays accurate
            follower.update();

            Pose currentPose = follower.pose();
            Pose targetPose;

            if (gamepad1.dpad_down) {
                if (!isHolding) {
                    follower.hold(currentPose);
                    isHolding = true;
                }
            } else if (gamepad1.dpad_up) {
                if (isHolding) {
                    follower.stop();
                    isHolding = false;
                }

                if (currentPose.x() > 72 && currentPose.y() > 72) {
                    // Quadrant 1
                    if (currentPose.y() > 103) {
                        targetPose = pf.of(128, 99, 355);
                    } else {
                        targetPose = pf.of(128, 89, 5);
                    }
                } else if (currentPose.x() > 72 && currentPose.y() < 72) {
                    // Quadrant 2
                    if (currentPose.x() > 103) {
                        targetPose = pf.of(97, 12, 265);
                    } else {
                        targetPose = pf.of(89, 12, 275);
                    }
                } else if (currentPose.x() < 72 && currentPose.y() < 72) {
                    // Quadrant 3
                    if (currentPose.y() < 38) {
                        targetPose = pf.of(13, 42, 175);
                    } else {
                        targetPose = pf.of(13, 52, 185);
                    }
                } else {
                    // Quadrant 4
                    if (currentPose.x() < 38) {
                        targetPose = pf.of(42, 128, 85);
                    } else {
                        targetPose = pf.of(52, 128, 95);
                    }
                }

                Path flower = Paths.line(currentPose, targetPose).constant(targetPose);
                follower.follow(flower);
            } else {
                if (isHolding || follower.isBusy()) {
                    follower.stop();
                    isHolding = false;
                }
                // Manual teleop drive when neither dpad button is pressed
                drivetrain.FieldCentricDrive(-gamepad1.left_stick_y, gamepad1.left_stick_x, -gamepad1.right_stick_x);
            }

            telemetry.addData("Lock Triggered:", isHolding);
            telemetry.addData("Pose:", follower.pose());
            telemetry.update();
        }
    }
}
