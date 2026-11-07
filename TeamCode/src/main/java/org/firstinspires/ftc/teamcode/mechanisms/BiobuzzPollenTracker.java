package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Biobuzz Pollen Tracker", group = "TeleOp")
public class BiobuzzPollenTracker extends LinearOpMode {

    private DistanceSensor intake_sensor;
    private DistanceSensor outtake_Sensor;


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

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            //9

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

            if (gamepad1.right_trigger > 0.1) {

                if (gamepad1.back) {
                    x_IntakeCount = 0;
                    y_OuttakeCount = 0;
                    P_PollenStored = 0;
                }


                telemetry.addData("Status", "Running");
                telemetry.addData("Pollen Stored (P)", P_PollenStored + " / " + MAX_CAPACITY);
                telemetry.addData("Total Intake (x)", x_IntakeCount);
                telemetry.addData("Total Outtake (y)", y_OuttakeCount);
                telemetry.addData("At Max Capacity", P_PollenStored >= MAX_CAPACITY ? "YES (STOPPED)" : "NO");
                telemetry.addData("Intake Dist (cm)", "%.2f", intakeDist);
                telemetry.addData("Outtake Dist (cm)", "%.2f", outtakeDist);
                telemetry.update();
                ElapsedTime mytimer = new ElapsedTime();
                mytimer.reset();
                while (opModeIsActive()  && mytimer.seconds() < 0.5) {
                    telemetry.addData("Time Elapsed", mytimer.seconds());
                 telemetry.update();
                }












            }
        }
    }
}

