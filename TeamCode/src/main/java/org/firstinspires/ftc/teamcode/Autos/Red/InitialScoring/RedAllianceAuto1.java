package org.firstinspires.ftc.teamcode.Autos.Red.InitialScoring;

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
public class RedAllianceAuto1 extends OpMode {
    Intake intake = new Intake();
    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(57.7327, 8, 90);
    private final Pose path1 = poseFactory.of(58, 40, 90);
    private final Pose point2Start = poseFactory.of(58, 40, 200);
    private final Pose point2 = poseFactory.of(12.3745, 13.6633, 250);
    private final Pose point3Start = poseFactory.of(12.3745, 13.6633, 60);
    private final Pose point3 = poseFactory.of(58.5776, 106.1296, 270);
    private final Pose point3Control1 = poseFactory.of(20.8551, 118.349, 0);
    private final Pose point4Start = poseFactory.of(58.5776, 106.1296, 130);
    private final Pose point4 = poseFactory.of(51.5837, 127.1245, 120);
    private final Pose point5Start = poseFactory.of(51.5837, 127.1245, 210);
    private final Pose point5 = poseFactory.of(14.501, 117.2684, 260);

    public Path path1() {
        return Paths.line(start, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.line(point2Start, point2).linear(point2Start, point2);
    }

    public Path path3() {
        return Paths.curve(point3Start, point3Control1, point3).linear(point3Start, point3);
    }

    public Path path4() {
        return Paths.line(point4Start, point4).linear(point4Start, point4);
    }

    public Path path5() {
        return Paths.line(point5Start, point5).linear(point5Start, point5);
    }

    // Autonomous routine
    public Command autoRoutine() {
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
