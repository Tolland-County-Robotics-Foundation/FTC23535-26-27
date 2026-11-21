package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Stopper {

    public static double openposition = 0;

    public static double closedposition = 90;


    public Servo stopper;

    public void init(HardwareMap hardwareMap) {
        stopper = hardwareMap.get(Servo.class, "stopper");
        stopper.setDirection(Servo.Direction.FORWARD);
    }

    public void blocking() {
        stopper.setPosition(closedposition / 360);


    }

    public void open() {
        stopper.setPosition(openposition / 360);


    }
}
