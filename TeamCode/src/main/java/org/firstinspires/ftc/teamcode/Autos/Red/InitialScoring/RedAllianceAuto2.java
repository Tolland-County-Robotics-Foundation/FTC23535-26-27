package org.firstinspires.ftc.teamcode.Autos.Red.InitialScoring;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
//import org.firstinspires.ftc.teamcode.mechanisms.Transfer;
//import org.firstinspires.ftc.teamcode.mechanisms.Shooter;
//import org.firstinspires.ftc.teamcode.mechanisms.Limelight;
import org.firstinspires.ftc.teamcode.pedro.Constants;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;

@Autonomous
public class RedAllianceAuto2 extends OpMode {
    Intake intake = new Intake();
    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();

    //put Poses here
    //private final Pose start = p.of(0,0,0);
    //private final Pose stop1 = p.of(0,0,0);
    //private final Pose stop2 = p.of(0,0,0);

    //put Paths here
    // private Path path1() {
        //return line(start, stop1).linear(start, stop1);
    //}

    //put Curves here (If you want)

    //control point
    //private final Pose control1 = p.of(0,0,0);

    //actual curve
    //private Path curve2() {
        //return curve(stop1, control, stop2).linear(stop1, stop2);
    //}

    //put Commands here
    private Command autoRoutine() {
        return sequential(
                //follow(follower, path1()),
                //follow(follower, curve2())
        );
    }

    @Override
    public void init() {
        Scheduler.reset();

        follower = Constants.create(hardwareMap);
        //follower.setPose(idkPose);
        follower.update();

        intake.init(hardwareMap);
    }

    @Override
    public void start() {
        schedule(autoRoutine());
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();

        //shooter
        //limelight
        //Intake
        //if (follower.currentPath() == path1())  {
            //intake.runIntake();
        //} else if (follower.currentPath() == curve2()) {
            //intake.runOuttake();
        //} else {
            //intake.stopIntake();
        //}
        //transfer

        //telemetry that is basic
        //telemetry.addData("X", follower.pose().x());
        //telemetry.addData("Y", follower.pose().y());
        //telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        //telemetry.addData("Follower Mode", follower.mode());
        //telemetry.update();
    }
}
