package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class limelightShooting {
    private Limelight3A limeLight;
    private double distance = 0;
    private double tagCount = 0;
    private int currentPipeline = -1;

    public void init(HardwareMap hardwareMap) {
        limeLight = hardwareMap.get(Limelight3A.class, "limeLight");
        limeLight.pipelineSwitch(0);  // AprilTag pipeline = 0
        currentPipeline = 0;
        limeLight.start();
    }

    private void checkPipeline(String teamcolor) {
        int targetPipeline;

        if (teamcolor.equalsIgnoreCase("red")) {
            targetPipeline = 0;
        } else {
            targetPipeline = 1;
        }

        if (targetPipeline != currentPipeline) {
            limeLight.pipelineSwitch(targetPipeline);
            currentPipeline = targetPipeline;
        }
    }

    public double getDistanceFromTage(String teamcolor) {
        checkPipeline(teamcolor);

        LLResult LLResult = limeLight.getLatestResult();
        if (LLResult != null && LLResult.isValid()) {
            tagCount = LLResult.getFiducialResults().size();
            if (tagCount > 0) {
                double ta = LLResult.getTa() / tagCount;
                double scale = 83.6021;
                distance = (scale / ta);
            }
        }

        return distance;
    }

    public double getAngleFromTage(String teamcolor) {
        checkPipeline(teamcolor);

        LLResult LLResult = limeLight.getLatestResult();
        if (LLResult != null && LLResult.isValid()) {
            return LLResult.getTx();
        }

        return 0.0;
    }
}
