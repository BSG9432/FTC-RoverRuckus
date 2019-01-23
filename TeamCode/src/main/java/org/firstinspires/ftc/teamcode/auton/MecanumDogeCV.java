package org.firstinspires.ftc.teamcode.auton;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.AutoTransitioner;

@Autonomous (name = "JetMecanumDogeCV")
public class MecanumDogeCV extends LinearOpMode {
    public DcMotor frontRight;
    public DcMotor backRight;
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor intakeLift;
    public DcMotor pivot;
    public DcMotor hangLift;
    public Servo servoboi;






    public GoldAlignDetector detector;

    //note to add

    @Override
    public void runOpMode() throws InterruptedException {
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
//detector
        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();

        detector.enable();

        waitForStart();

        //If Gold Detector hasn't aligned yet, then rotate clockwise
        boolean endReached = false;
        ElapsedTime endTimer = new ElapsedTime();
        double endTimeLimit = 12.0;//in seconds
        while(!detector.getAligned() && !endReached && opModeIsActive()){
            frontLeft.setPower(.4/1.5);
            frontRight.setPower(.35/1.5);
            backLeft.setPower(.4/1.5);
            backRight.setPower(.35/1.5);
            if(endTimer.time() >= endTimeLimit){
                endReached = true;
            }
        }

        //forward for two seconds
        frontLeft.setPower(.3);
        frontRight.setPower(-.3);
        backLeft.setPower(.3);
        backRight.setPower(-.3);

        sleep(2000);

        //stop moving and disable detector
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        detector.disable();

        AutoTransitioner.transitionOnStop(this, "FASTBOIv4");


    }
}