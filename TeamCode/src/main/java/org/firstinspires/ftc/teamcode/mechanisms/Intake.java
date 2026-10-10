package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    private DcMotor intake;
//initiates intake motor
    public void init(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setDirection(DcMotor.Direction.FORWARD);
    }
//intiates code
   public void runIntake() {
        intake.setPower(1.0);
//runs intake

    } public void runOuttake() {
        intake.setPower(-1.0);
//runs outake



    } public void stopIntake() {
        intake.setPower(0.0);
        //stops the intake motor
    }
}
