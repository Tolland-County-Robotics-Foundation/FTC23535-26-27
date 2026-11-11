
package org.firstinspires.ftc.teamcode.tests;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.AutoAlign;
import org.firstinspires.ftc.teamcode.mechanisms.MechanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.limelightShooting;

@TeleOp(name="Auto Align test", group="Linear OpMode")
public class AutoAligntest extends LinearOpMode {

    AutoAlign autoAlign = new AutoAlign();

    MechanumDrive drivetrain = new MechanumDrive();

    limelightShooting limelightShooting = new limelightShooting();

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        autoAlign.init(hardwareMap);

        limelightShooting.init(hardwareMap);

        drivetrain.init(hardwareMap, 2);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();


        while (opModeIsActive()) {

            String teamcolor = "red";

            if (gamepad1.a) {
                autoAlign.Align("red");
                teamcolor = "red";
            } else if (gamepad1.b) {
                autoAlign.Align("blue");
                teamcolor = "blue";
            } else {
                autoAlign.stopAlign();
                drivetrain.Drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            }

            drivetrain.Drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);


            telemetry.addData("target", autoAlign.target);
            telemetry.addData("current position", limelightShooting.getAngleFromTage(teamcolor));
            telemetry.addData("power", autoAlign.power);
            telemetry.addLine("a = red, b = blue");
            telemetry.update();
        }
    }}
