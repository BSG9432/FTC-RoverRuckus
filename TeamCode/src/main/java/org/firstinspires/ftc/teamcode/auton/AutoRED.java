package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@Autonomous (name = "autoRED") //UM I'LL ASSUME THIS IS THE RIGHT ONE
public class AutoRED extends LinearOpMode {

    public DcMotor topRight;
    public DcMotor topLeft;
    public DcMotor backRight;
    public DcMotor backLeft;

    public Servo teamMarker;


    @Override
    public void runOpMode() throws InterruptedException {
        topRight = hardwareMap.dcMotor.get("topRight");
        topLeft = hardwareMap.dcMotor.get("topLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        topRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        topLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        teamMarker = hardwareMap.servo.get ("teamMarker");



// left servo port 0 middle port 1 right servo port 2

        waitForStart(); //Straight, turn 90 degrees, straight

        topRight.setPower(.25); //Straight for 2 Seconds
        backRight.setPower(.25);
        topLeft.setPower(-.25);
        backLeft.setPower(-.25);
        sleep(2000);

        topRight.setPower(-.5);  //Turn Clockwise for 1 second (90 degrees)
        backRight.setPower(-.5);
        topLeft.setPower(-.5);
        backLeft.setPower(-.5);
        sleep(1000);

        topRight.setPower(.5);  //Straight for 2.7 seconds
        backRight.setPower(.5);
        topLeft.setPower(-.5);
        backLeft.setPower(-.5);
        sleep(2700);

        topRight.setPower(0);  //In theory, the motors should stop
        backRight.setPower(0);
        topLeft.setPower(0);
        backLeft.setPower(0);

        teamMarker.setPosition(.6); //(hopefully) dump the team marker
        sleep(3000);
        teamMarker.setPosition(.3);
        sleep(3000);
    }
}