package org.firstinspires.ftc.teamcode.transfer;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "Square Auto", group = "Examples")
public class SquareAuto extends OpMode {

    private Follower follower;
    private Timer pathTimer, opmodeTimer;
    private int pathState;

    /* ==================== FIELD POSES ==================== */
// 24-inch square, starting at (0, 0) facing 0 degrees
    private final Pose startPose = new Pose(0, 0, Math.toRadians(0));
    private final Pose corner1 = new Pose(24, 0, Math.toRadians(0));
    private final Pose corner2 = new Pose(24, 24, Math.toRadians(0));
    private final Pose corner3 = new Pose(0, 24, Math.toRadians(0));

    /* ==================== PATHS ==================== */
    private Path side1, side2, side3, side4;

    /* ==================== BUILD PATHS ==================== */
    public void buildPaths() {
// Side 1: Start → Corner 1 (forward)
        side1 = new Path(new BezierLine(startPose, corner1));
        side1.setConstantHeadingInterpolation(Math.toRadians(0));

// Side 2: Corner 1 → Corner 2 (strafe left)
        side2 = new Path(new BezierLine(corner1, corner2));
        side2.setConstantHeadingInterpolation(Math.toRadians(0));

// Side 3: Corner 2 → Corner 3 (backward)
        side3 = new Path(new BezierLine(corner2, corner3));
        side3.setConstantHeadingInterpolation(Math.toRadians(0));

// Side 4: Corner 3 → Start (strafe right, back to origin)
        side4 = new Path(new BezierLine(corner3, startPose));
        side4.setConstantHeadingInterpolation(Math.toRadians(0));
    }

    /* ==================== FSM ==================== */
    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0: // Start side 1
                follower.followPath(side1);
                setPathState(1);
                break;

            case 1: // Wait for side 1, then side 2
                if (!follower.isBusy()) {
                    follower.followPath(side2);
                    setPathState(2);
                }
                break;

            case 2: // Wait for side 2, then side 3
                if (!follower.isBusy()) {
                    follower.followPath(side3);
                    setPathState(3);
                }
                break;

            case 3: // Wait for side 3, then side 4
                if (!follower.isBusy()) {
                    follower.followPath(side4);
                    setPathState(4);
                }
                break;

            case 4: // Done
                if (!follower.isBusy()) {
                    setPathState(-1);
                }
                break;
        }
    }

    public void setPathState(int newState) {
        pathState = newState;
        pathTimer.resetTimer();
    }

    /* ==================== LIFECYCLE ==================== */
    @Override
    public void init() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();

        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setStartingPose(startPose);

        telemetry.addLine("Square Auto Ready!");
        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.update();
    }
/*
    @Override
    public void start() {
        opmodeTimer.resetTimer();
        setPathState(0);
    }

 */

    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();

        telemetry.addData("Path State", pathState);
        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading", Math.toDegrees(follower.getPose().getHeading()));

        //telemetry.addData("x:", follower.getPose().getX());
        //telemetry.addData("y:", follower.getPose().getY());
        telemetry.update();
    }

    @Override
    public void start() { opmodeTimer.resetTimer(); setPathState(0); }

    @Override
    public void stop() {}
}


