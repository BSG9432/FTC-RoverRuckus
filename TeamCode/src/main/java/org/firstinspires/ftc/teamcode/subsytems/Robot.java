package org.firstinspires.ftc.teamcode.subsytems;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsTouchSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.subsytems.Intake;

import java.util.ArrayList;
import java.util.Locale;
public class Robot{
    public DcMotor frontRight, frontLeft, backRight, backLeft, intake;
    public DcMotor[] motors =  {frontRight, frontLeft, backRight, backLeft, intake};
    public String[] motorNames =  {"frontRight", "frontLeft", "backRight", "backLeft", "intake"};
    public GoldAlignDetector detector;
    public Telemetry telemetry;
    public HardwareMap hMap;
    public BNO055IMU imu;
    public Orientation angles;
    public Acceleration gravity;

   // public static Intake intakeSystem;

    //TODO: Modify COUNTS_PER_INCH to VEX mech wheels
    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // VEX WHEEL
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
   // private static final double TICKS_PER_INCH = 1120 * (16. / 24.) / (Math.PI * 4.0);
    private static final double COUNTS_PER_CM = COUNTS_PER_INCH / 2.54;
    private static final double ENCODER_DRIVE_POWER = 0.3;
    private ElapsedTime time = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS); //time in ms


    //Constructor
    public Robot(){

    }

    public void init(HardwareMap ahMap){
        hMap = ahMap;

        //cool but extra way to init motor names
        for(int i = 0; i < motors.length; i++) {
            motors[i] = hMap.dcMotor.get(motorNames[i]);
        }
        /*
        frontRight = hMap.dcMotor.get("frontRight");
        frontLeft = hMap.dcMotor.get("frontLeft");
        backRight = hMap.dcMotor.get("backRight");
        backLeft = hMap.dcMotor.get("backLeft");
        */
        detector = new GoldAlignDetector();
        detector.init(hMap.appContext, CameraViewDisplay.getInstance());
        //enable detector?
        //set direction (later)




    }

    public double getGravity (){
        gravity=imu.getGravity();
        return(gravity.zAccel);

    }

}