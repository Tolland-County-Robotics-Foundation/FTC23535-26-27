package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.acmerobotics.dashboard.config.Config;

@Config
public class Flywheel {

    PIDF pidf = new PIDF();

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


