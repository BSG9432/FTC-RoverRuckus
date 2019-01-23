package org.firstinspires.ftc.teamcode.testing;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "armtest")
@Disabled
public class armtest extends OpMode {
    public DcMotor frontRight;
    public DcMotor backRight;
    public DcMotor frontLeft;
    public DcMotor backLeft;

    //public Servo hangyboi;
    public DcMotor hangLift;

    public DcMotor pivot;
    public DcMotor intakeLift;
    // public DcMotor intake;


    // public Servo teamMarker;


    public void init() {

        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // hangyboi = hardwareMap.servo.get("hangyboi");
        hangLift = hardwareMap.dcMotor.get("hangLift");
        hangLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pivot = hardwareMap.dcMotor.get("pivot");
        pivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeLift = hardwareMap.dcMotor.get("intakeLift");
        intakeLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // intake = hardwareMap.dcMotor.get("intake");
        // intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // teamMarker = hardwareMap.servo.get("teamMarker");


    }

    @Override
    public void loop() {
    if(gamepad1.a){
        pivot.setPower(1);
    }
    else if(gamepad1.y){
        pivot.setPower(-1);
    }
    else {
        pivot.setPower(0);
    }

    }
}
