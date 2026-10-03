package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.Encoder;
import com.pedropathing.revhub.localizers.ThreeWheelConfig;
import com.pedropathing.revhub.localizers.ThreeWheelIMUConfig;
import com.pedropathing.revhub.localizers.ThreeWheelLocalizer;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static MecanumConfig drivetrainConfig = new MecanumConfig(
        c -> {
            c.frontLeftName.set("lf");
            c.frontRightName.set("rf");
            c.backLeftName.set("lr");
            c.backRightName.set("rr");
            c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
            c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
            c.backLeftDirection.set(DcMotorSimple.Direction.FORWARD);
            c.backRightDirection.set(DcMotorSimple.Direction.REVERSE);

            c.manualBrakeMode.set(true);
    });
//ADD IN IMU STUFF, EVERYTHING ELSE GOOD
    //public static ThreeWheelIMUConfig localizerConfig = new ThreeWheelIMUConfig(
//c -> {
        //c.leftEncoderName.set("lf");
       // c.rightEncoderName.set("rr");
       // c.strafeEncoderName.set("lr");
       // c.leftPodY.set(2.750148391024103);
       // c.rightPodY.set(-2.653128447895786);
        //c.strafePodX.set(0.009182736482849);
       // c.forwardTicksToInches.set(0.0023439394940774254);
       // c.strafeTicksToInches.set(0.0019634242118254235);
       // c.turnTicksToRadians.set(0.0025276493394396473);
       // c.leftEncoderDirection.set(Encoder.REVERSE);
        //c.rightEncoderDirection.set(Encoder.REVERSE);
      //  c.strafeEncoderDirection.set(Encoder.FORWARD);
    //});

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.1575520001572371);
                Controller secondaryTranslationalForward = Controller.proportional(0.05821125419112068);
                Controller primaryTranslationalLateral = Controller.proportional(2.0462915935484816);
                Controller secondaryTranslationalLateral = Controller.proportional(0.7560500658977667);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.013793184854321515));
                c.brake.set(Controller.proportionalFeedforward(0.011724207126173287));

                c.headingFeedback.set(Controller.proportional(2.1870571101040723));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.09321604441219117, 0.0011971804287776982));

                c.linearBrakeCoefficients.set(Matrix.diag(0.12204230521446473, 0.051837122478923546));
                c.quadraticBrakeCoefficients.set(Matrix.diag(6.554272862598717E-4, 0.002660304914148627));

                c.maxAchievableForwardVelocity.set(73.02916603511935);
                c.maxAchievableStrafeVelocity.set(50.68046604741191);
                c.naturalForwardDeceleration.set(54.05103156326941);
                c.naturalStrafeDeceleration.set(121.33914443736036);
            }
    );

//WHEN FINISHED DELETE COMMENT MARKS
    //public static Follower create(HardwareMap hardwareMap) {
        // return new Follower(Drivetrain, Localizer, Foresight);
        //return new Follower(
        //        new ThreeWheelIMULocalizer(hardwareMap, localizerConfig),
          //      new Mecanum(hardwareMap, drivetrainConfig),
           //     new Foresight(foresightConfig)
        //);
    //}
}