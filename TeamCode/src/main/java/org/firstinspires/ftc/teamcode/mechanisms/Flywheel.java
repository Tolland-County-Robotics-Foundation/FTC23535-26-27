package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class Flywheel {

    PIDF pidf = new PIDF();

    public DcMotorEx flywheel;
    public static double kp = 0.012;
    public static double ki = 0.0001;
    public static double kd = 0;
    public static double kf;
    public static double ks = 0.18;
    public static double ka = 0.6;
    public static double kv = 0.000175;

    public double lastargetvelocity;


    public static double target = 2000;

    public void init(HardwareMap hardwareMap) {
        flywheel = hardwareMap.get(DcMotorEx.class, "flywheel");
        flywheel.setDirection(DcMotorEx.Direction.REVERSE);
    }

    public void runflywheel(){

        double RPM = flywheel.getVelocity() * (60.0/28.0);

        double power = pidf.CALCULATE(kp, ki, kd, target, RPM, ks,ka,kv);
        flywheel.setPower(power);
;
    }


    public void stopflywheel(){
        flywheel.setPower(0);
        pidf.Reset();
    }
}


