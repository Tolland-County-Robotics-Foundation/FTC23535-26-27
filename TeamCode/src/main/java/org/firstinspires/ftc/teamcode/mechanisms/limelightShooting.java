package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
@TeleOp
public class limelightShooting extends OpMode {
    private Limelight3A limeLight;
    private IMU imu;
    private double distance;
    @Override
    public void init() {
        limeLight = hardwareMap.get(Limelight3A.class, "limeLight");
        limeLight.pipelineSwitch(0);  //apraltag pipline = 0
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot RevHubOrientationOnRobot = new RevHubOrientationOnRobot(com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.UP,
                com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.LEFT); //whare the  robot hub is orenetated
        imu.initialize(new IMU.Parameters(RevHubOrientationOnRobot));
    }
    @Override
    public void start() {
        limeLight.start();
    }
    @Override
    public void loop() {
        YawPitchRollAngles oreantation = imu.getRobotYawPitchRollAngles();
        limeLight.updateRobotOrientation(oreantation.getYaw());
        LLResult LLResult = limeLight.getLatestResult();
        if (LLResult != null && LLResult.isValid()) {
            Pose3D botPose = LLResult.getBotpose_MT2();
            distance = getDistanceFromTage(LLResult.getTa());
            telemetry.addData("distance",distance); // shows on screen
            telemetry.addData("Tx", LLResult.getTx());
            telemetry.addData("Ty", LLResult.getTy());
            telemetry.addData("Ta", LLResult.getTa());
        }
    }
    public double getDistanceFromTage (double ta) {
        double scale =419.979 ; //this is whare to put the y value in the equation//
        double distance = (scale / ta);
        return distance;
    }
}