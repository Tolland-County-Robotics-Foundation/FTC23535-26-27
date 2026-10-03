/* Mecnum drive mechanism for Teleop one, this takes the input(controllers)
and then outputs(power to motors).
*/

package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MechanumDrive {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private IMU imu;

    public void init(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT
        ));
        imu.initialize(parameters);

        imu.resetYaw();
    }

    public void FieldCentricDrive(double drive, double strafe, double turn) {


        //Driving sideways usualdrive takes more power than driving forwards
        strafe = strafe * 1.25;

        double SpeedMultiplier = 0.85;

        double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double max = Math.max(Math.abs(drive) + Math.abs(turn), 1);

        double adjusted_strafe = -drive * Math.sin(heading) + strafe * Math.cos(heading);

        double adjusted_drive = drive * Math.cos(heading) + strafe * Math.sin(heading);

        double flPower = (adjusted_drive - adjusted_strafe - turn / max) * SpeedMultiplier;
        double frPower = (adjusted_drive + adjusted_strafe + turn / max) * SpeedMultiplier;
        double blPower = (adjusted_drive + adjusted_strafe - turn / max) * SpeedMultiplier;
        double brPower = (adjusted_drive - adjusted_strafe + turn / max) * SpeedMultiplier;

        frontLeft.setPower(flPower);
        frontRight.setPower(frPower);
        backLeft.setPower(blPower);
        backRight.setPower(brPower);
    }

    public void Drive(double drive, double strafe, double turn) {

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
