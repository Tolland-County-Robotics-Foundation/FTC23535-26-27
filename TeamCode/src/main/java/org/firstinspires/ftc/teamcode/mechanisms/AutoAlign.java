package org.firstinspires.ftc.teamcode.mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class AutoAlign {

    public static double kp = -0.1;
    public static double ki = 0;
    public static double kd = 0;
    public static double ks = 0;
    public static double ka = 0;
    public static double kv = 0;

    public static double target = 0;
    public double currentposition = 0;
    public double power = 0;

    limelightShooting limelightShooting = new limelightShooting();

    MechanumDrive drivetrain = new MechanumDrive();

    PIDF pidf = new PIDF();


    //initiates intake motor
    public void init(HardwareMap hardwareMap) {
        limelightShooting.init(hardwareMap);

        drivetrain.init(hardwareMap, 2);
    }

    public void Align (String teamcolor) {

        currentposition = limelightShooting.getAngleFromTage(teamcolor);

        if (currentposition != 0) {
            power = pidf.CALCULATE(kp, ki, kd, target, currentposition, ks,ka,kv);
            drivetrain.Drive(0,0,power);
        } else {
            stopAlign();
        }
    }

    public void stopAlign(){
        power = 0;
        drivetrain.Drive(0,0,0);
        pidf.Reset();
    }



}
