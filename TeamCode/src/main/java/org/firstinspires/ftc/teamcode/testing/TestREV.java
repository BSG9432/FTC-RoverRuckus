package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "REV_Test")
@Disabled
public class TestREV extends OpMode {
    DcMotor left;
    DcMotor right;
    @Override
    public void init() {
        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");
    }

    @Override
    public void loop() {
        if(gamepad1.y){
            left.setPower(.5);
            right.setPower(.5);
        }
        else if (gamepad1.a){
            left.setPower(-.5);
            right.setPower(-.5);
        }
        else {
            left.setPower(0);
            right.setPower(0);
        }

    }
}
