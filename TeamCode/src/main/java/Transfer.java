import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Transfer {

    private DcMotor Transfer;

    //initiates Transfer motor
    public void init(HardwareMap hardwareMap) {
        Transfer = hardwareMap.get(DcMotor.class, "Transfer");
        Transfer.setDirection(DcMotor.Direction.FORWARD);
    }

    //intakes code
    public void runIntake() {
        Transfer.setPower(1.0);
    }

    public void runOuttake() {
        Transfer.setPower(-1.0);
    }

    public void stoptransfer() {
        Transfer.setPower(0.0);
        //stops the Transfer motor
    }
}
