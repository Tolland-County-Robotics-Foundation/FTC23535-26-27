package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class Flywheel {

    PIDF pidf = new PIDF();
    limelightShooting limelightShooting = new limelightShooting();
    public DcMotorEx flywheel;
    public static double kp;
    public static double ki ;
    public static double kd;
    public static double kf;
    public static double ks;
    public static double ka;
    public static double kv;

    public double lastargetvelocity;


    public static double target = 2000;

    public void init(HardwareMap hardwareMap) {
        flywheel = hardwareMap.get(DcMotorEx.class, "flywheel");
        flywheel.setDirection(DcMotorEx.Direction.REVERSE);
        limelightShooting.init(hardwareMap);
    }

    public void runflywheel(){

        double RPM = flywheel.getVelocity() * (60.0/28.0);

        double power = pidf.CALCULATE(kp, ki, kd, target, RPM, ks,ka,kv);
        flywheel.setPower(power);

    }

    public void runflywheelwhihcamrared(){
        double distance = limelightShooting.getDistanceFromTage("red");

        double RPM = flywheel.getVelocity() * (60.0/28.0);

        double power = pidf.CALCULATE(kp, ki, kd, target, RPM, ks,ka,kv);
        flywheel.setPower(distance);

    }

    public void runflywheelwhihcamrablue(){
        double distance = limelightShooting.getDistanceFromTage("blue");

        double RPM = flywheel.getVelocity() * (60.0/28.0);

        double power = pidf.CALCULATE(kp, ki, kd, target, RPM, ks,ka,kv);
        flywheel.setPower(distance);

    }


    public void stopflywheel(){
        flywheel.setPower(0);
        pidf.Reset();
    }
}


