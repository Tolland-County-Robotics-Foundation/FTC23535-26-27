package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class limelightShooting {
    private Limelight3A limeLight;
    private IMU imu;
    private double distance;
    double tagcount;
    double Ta;
    double amount;

    public void init(HardwareMap hardwareMap) {
        limeLight = hardwareMap.get(Limelight3A.class, "limeLight");
        limeLight.pipelineSwitch(0);  //apraltag pipline = 0


    }

    public void start() {
        limeLight.start();
    }

    public double getDistanceFromTage(String teamcolor) {
        if (teamcolor == "red") {
            limeLight.pipelineSwitch(0);
        } else {
            limeLight.pipelineSwitch(1);
        }

        LLResult LLResult = limeLight.getLatestResult();
        if (LLResult != null && LLResult.isValid()) {
            tagcount = LLResult.getFiducialResults().size();
            double ta = LLResult.getTa() / tagcount;
            double scale = 83.6021; //this is whare to put the y value in the equation//
            distance = (scale / (ta));
        }

        return distance;
    }
}