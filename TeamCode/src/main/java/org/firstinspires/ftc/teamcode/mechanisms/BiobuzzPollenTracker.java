package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Biobuzz Pollen Tracker", group = "TeleOp")
public class BiobuzzPollenTracker extends LinearOpMode {

    private DistanceSensor intake_sensor;
    private DistanceSensor outtake_Sensor;
    private DcMotor intake_motor;

    private static final double THRESHOLD_CM = 6.0;

    private int x_IntakeCount = 0;
    private int y_OuttakeCount = 0;
    private int P_PollenStored = 0;

    private boolean intakeDetected = false;
    private boolean outtakeDetected = false;

    private static final int MAX_CAPACITY = 4;

    @Override
    public void runOpMode() {
        intake_sensor = hardwareMap.get(DistanceSensor.class, "intake_sensor");
        outtake_Sensor = hardwareMap.get(DistanceSensor.class, "outtake_sensor");
        intake_motor = hardwareMap.get(DcMotor.class, "intake_motor");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            double intakeDist = intake_sensor.getDistance(DistanceUnit.CM);
            double outtakeDist = outtake_Sensor.getDistance(DistanceUnit.CM);

            if (intakeDist < THRESHOLD_CM) {
                if (!intakeDetected) {
                    x_IntakeCount++;
                    intakeDetected = true;
                }
            } else {
                intakeDetected = false;
            }

            if (outtakeDist < THRESHOLD_CM) {
                if (!outtakeDetected) {
                    y_OuttakeCount++;
                    outtakeDetected = true;
                }
            } else {
                outtakeDetected = false;
            }

            P_PollenStored = x_IntakeCount - y_OuttakeCount;

            if (P_PollenStored < 0) {
                P_PollenStored = 0;
                x_IntakeCount = y_OuttakeCount;
            }

            // Manual reset with Back button
            if (gamepad1.back) {
                x_IntakeCount = 0;
                y_OuttakeCount = 0;
                P_PollenStored = 0;
            }

            // Intake motor control with 4-piece limit enforcement
            if (gamepad1.right_trigger > 0.1) {
                if (P_PollenStored >= MAX_CAPACITY) {
                    intake_motor.setPower(0.0); // Stop intake at max capacity (4)
                } else {
                    intake_motor.setPower(gamepad1.right_trigger);
                }
            } else if (gamepad1.left_trigger > 0.1) {
                intake_motor.setPower(-gamepad1.left_trigger); // Reverse outtake
            } else {
                intake_motor.setPower(0.0);
            }

            // Telemetry feedback
            telemetry.addData("Status", "Running");
            telemetry.addData("Pollen Stored (P)", P_PollenStored + " / " + MAX_CAPACITY);
            telemetry.addData("Total Intake (x)", x_IntakeCount);
            telemetry.addData("Total Outtake (y)", y_OuttakeCount);
            telemetry.addData("At Max Capacity", P_PollenStored >= MAX_CAPACITY ? "YES (STOPPED)" : "NO");
            telemetry.addData("Intake Dist (cm)", "%.2f", intakeDist);
            telemetry.addData("Outtake Dist (cm)", "%.2f", outtakeDist);
            telemetry.update();
        }
    }
}
