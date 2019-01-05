package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutoTransitioner;

@Autonomous(name = "AutoBDepot")//Autonomous facing the blue depot
public class AutoBlueDepot extends LinearOpMode { //FACING BLUE DEPOT
    public DcMotor topRight;
    public DcMotor backRight;
    public DcMotor topLeft;
    public DcMotor backLeft;

    public DcMotor lift;

    public Servo phoneServo;


    @Override
    public void runOpMode() throws InterruptedException {

        topRight = hardwareMap.dcMotor.get("topRight");
        backRight = hardwareMap.dcMotor.get("backRight");
        topLeft = hardwareMap.dcMotor.get("topLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        topRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        topLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lift = hardwareMap.dcMotor.get ("lift");

        phoneServo = hardwareMap.servo.get ("phoneServo");

        AutoTransitioner.transitionOnStop(this, "Teleop");
        waitForStart();

        topRight.setPower(.5);  //straight for 1 seconds
        backRight.setPower(.5);
        topLeft.setPower(-.5);
        backLeft.setPower(-.5);
        sleep(1000);

        topRight.setPower(.5);  //point turn
        backRight.setPower(.5);
        topLeft.setPower(0);
        backLeft.setPower(0);
        sleep(1800);

        topRight.setPower(.5);  //straight for .75 seconds
        backRight.setPower(.5);
        topLeft.setPower(-.5);
        backLeft.setPower(-.5);
        sleep(750);

       /* teamMarker.setPower(-.5); //deposit team marker
        sleep(500);

        teamMarker.setPower(.5); //retract team marker
        sleep(500);*/
    }
}

