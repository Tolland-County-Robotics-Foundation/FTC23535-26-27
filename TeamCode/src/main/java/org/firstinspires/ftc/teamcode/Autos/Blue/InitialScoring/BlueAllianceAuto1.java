package org.firstinspires.ftc.teamcode.Autos.Blue.InitialScoring;

import com.pedropathing.api.Paths;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
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
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous
public class BlueAllianceAuto1 extends OpMode {
    Intake intake = new Intake();
    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();

    private final Pose start = p.of(82.5, 133, 90);
    private final Pose path1 = p.of(82.4337, 105, 270);
    private final Pose point2Start = p.of(82.4337, 105, 30);
    private final Pose point2 = p.of(133.0765, 129, 90);
    private final Pose point3 = p.of(82.5827, 34.651, 90);
    private final Pose point3Control1 = p.of(90.6041, 108.9418, 0);
    private final Pose point3Control2 = p.of(141.8031, 14.9724, 0);
    private final Pose point4Start = p.of(82.5827, 34.651, 180);
    private final Pose point4 = p.of(96.1561, 11.3143, 270);
    private final Pose point5 = p.of(128.8245, 43.9673, 44.9866);

    public Path path1() {
        return Paths.line(start, path1).constant(path1);
    }

    public Path path2() {
        return Paths.line(point2Start, point2).linear(point2Start, point2);
    }

    public Path path3() {
        return Paths.curve(point2, point3Control1, point3Control2, point3).linear(point2, point3);
    }

    public Path path4() {
        return Paths.line(point4Start, point4).linear(point4Start, point4);
    }

    public Path path5() {
        return Paths.line(point4, point5).tangent();
    }

    //put Commands here
    private Command autoRoutine() {
        return sequential(
                follow(follower, path1()),
                waitMs(3000),
                follow(follower, path2()),
                waitMs(2000),
                follow(follower, path3()),
                waitMs(3000),
                follow(follower, path4()),
                waitMs(5000),
                follow(follower, path5())
        );
    }

    @Override
    public void init() {
        Scheduler.reset();

        follower = Constants.create(hardwareMap);
        follower.setPose(start);
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
        if (follower.currentPath() == path2() && !follower.isBusy())  {
            intake.runIntake();
        } else if (follower.currentPath() == path4() && !follower.isBusy()) {
            intake.runIntake();
        } else {
            intake.stopIntake();
        }
        //transfer

        //telemetry
        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());
        telemetry.update();
    }
}