package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "tylaop", group = "kms")

public class tylaop extends OpMode {

    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;
    public DcMotor lift;
    public Servo   phoneServo;
    public Servo   hangyboi;


    @Override
    public void init() {

        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");

        lift = hardwareMap.dcMotor.get("lift");
        phoneServo = hardwareMap.servo.get("phoneServo");
        hangyboi = hardwareMap.servo.get("hangyboi");


    }

    @Override
    public void loop() {
        //Right Side
        if (Math.abs(gamepad1.right_stick_y) > .1){
            frontRight.setPower(-gamepad1.right_stick_y);
            backRight.setPower(-gamepad1.right_stick_y);
        }
        else {
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        //Left Side
        if (Math.abs(gamepad1.left_stick_y) > .1){
            frontLeft.setPower(gamepad1.left_stick_y);
            backLeft.setPower(gamepad1.left_stick_y);
        }
        else {
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }
        //lift
        if(gamepad1.a){
            lift.setPower(-.3);
        }
        else if(gamepad1.y){
            lift.setPower(.3);
        }
        else{
            lift.setPower(0);
        }
        //hangyboi
        if(gamepad1.x){
            hangyboi.setPosition(.7); //latch
        }
        else if(gamepad1.b){
            hangyboi.setPosition(.1);//unlatch
        }
    }
}
