package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class MecanumTest extends OpMode {
    public DcMotor frontRight;
    public DcMotor backRight;
    public DcMotor frontLeft;
    public DcMotor backLeft;



    @Override
    public void init() {

        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    @Override
    public void loop() {

        double G1rightStickY = gamepad1.right_stick_y;
        double G1leftStickY = -gamepad1.left_stick_y;
        boolean G1rightBumper = gamepad1.right_bumper;
        boolean G1leftBumper = gamepad1.left_bumper;

        //Strafing
        if (G1rightBumper) { //Right Bumper to go right
            frontRight.setPower(1);
            backRight.setPower(-1);
            frontLeft.setPower(1);
            backLeft.setPower(-1);
        } else if (G1leftBumper) { //Left Bumper to go left
            frontRight.setPower(-1);
            backRight.setPower(1);
            frontLeft.setPower(-1);
            backLeft.setPower(1);
        } else {
            frontRight.setPower(G1rightStickY);
            backRight.setPower(G1rightStickY);
            frontLeft.setPower(G1leftStickY);
            backLeft.setPower(G1leftStickY);
        }

        //Right Side
        if (Math.abs(gamepad1.right_stick_y) > .1) {
            frontRight.setPower(gamepad1.right_stick_y);
            backRight.setPower(gamepad1.right_stick_y);
        } else {
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        //Left Side
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            frontLeft.setPower(-gamepad1.left_stick_y);
            backLeft.setPower(-gamepad1.left_stick_y);
        } else {
            frontLeft.setPower(0);
            backLeft.setPower(0);

        }




        //Strafing
        if (gamepad2.right_bumper) { //Right Bumper to go right
            frontRight.setPower(.5);
            backRight.setPower(-.5);
            frontLeft.setPower(.5);
            backLeft.setPower(-.5);
        } else if (gamepad2.left_bumper) { //Left Bumper to go left
            frontRight.setPower(-.5);
            backRight.setPower(.5);
            frontLeft.setPower(-.5);
            backLeft.setPower(.5);
        } else {
            frontRight.setPower(0);
            backRight.setPower(0);
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }


        //um half power for right side
        if (Math.abs(gamepad2.right_stick_y) > .1 ) {
            frontRight.setPower(gamepad2.right_stick_y/2);
            backRight.setPower(gamepad2.right_stick_y/2);
        }
        else {
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        //um half power for left side
        if (Math.abs(gamepad2.left_stick_y) > .1) {
            frontLeft.setPower(-gamepad2.left_stick_y/2);
            backLeft.setPower(-gamepad2.left_stick_y/2);
        }
        else {
            frontLeft.setPower(0);
            backLeft.setPower(0);

        }
    }

}
