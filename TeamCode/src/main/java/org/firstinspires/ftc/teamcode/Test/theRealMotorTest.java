package org.firstinspires.ftc.teamcode.Test;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.telemetryM;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class theRealMotorTest extends OpMode {
    private DcMotorEx frontRight;
    private DcMotorEx frontLeft;
    private DcMotorEx backRight;
    private DcMotorEx backLeft;

    //private Telemetry telemetryA;
    private TelemetryManager telemetryM;



    @Override
    public void init() {
        frontRight = hardwareMap.get(DcMotorEx.class, "front_right_motor");
        frontLeft = hardwareMap.get(DcMotorEx.class, "front_left_motor");
        backRight = hardwareMap.get(DcMotorEx.class, "back_right_motor");
        backLeft = hardwareMap.get(DcMotorEx.class, "back_left_motor");

        frontRight.setDirection(DcMotorEx.Direction.FORWARD);
        frontLeft.setDirection(DcMotorEx.Direction.REVERSE);
        backRight.setDirection(DcMotorEx.Direction.FORWARD);
        backLeft.setDirection(DcMotorEx.Direction.REVERSE);
    }

    @Override
    public void loop() {



        if(gamepad1.a){
            frontLeft.setPower(1);
        }else{
            frontLeft.setPower(0);
        }
        if(gamepad1.y){
            backLeft.setPower(1);
        }else{
            backLeft.setPower(0);
        }
        if(gamepad1.b){
            frontRight.setPower(1);
        }else{
            frontRight.setPower(0);
        }
        if(gamepad1.x) {
            backRight.setPower(1);
        }else{
            backRight.setPower(0);
        }

        telemetry.addData("Path State", backLeft.getDirection());
        telemetry.update();

    }
}
