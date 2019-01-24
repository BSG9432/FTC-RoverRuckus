package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeleOp;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Player 1 - START")

public class OnePlayerTele extends OpMode {
    public DcMotor frontRight;
    public DcMotor backRight;
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor intakeLift;
    public DcMotor pivot;
    public DcMotor hangLift;
    public DcMotor intake;
    public Servo servoboi;

    public void init() {

        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        intakeLift = hardwareMap.dcMotor.get("intakeLift");
        pivot = hardwareMap.dcMotor.get("pivot");
        hangLift = hardwareMap.dcMotor.get("hangLift");
        intake = hardwareMap.dcMotor.get("intake");
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

        //BS STRAFE CODE BELOW
        //x axis to right -> 1
        //x axis to left -> -1
        //strafe right
        if(gamepad1.right_stick_x > .1){
            frontRight.setPower(gamepad1.right_stick_x);
            backRight.setPower(-gamepad1.right_stick_x);
            frontLeft.setPower(gamepad1.right_stick_x);
            backLeft.setPower(-gamepad1.right_stick_x);
        }
        //strafe left
        else if(gamepad1.right_stick_x < .1){
            frontRight.setPower(-gamepad1.right_stick_x);
            backRight.setPower(gamepad1.right_stick_x);
            frontLeft.setPower(-gamepad1.right_stick_x);
            backLeft.setPower(gamepad1.right_stick_x);
        }
        else {
            frontRight.setPower(G1rightStickY);
            backRight.setPower(G1rightStickY);
            frontLeft.setPower(G1leftStickY);
            backLeft.setPower(G1leftStickY);

        }

        //Right Side
        if (Math.abs(gamepad1.right_stick_y) > .1) {
            frontRight.setPower(gamepad1.right_stick_y/2);
            backRight.setPower(gamepad1.right_stick_y/2);
        } else {
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        //Left Side
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            frontLeft.setPower(-gamepad1.left_stick_y/2);
            backLeft.setPower(-gamepad1.left_stick_y/2);
        } else {
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }

        if(gamepad1.dpad_up) {  //YEET UP
            hangLift.setPower(1);
        }
        else if(gamepad1.dpad_down){  //YEET DOWN
            hangLift.setPower(-1);
        }
        else{
            hangLift.setPower(0);
        }

        if(gamepad1.right_trigger > .1){ // GO FORWARD
            intakeLift.setPower(.5);

        }
        else if(gamepad1.left_trigger > .1){ // GO BACK
            intakeLift.setPower(-.5);
        }
        else {
            intakeLift.setPower(0);
        }
        if(gamepad1.b){  //OUT
            servoboi.setPosition(0);
            telemetry.addData("servoPos: ", servoboi.getPosition());
            telemetry.update();
        }
        else {
            servoboi.setPosition(1);
        }//IN

        if(gamepad1.right_bumper){//UP
            pivot.setPower(.8);
        }
        else if(gamepad1.left_bumper){//DOWN
            pivot.setPower(-.5);
        }
        else{
            pivot.setPower(0);
        }
        if(gamepad1.y){//IN
            intake.setPower(.8);
        }
        else if(gamepad1.a){
            intake.setPower(-.8);
        }
        else{
            intake.setPower(0);
        }


}
}
