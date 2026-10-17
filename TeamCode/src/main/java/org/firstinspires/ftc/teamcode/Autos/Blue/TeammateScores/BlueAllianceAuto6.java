package org.firstinspires.ftc.teamcode.Autos.Blue.TeammateScores;

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
public class BlueAllianceAuto6 extends OpMode {
    Intake intake = new Intake();
    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(80, 8.8, 90);
    private final Pose path1 = poseFactory.of(82.5, 35, 90);
    private final Pose point2Start = poseFactory.of(82.5, 35, 100);
    private final Pose point2 = poseFactory.of(96.2357, 12.4163, 270);
    private final Pose point3 = poseFactory.of(83.0745, 34.2673, 90);
    private final Pose point4 = poseFactory.of(129.301, 26.4745, -9.5689);

    public Path path1() {
        return Paths.line(start, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.line(point2Start, point2).linear(point2Start, point2);
    }

    public Path path3() {
        return Paths.line(point2, point3).linear(point2, point3);
    }

    public Path path4() {
        return Paths.line(point3, point4).tangent();
    }

    //put Commands here
    private Command autoRoutine() {
        return sequential(
                follow(follower, path1()),
                waitMs(12000),
                follow(follower, path2()),
                waitMs(5000),
                follow(follower, path3()),
                waitMs(3000),
                follow(follower, path4())
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

        //intake
        if (follower.currentPath() == path2() && !follower.isBusy()) {
            intake.runIntake();
        } else {
            intake.stopIntake();
        }

        //transfer
        //shooter
        //limelight

        //telemetry
        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());
        telemetry.update();
    }
}