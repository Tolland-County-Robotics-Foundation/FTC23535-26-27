package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class Hood {

    double target;

    limelightShooting limelightShooting = new limelightShooting();
    private ServoImplEx hood;

    public void init(HardwareMap hardwareMap) {
        hood = hardwareMap.get(ServoImplEx.class, "intake");
        hood.setDirection(ServoImplEx.Direction.FORWARD);
        limelightShooting.init(hardwareMap);
    }

    public void aimhoodtest(String teamcolor, double distancetest) {
        hood.setPosition(distancetest);
    }

    public void aimhood(String teamcolor) {
        double distance = limelightShooting.getDistanceFromTage(teamcolor);
        if (distance > 0) {
            target = 1785 * Math.exp(2.58e-3 * distance);
        }

        hood.setPosition(target);

    }

    public void reset() {
        hood.setPosition(0);
    }
}
