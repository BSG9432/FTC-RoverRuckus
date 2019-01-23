package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Outreach TeleOp")
@Disabled
public class Outreach_TeleOp extends OpMode{
    DcMotor right1;
    DcMotor right2;
    DcMotor left1;
    DcMotor left2;
   // Servo dump;
  //  DcMotor intake;

    @Override
    public void init() {
        right1 = hardwareMap.dcMotor.get ("right1"); // Right Front
        right2 = hardwareMap.dcMotor.get ("right2"); // Right Back
        left1 = hardwareMap.dcMotor.get ("left1"); // Left Front
        left2 = hardwareMap.dcMotor.get ("left2"); //Left Back
     //   dump = hardwareMap.servo.get ("dump"); // For the team marker
       // intake = hardwareMap.dcMotor.get ("intake"); //For the intake


    }

    @Override
    public void loop() {


        if (Math.abs(gamepad1.right_stick_y) > .1) {
            right1.setPower(-gamepad1.right_stick_y);
            right2.setPower(-gamepad1.right_stick_y);
        }
        else {
            right1.setPower(0);
            right2.setPower(0);
        }
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            left1.setPower(gamepad1.left_stick_y);
            left2.setPower(gamepad1.left_stick_y);
        }
        else {
            left1.setPower(0);
            left2.setPower(0);
        }


    }
}
