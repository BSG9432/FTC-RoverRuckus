package org.firstinspires.ftc.teamcode.testing.Encoders;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "LiftDownWithEncoders")
@Disabled

public class LiftDownWithEncoders extends LinearOpMode{
    public DcMotor frontRight;
    public DcMotor backRight;
    public DcMotor frontLeft;
    public DcMotor backLeft;

    //public Servo hangyboi;
    public DcMotor hangLift;

    public DcMotor pivot;
    public DcMotor intakeLift;
    //Variables

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;//TorqueNADO
    private ElapsedTime runtime = new ElapsedTime();




    @Override
    public void runOpMode() throws InterruptedException {
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

        //Encoder Stuff
        hangLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        hangLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void liftRevolutions(int revolutions, double speed, double timeoutS){
        double targetPosition;
        int newTarget;
       targetPosition = revolutions * COUNTS_PER_MOTOR_REV;
        newTarget = hangLift.getCurrentPosition() + (int)targetPosition;
        hangLift.setTargetPosition(newTarget);
        hangLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        runtime.reset();
        hangLift.setPower(speed);

        while(runtime.seconds() < timeoutS && hangLift.isBusy()){
            telemetry.addData("Current Position: " , hangLift.getCurrentPosition());
            telemetry.update();
        }
        //Stop all motion
        hangLift.setPower(0);

        hangLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);






    }
}
