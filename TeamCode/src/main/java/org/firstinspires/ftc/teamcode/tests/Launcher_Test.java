
package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Flywheel_Test", group="Linear OpMode")
public class Launcher_Test extends LinearOpMode {



    private DcMotor flywheel = null;

    @Override
    public void runOpMode() {


        flywheel = hardwareMap.get(DcMotor.class, "flywheel");

        flywheel.setDirection(DcMotor.Direction.FORWARD);


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();


        while (opModeIsActive()) {

            if (gamepad1.a) {
                flywheel.setPower(-0.25);
            } else if (gamepad1.b) {
                flywheel.setPower(-0.5);
            } else if (gamepad1.x) {
                flywheel.setPower(-0.75);
            } else if (gamepad1.y) {
                flywheel.setPower(-1);
            } else {
                flywheel.setPower(0);
            }

            telemetry.addData("a = 25, b = 50, x = 75, y = 100", 1 );
            telemetry.addData("Power %", flywheel.getPower() * 100);
            telemetry.update();
        }
    }}
