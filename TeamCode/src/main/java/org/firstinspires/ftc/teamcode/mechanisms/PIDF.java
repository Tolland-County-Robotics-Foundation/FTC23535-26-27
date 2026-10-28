package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDF {
    public ElapsedTime timer = new ElapsedTime(); //metioning timer
    public double integralsum = 0;
    public double previouserror;
    public double error;
    public double deltatime = 0.0001;
    public double lastargetvelocity=0;


    public double CALCULATE(double kp, double ki, double kd, double kf, double target, double currentposition,double ks, double ka, double kv) {

        deltatime = timer.seconds();

        error = target - currentposition;

        integralsum += error * deltatime;
        double p = kp * error; //what proportional is

        double i = ki * integralsum; //what intergral is

        double d = kd * ((error - previouserror) / deltatime); // what derivitive is

        double sign=Math.signum(target);

        double targetacceleration=(lastargetvelocity-target)/deltatime;

        double f = ks*sign+kv*target+ka*targetacceleration;

        double output = p + i + d + f; // add all the pidf to create the otuput
        previouserror = error;
        timer.reset(); // reetts the timer loop

        return output;
    }
    public void Reset() {
        integralsum = 0;
        previouserror = 0;
        deltatime = 0.0001;
    }

}


