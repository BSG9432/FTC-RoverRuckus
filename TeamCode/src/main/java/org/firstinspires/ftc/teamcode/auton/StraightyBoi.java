package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutoTransitioner;

@Autonomous (name = "Straight")//If we're on the crater side; it'll just go straight
@Disabled
public class StraightyBoi extends LinearOpMode {
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;
    public DcMotor lift;
    public Servo   phoneServo;
    public Servo   hangyboi;

    @Override
    public void runOpMode() throws InterruptedException {
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        lift = hardwareMap.dcMotor.get ("lift");
        phoneServo = hardwareMap.servo.get ("phoneServo");
        hangyboi = hardwareMap.servo.get ("hangyboi");

        AutoTransitioner.transitionOnStop(this, "tylaop");
        waitForStart();
        frontRight.setPower(.5);
        backRight.setPower(.5);
        frontLeft.setPower(-.5);
        backLeft.setPower(-.5);
        sleep(5000);

    }
}