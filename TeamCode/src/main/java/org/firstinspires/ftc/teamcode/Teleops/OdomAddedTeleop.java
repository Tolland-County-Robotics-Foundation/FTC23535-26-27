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
        drivetrain.init(hardwareMap);

        // Initializes the Pedro Pathing follower

        follower = Constants.create(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
            Pose currentPose = follower.pose();
            Pose targetPose;
            if (gamepad1.dpad_down) {
                if (!isHolding) {
                    follower.hold(follower.pose());
                    isHolding = true;
                }
                follower.update();
            } else if (gamepad1.dpad_up) {
                if (currentPose.x() > 72 && currentPose.y() > 72) {
                    if (currentPose.y() > 103) {
                        targetPose = pf.of(128, 99, 355);
                        Path flower1 = Paths.line(currentPose, targetPose).constant(targetPose);
                        follower.follow(flower1);
                    } else {
                        targetPose = pf.of(128, 89, 5);
                        Path flower1 = Paths.line(currentPose, targetPose).constant(targetPose);
                        follower.follow(flower1);
                    }
                    follower.update();
                } else if (currentPose.x() > 72 && currentPose.y() < 72) {
                    if (currentPose.x() > 72 && currentPose.y() > 72) {
                        if (currentPose.x() > 103) {
                            targetPose = pf.of(97, 12, 265);
                            Path flower2 = Paths.line(currentPose, targetPose).constant(targetPose);
                            follower.follow(flower2);
                        } else {
                            targetPose = pf.of(89, 12, 275);
                            Path flower2 = Paths.line(currentPose, targetPose).constant(targetPose);
                            follower.follow(flower2);
                        }
                        follower.update();
                } else if (currentPose.x() < 72 && currentPose.y() < 72) {
                            if (currentPose.y() < 38) {
                                targetPose = pf.of(13, 42, 175);
                                Path flower3 = Paths.line(currentPose, targetPose).constant(targetPose);
                                follower.follow(flower3);
                            } else {
                                targetPose = pf.of(13, 52, 185);
                                Path flower3 = Paths.line(currentPose, targetPose).constant(targetPose);
                                follower.follow(flower3);
                            }
                            follower.update();
                } else {
                        if (currentPose.x() < 38) {
                            targetPose = pf.of(42, 128, 85);
                            Path flower4 = Paths.line(currentPose, targetPose).constant(targetPose);
                            follower.follow(flower4);
                        } else {
                            targetPose = pf.of(52, 128, 95);
                            Path flower4 = Paths.line(currentPose, targetPose).constant(targetPose);
                            follower.follow(flower4);
                        }
                        follower.update();
                }
            }
            else {
                if (isHolding) {
                    follower.stop();
                    isHolding = false;
                }
                // Manual teleop drive when trigger is released
                drivetrain.Drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            }
            telemetry.addData("Lock Triggered:", isHolding);
            telemetry.addData("Pose:", follower.pose());
            telemetry.update();
        }
    }
}
}

