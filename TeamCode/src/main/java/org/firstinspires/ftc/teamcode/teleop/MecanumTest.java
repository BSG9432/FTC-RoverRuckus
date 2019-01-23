package org.firstinspires.ftc.teamcode.teleop;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "FASTBOIv4", group = "working")
public class MecanumTest extends OpMode {
    public DcMotor frontRight;
    public DcMotor backRight;
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor intakeLift;
    public DcMotor pivot;
    public DcMotor hangLift;
    public Servo servoboi;
    public void init() {

        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        intakeLift = hardwareMap.dcMotor.get("intakeLift");
        pivot = hardwareMap.dcMotor.get("pivot");
        hangLift = hardwareMap.dcMotor.get("hangLift");
        servoboi = hardwareMap.servo.get("servoboi");
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




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
            frontRight.setPower(gamepad1.right_stick_y/.7);
            backRight.setPower(gamepad1.right_stick_y/.7);
        } else {
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        //Left Side
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            frontLeft.setPower(-gamepad1.left_stick_y/.7);
            backLeft.setPower(-gamepad1.left_stick_y/.7);
        } else {
            frontLeft.setPower(0);
            backLeft.setPower(0);

        }


/*

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
            frontRight.setPower(gamepad2.right_stick_y);
            backRight.setPower(gamepad2.right_stick_y);
        }
        else {
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        //um half power for left side
        if (Math.abs(gamepad2.left_stick_y) > .1) {
            frontLeft.setPower(-gamepad2.left_stick_y);
            backLeft.setPower(-gamepad2.left_stick_y);
        }
        else {
            frontLeft.setPower(0);
            backLeft.setPower(0);

        }
        */
        if(gamepad1.dpad_up) {  //YEET UP
            hangLift.setPower(1);
        }
        else if(gamepad1.dpad_down){  //YEET DOWN
            hangLift.setPower(-1);
        }
        else{
            hangLift.setPower(0);
        }

        if(gamepad2.right_trigger > .1){ // GO FORWARD
            intakeLift.setPower(.5);

        }
        else if(gamepad2.left_trigger > .1){ // GO BACK
            intakeLift.setPower(-.5);
        }
        else {
            intakeLift.setPower(0);
        }
        if(gamepad2.x){  //out
            servoboi.setPosition(0);
            telemetry.addData("servoPos: ", servoboi.getPosition());
            telemetry.update();
        }
        else if(gamepad2.b){ // IN
            servoboi.setPosition(1);
            telemetry.addData("servoPos: ", servoboi.getPosition());
            telemetry.update();
        }
        else {
            servoboi.setPosition(0);
        }




        if(gamepad2.y){
            pivot.setPower(.8);
        }
        else if(gamepad2.a){
            pivot.setPower(-.5);
        }
        else{
            pivot.setPower(0);
        }

    }

}
