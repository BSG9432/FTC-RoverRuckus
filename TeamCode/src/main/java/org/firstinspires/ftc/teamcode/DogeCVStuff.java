package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "DogeCV Tests")
public class DogeCVStuff extends LinearOpMode {
    //Declare Motors
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;
    public DcMotor teamMarker;

    //Declare detector
    public GoldAlignDetector detector;

    @Override
    public void runOpMode() throws InterruptedException {
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");

        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        teamMarker = hardwareMap.dcMotor.get("teamMarker");
        //  frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //Create instance of detector (object)
        detector = new GoldAlignDetector();
        //Use REAR Phone Camera
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();
        //Enable Camera
        detector.enable();

        waitForStart();
        boolean endReached = false;
        //Create Timer
        ElapsedTime endTimer = new ElapsedTime();
        //Create Time Goal, in seconds
        double endTimeLimit = 4.0;
        while(!detector.getAligned() && !endReached && opModeIsActive()){ //Align Code
            frontLeft.setPower(0.4);
            frontRight.setPower(0.4);
            backLeft.setPower(0.4);
            backRight.setPower(0.4);
            if(endTimer.time() >= endTimeLimit){
                endReached = true;
            }
        }

        //Drive Forward when Aligned
        frontLeft.setPower(1.0);
        frontRight.setPower(1.0);
        backLeft.setPower(-1.0);
        backRight.setPower(-1.0);

        sleep(2000);

        //Stop Movement, Disable Camera
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        detector.disable();
    }
}
