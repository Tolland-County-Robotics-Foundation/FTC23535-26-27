package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.pedro.Constants;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;

import static com.pedropathing.api.Paths.*;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous
public class RedAllianceAuto1 extends OpMode {

        Intake intake = new Intake();
    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();

    private final Pose start = p.of(8.5,8.5, 78.7);
    private final Pose stop1 = p.of(24,120,90);
    private final Pose stop2 = p.of(120,120,0);
    private final Pose control1 = p.of(120,24, 90);
    private final Pose park = p.of(24,24,180);

    private Path path1() {
        return line(start, stop1).linear(start, stop1);
    }
    private Path path2() {
        return line(stop1, stop2).linear(stop1, stop2);
    }
    private Path curvePark() {
        return curve(stop2, control1, park).linear(stop2, park);
    }

    private Command autoRoutine() {
        return sequential(
                follow(follower, path1()),
                follow(follower, path2()),
                follow(follower, curvePark())
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
        //intake

        //transfer

        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());
        telemetry.update();
    }
}
