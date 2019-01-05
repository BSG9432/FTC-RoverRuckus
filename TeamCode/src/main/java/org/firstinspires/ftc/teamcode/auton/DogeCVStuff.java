package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "DogeCV Tests")

public class DogeCVStuff extends LinearOpMode {
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;
    public DcMotor lift;
    public Servo   phoneServo;
    public Servo   hangyboi;


    public GoldAlignDetector detector;

    @Override
    public void runOpMode() throws InterruptedException {
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        lift = hardwareMap.dcMotor.get("lift");
        phoneServo = hardwareMap.servo.get("phoneServo");
        hangyboi = hardwareMap.servo.get("hangyboi");

        //  frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //frontRight.setDirection(DcMotorSimple.Direction.REVERSE);


        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();

        detector.enable();

        waitForStart();

        boolean endReached = false;
        ElapsedTime endTimer = new ElapsedTime();
        double endTimeLimit = 4.0;//in seconds
        while(!detector.getAligned() && !endReached && opModeIsActive()){
            frontLeft.setPower(0);
            frontRight.setPower(0.2);
            backLeft.setPower(0);
            backRight.setPower(0.2);
            if(endTimer.time() >= endTimeLimit){
                endReached = true;
            }
        }

        frontLeft.setPower(-1.0);
        frontRight.setPower(1.0);
        backLeft.setPower(-1.0);
        backRight.setPower(1.0);

        sleep(2000);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        detector.disable();



      /*  teamMarker.setPower(-.5); //deposit team marker
        sleep(500);

        teamMarker.setPower(.5); //retract team marker
        sleep(500);
        */
    }
}
