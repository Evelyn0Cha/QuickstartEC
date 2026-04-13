package org.firstinspires.ftc.teamcode.transfer;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
public class PracticeAuto extends OpMode {

    private Follower follower;
    private int pathState;

    //poses
    private final Pose startPose = new Pose(56,8, Math.toRadians(90));

    private final Pose setIntake1 = new Pose(36, 35, Math.toRadians(180));
    private final Pose intake1 = new Pose(8, 35, Math.toRadians(180));

    private final Pose control1 = new Pose(53.5, 44);
    private final Pose setIntake2 = new Pose(38, 58.5, Math.toRadians(180));
    private final Pose intake2 = new Pose(8, 58.5, Math.toRadians(180));
    private final Pose control2 = new Pose(56.5, 77);
    private final Pose setIntake3 = new Pose(39, 83, Math.toRadians(180));
    private final Pose intake3 = new Pose(14, 83, Math.toRadians(180));


    private final Pose launchPose = new Pose(38.5, 108, Math.toRadians(140));
    private final Pose endPose = new Pose(54, 81, Math.toRadians(180));


    //paths
    //private Path path;
    private PathChain moveOne;
    private PathChain moveTwo;
    private PathChain moveThree;
    private PathChain moveFour;
    private PathChain moveFive;
    private PathChain moveSix;
    private PathChain moveSeven;
    private PathChain moveEight;

    //build paths
    public void buildPaths(){
        moveOne = follower.pathBuilder()
                .addPath(new BezierLine(startPose, setIntake1))
                .setLinearHeadingInterpolation(startPose.getHeading(), setIntake1.getHeading())
                .build();
        moveTwo = follower.pathBuilder()
                .addPath(new BezierLine(setIntake1, intake1))
                .setLinearHeadingInterpolation(setIntake1.getHeading(), intake1.getHeading())
                .build();
        moveThree = follower.pathBuilder()
                .addPath(new BezierCurve(intake1, launchPose))
                .setLinearHeadingInterpolation(intake1.getHeading(), launchPose.getHeading())
                .build();
    }

    public void pathUpdate() {

        switch(pathState) {
            case 0:
                follower.followPath(moveOne);
                setPathState(-1);
                break;
        }


        if(pathState == 0) {
            follower.followPath(moveOne);
            setPathState(-1);
        }
    }

    private void setPathState(int state) {
        pathState = state;
    }

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startPose);
        buildPaths();
    }

    @Override
    public void start() {
        setPathState(0);
    }

    @Override
    public void loop() {
        follower.update();
        pathUpdate();
    }
}
