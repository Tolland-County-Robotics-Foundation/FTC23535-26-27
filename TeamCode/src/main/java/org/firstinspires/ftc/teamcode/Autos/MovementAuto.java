package org.firstinspires.ftc.teamcode.Autos;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.pedro.Constants;

import static com.pedropathing.api.Paths.*;

@Autonomous
public class MovementAuto extends OpMode {

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

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {

    }
}
