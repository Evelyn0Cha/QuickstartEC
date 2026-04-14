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
    private final Pose startPose = new Pose(50,2, Math.toRadians(90));

    private final Pose setIntake1 = new Pose(36, 35, Math.toRadians(180));
    private final Pose intake1 = new Pose(-5, 35, Math.toRadians(180));

    private final Pose control1 = new Pose(60, 26);

    private final Pose setIntake2 = new Pose(43, 61.5, Math.toRadians(180));
    private final Pose intake2 = new Pose(-5, 61.5, Math.toRadians(180));

    private final Pose control2 = new Pose(74.5, 72);

    private final Pose gate = new Pose(-1, 69, Math.toRadians(180));

    private final Pose controlGate = new Pose(24, 59.5, Math.toRadians(180));

    private final Pose setIntake3 = new Pose(39, 83, Math.toRadians(180));
    private final Pose intake3 = new Pose(4, 83, Math.toRadians(180));

    private final Pose control3 = new Pose(60, 81);

    private final Pose launchPose = new Pose(40, 105, Math.toRadians(140)); // adjust later
    private final Pose endPose = new Pose(54, 81, Math.toRadians(180));



    //paths
    //private Path path;
    private PathChain moveOne;
    private PathChain moveTwo;
    private PathChain moveThree;
    private PathChain moveFour;
    private PathChain moveFive;
    private PathChain openGate;
    private PathChain moveSix;
    private PathChain moveSeven;
    private PathChain moveEight;
    private PathChain moveNine;
    private PathChain end;

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
                .addPath(new BezierCurve(intake1, control1, launchPose))
                .setLinearHeadingInterpolation(intake1.getHeading(), launchPose.getHeading())
                .build();
        moveFour = follower.pathBuilder()
                .addPath(new BezierLine(launchPose, setIntake2))
                .setLinearHeadingInterpolation(launchPose.getHeading(), setIntake2.getHeading())
                .build();
        moveFive = follower.pathBuilder()
                .addPath(new BezierLine(setIntake2, intake2))
                .setLinearHeadingInterpolation(setIntake2.getHeading(), intake2.getHeading())
                .build();
        openGate = follower.pathBuilder()
                .addPath(new BezierCurve(intake2, controlGate, gate))
                .setLinearHeadingInterpolation(intake2.getHeading(), gate.getHeading())
                .build();
        moveSix = follower.pathBuilder()
                .addPath(new BezierCurve(gate, control2, launchPose))
                .setLinearHeadingInterpolation(gate.getHeading(), launchPose.getHeading())
                .build();
        moveSeven = follower.pathBuilder()
                .addPath(new BezierCurve(launchPose, control3, setIntake3))
                .setLinearHeadingInterpolation(launchPose.getHeading(), setIntake3.getHeading())
                .build();
        moveEight = follower.pathBuilder()
                .addPath(new BezierLine(setIntake3, intake3))
                .setLinearHeadingInterpolation(setIntake3.getHeading(), intake3.getHeading())
                .build();
        moveNine = follower.pathBuilder()
                .addPath(new BezierLine(intake3, launchPose))
                .setLinearHeadingInterpolation(intake3.getHeading(), launchPose.getHeading())
                .build();
        end = follower.pathBuilder()
                .addPath(new BezierLine(launchPose, endPose))
                .setLinearHeadingInterpolation(launchPose.getHeading(), endPose.getHeading())
                .build();
    }

    public void pathUpdate() {

        follower.setMaxPower(0.5);
        switch(pathState) {
            case 0:
                follower.followPath(moveOne);
                setPathState(1);
                break;
            case 1:
                if(!follower.isBusy()) {
                    follower.followPath(moveTwo);
                    setPathState(2);
                    break;
                }
            case 2:
                if(!follower.isBusy()) {
                    follower.followPath(moveThree);
                    setPathState(3);
                    break;
                }
            case 3:
                if(!follower.isBusy()) {
                    follower.followPath(moveFour);
                    setPathState(4);
                    break;
                }
            case 4:
                if(!follower.isBusy()) {
                    follower.followPath(moveFive);
                    setPathState(5);
                    break;
                }
            case 5:
                if(!follower.isBusy()) {
                    follower.followPath(openGate);
                    setPathState(6);
                    break;
                }
            case 6:
                if(!follower.isBusy()) {
                    follower.followPath(moveSix);
                    setPathState(7);
                    break;
                }
            case 7:
                if(!follower.isBusy()) {
                    follower.followPath(moveSeven);
                    setPathState(8);
                    break;
                }
            case 8:
                if(!follower.isBusy()) {
                    follower.followPath(moveEight);
                    setPathState(9);
                    break;
                }
            case 9:
                if(!follower.isBusy()) {
                    follower.followPath(moveNine);
                    setPathState(10);
                    break;
                }
            case 10:
                if(!follower.isBusy()) {
                    follower.followPath(end);
                    setPathState(-1);
                    break;
                }
        }

/*
        if(pathState == 0) {
            follower.followPath(moveOne);
            setPathState(-1);
        }

 */
    }

    private void setPathState(int state) {
        pathState = state;
    }

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setStartingPose(startPose);

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

    @Override
    public void stop() {

    }
}
