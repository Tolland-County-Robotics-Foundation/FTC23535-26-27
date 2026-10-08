package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Flywheel {
    public DcMotorEx flywheel;
    PIDF pidf = new PIDF();
    double kp = 0.1;
    double ki = 0.1;
    double kd = 0.1;
    double kf = 0.1;

    double target;


    public void init(HardwareMap hardwareMap) {
        flywheel = hardwareMap.get(DcMotorEx.class, "frontLeft");

        flywheel.setDirection(DcMotorEx.Direction.FORWARD);
    }

    public void runflywheel(float dpad_up){

        target = 2000;
        double RPM = flywheel.getVelocity()* 60;

        double power = pidf.CALCULATE(kp, ki, kd, kf, target, RPM);


    }

}

