package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDF {
    public ElapsedTime timer = new ElapsedTime();
    public double integralsum = 0;
    public double previouserror;
    public double error;
    public double deltatime;

    public double CALCULATE(double kp, double ki, double kd, double kf, double target, double currentposition) {

        deltatime = timer.seconds();

        error = target - currentposition;

        integralsum += error * deltatime;
        double p = kp * error; //what proportional is

        double i = ki * integralsum; //what intergral is

        double d = kd * ((error - previouserror) / deltatime); // what derivitive is

        double f = kf; // waht feedforward is

        double output = p + i + d + f; // add all the pidf to create the otuput
        previouserror = error;
        timer.reset(); // reetts the timer loop

        return output;
    }

}


