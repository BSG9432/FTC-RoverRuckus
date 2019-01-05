package org.firstinspires.ftc.teamcode;

//import com.disnodeteam.dogecv.CameraViewDisplay;
//import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "Teleop")//THIS IS THE RIGHT TELE OP
public class TeleOp extends OpMode {
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;
    public DcMotor lift;
    public Servo   phoneServo;
    public Servo   hangyboi;



  //private GoldAlignDetector detector;

    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get ("frontLeft");
        backLeft = hardwareMap.dcMotor.get ("backLeft");
        frontRight = hardwareMap.dcMotor.get ("frontRight");
        backRight = hardwareMap.dcMotor.get ("backRight");
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lift = hardwareMap.dcMotor.get ("lift");
        phoneServo = hardwareMap.servo.get ("phoneServo");
        hangyboi = hardwareMap.servo.get ("hangyboi");
        //detector = new GoldAlignDetector();
        //detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());

        //detector.useDefaults();
        //detector.enable();

        //detector.getAligned();
    }

    @Override
    public void loop() {
        if (Math.abs(gamepad1.right_stick_y) > .1) {
            frontRight.setPower(-gamepad1.right_stick_y);
            backRight.setPower(-gamepad1.right_stick_y);
        } else {
            frontRight.setPower(0);
            backRight.setPower(0);
        }


        if (Math.abs(gamepad1.left_stick_y) > .1) {
            frontLeft.setPower(gamepad1.left_stick_y);
            backLeft.setPower(gamepad1.left_stick_y);
        } else {
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }




        }
    }
