package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.AutoAlign;
import org.firstinspires.ftc.teamcode.mechanisms.Flywheel;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.MechanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.transfer;

@Autonomous(name="Test Auto Blue", group="Autonomous")
public class TestBlue extends LinearOpMode {

    MechanumDrive drivetrain = new MechanumDrive();
    Flywheel flywheel = new Flywheel();
    transfer Transfer = new transfer();
    Intake intake = new Intake();
    ElapsedTime timer = new ElapsedTime();

    String teamcolor = "blue";

    @Override
    public void runOpMode() {
        flywheel.init(hardwareMap);
        Transfer.init(hardwareMap);
        intake.init(hardwareMap);
        drivetrain.init(hardwareMap, 2);

        waitForStart();

        timer.reset();

        while (opModeIsActive() && timer.seconds() < 3) {
            drivetrain.Drive(0.2, 0, 0);
        }
        drivetrain.Drive(0, 0, 0);

        while (opModeIsActive() && timer.seconds() < 15) {
            // waiting
        }

        while (opModeIsActive() && timer.seconds() < 25) {
            flywheel.runflywheelwithcamera(teamcolor);

            if (flywheel.target > 0 && Math.abs(flywheel.RPM - flywheel.target) <= 200) {
                Transfer.runTransfer();
                intake.runIntake();
            } else {
                Transfer.stopTransfer();
                intake.stopIntake();
            }
        }

        flywheel.stopflywheel();
        Transfer.stopTransfer();
        intake.stopIntake();
    }
}
