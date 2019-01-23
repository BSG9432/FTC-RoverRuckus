package org.firstinspires.ftc.teamcode.testing;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
@Autonomous(name = "Adv DoegeCV")
@Disabled

public class aDvAnCEdDoGeCV extends LinearOpMode {
    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;
    public DcMotor lift;
    public Servo hangyboi;
    BNO055IMU imu;
    Orientation lastAngles = new Orientation();
    double globalAngle;
    double correction;
    boolean aButton, bButton, touched;

    public GoldAlignDetector detector;
    @Override
    public void runOpMode() throws InterruptedException {
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");

        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");

        lift = hardwareMap.dcMotor.get("lift");
        hangyboi = hardwareMap.servo.get("hangyboi");
        //frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        // Send telemetry message to signify robot waiting
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                backLeft.getCurrentPosition(),
                backRight.getCurrentPosition(),
                frontRight.getCurrentPosition(),
                frontLeft.getCurrentPosition());
        telemetry.update();

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);

        telemetry.addData("Mode", "calibrating...");
        telemetry.update();

        // make sure the imu gyro is calibrated before continuing.
        while (!isStopRequested() && !imu.isGyroCalibrated())
        {
            sleep(50);
            idle();
        }

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu calib status", imu.getCalibrationStatus().toString());
        telemetry.update();



        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();
        detector.enable();
        telemetry.addLine("PLEASE WORK");
        telemetry.update();

        //AutoTransitioner.transitionOnStop(this, "tylaop");

        waitForStart();
        int encoderPos = frontRight.getCurrentPosition() & frontLeft.getCurrentPosition();
        while(!detector.getAligned()){
            encoderDrive(.3, 0,48, 5);
            telemetry.addData("encoderPosJEt", encoderPos);
            if(detector.getAligned() == true){
                telemetry.addLine("Aligned!");
                telemetry.update();
            }
            telemetry.update();
        }
        encoderDrive(.7, 5, 5, 10);

        /*
        boolean endReached = false;
        ElapsedTime endTimer = new ElapsedTime();
        double endTimeLimit = 6.0; //in seconds
        while (!detector.getAligned() && !endReached && opModeIsActive()) {
            telemetry.addLine("TURNING...");
            telemetry.update();
            frontLeft.setPower(0);
            frontRight.setPower(0.3);
            backLeft.setPower(0);
            backRight.setPower(0.3);

            if (endTimer.time() >= endTimeLimit) {
                endReached = true;
            }
            if (detector.getAligned() == true) {
                telemetry.addLine("ALIGNED!!!");
                telemetry.update();
            }
            telemetry.update();
        }

        frontLeft.setPower(-1.0);
        frontRight.setPower(1.0);
        backLeft.setPower(-1.0);
        backRight.setPower(1.0);

        sleep(3000);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        detector.disable();
    */
    }
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller
            newLeftTarget = frontLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = backRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

            frontLeft.setTargetPosition(newLeftTarget);
            backLeft.setTargetPosition(newLeftTarget);
            backRight.setTargetPosition(newRightTarget);
            frontRight.setTargetPosition(newRightTarget);
            // Turn On RUN_TO_POSITION
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();
            frontRight.setPower(Math.abs(speed));
            backRight.setPower(Math.abs(speed));
            frontLeft.setPower(Math.abs(speed));
            backLeft.setPower(Math.abs(speed));
            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontLeft.isBusy() && backLeft.isBusy()) & backRight.isBusy() & frontRight.isBusy()) {
                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        frontRight.getCurrentPosition(),
                        backRight.getCurrentPosition(),
                        frontLeft.getCurrentPosition(),
                        backLeft.getCurrentPosition());
                telemetry.update();
            }
            // Stop all motion;
            frontLeft.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
            // Turn off RUN_TO_POSITION
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}

