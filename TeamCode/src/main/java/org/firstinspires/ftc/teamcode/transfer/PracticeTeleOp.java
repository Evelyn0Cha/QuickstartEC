package org.firstinspires.ftc.teamcode.transfer;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@TeleOp
public class PracticeTeleOp extends OpMode {
    private Follower follower;
    public static Pose startingPose;
    private boolean slowMode = false;
    private double slowMultiplier = 0.7;

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.update();
    }

    @Override
    public void loop() {
        follower.update();

        if(gamepad1.aWasPressed()) {
            slowMode = true;
        }else{
            slowMode = false;
        }
        if(!slowMode) {
            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x,
                    false //is Robot Centric
            );
        }else{
            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y * slowMultiplier,
                    -gamepad1.left_stick_x * slowMultiplier,
                    -gamepad1.right_stick_x * slowMultiplier,
                    false //is Robot Centric
            );
        }
    }

    @Override
    public void start() {
        follower.startTeleopDrive();
    }
}
