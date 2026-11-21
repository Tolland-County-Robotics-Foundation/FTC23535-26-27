
package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.Flywheel;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Stopper;
import org.firstinspires.ftc.teamcode.mechanisms.limelightShooting;
import org.firstinspires.ftc.teamcode.mechanisms.transfer;

@TeleOp(name="Stopper Test", group="Linear OpMode")
public class stoppertest extends LinearOpMode {

    Stopper stopper = new Stopper();
    public ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        stopper.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.right_bumper) {
                stopper.open();
            } else if (gamepad1.left_bumper) {
                stopper.blocking();
            }

            telemetry.addData("open position", Stopper.openposition);
            telemetry.addData("closed position", Stopper.closedposition);
            telemetry.addData("current position", stopper.stopper.getPosition());
            telemetry.update();
        }
    }}
