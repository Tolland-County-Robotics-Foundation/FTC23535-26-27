/* Mecnum drive mechanism for Teleop one, this takes the input(controllers)
and then outputs(power to motors).
*/
package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrive {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    public void init(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotor.class, "lf");
        frontRight = hardwareMap.get(DcMotor.class, "rf");
        backLeft = hardwareMap.get(DcMotor.class, "lr");
        backRight = hardwareMap.get(DcMotor.class, "rr");



        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
    }

    public void drive(double drive, double strafe, double turn) {

        //Driving sideways usually takes more power than driving forwards
        strafe = strafe * 1.25;

        double SpeedMultiplier = 0.75;

        double flPower = (drive - strafe - turn) * SpeedMultiplier;
        double frPower = (drive + strafe + turn) * SpeedMultiplier;
        double blPower = (drive + strafe - turn) * SpeedMultiplier;
        double brPower = (drive - strafe + turn) * SpeedMultiplier;

        // Normalize the values so no wheel power exceeds 100%
        double max = Math.max(Math.abs(flPower), Math.abs(frPower));
        max = Math.max(max, Math.abs(blPower));
        max = Math.max(max, Math.abs(brPower));

        if (max > 1.0) {
            flPower /= max;
            frPower /= max;
            blPower /= max;
            brPower /= max;
        }

        frontLeft.setPower(flPower);
        frontRight.setPower(frPower);
        backLeft.setPower(blPower);
        backRight.setPower(brPower);
    }
}
