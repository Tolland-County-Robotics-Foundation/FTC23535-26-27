package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class transfer {

    private DcMotor transfer;
    //initiates intake motor
    public void init(HardwareMap hardwareMap) {
        transfer = hardwareMap.get(DcMotor.class, "transfer");
        transfer.setDirection(DcMotor.Direction.FORWARD);
    }
    //intiates code
    public void runTransfer() {
        transfer.setPower(-1.0);
//runs intake

    } public void runTransferback() {
        transfer.setPower(1.0);
//runs outake



    } public void stopTransfer() {
        transfer.setPower(0.0);
        //stops the intake motor
    }
}
