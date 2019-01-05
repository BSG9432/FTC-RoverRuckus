package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptTelemetry;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Locale;


public class Robot extends ConceptTelemetry {
    //declare motors
    public DcMotor topRight;
    public DcMotor topLeft;
    public DcMotor backRight;
    public DcMotor backLeft;
    public DcMotor teamMarker;
    //declare servos
   // public Servo teamMarkerDump;
    //declare sensors
    public ColorSensor sensorRGB;
    public BNO055IMU  imu;
    public Orientation angles;
    public Acceleration gravity;
    //declare HardwareMap variable
    public HardwareMap hMap;

    public Robot (){ //constructor

    }

    public void init(HardwareMap map) {
        //create hardware map var
        hMap = map;

        //declare motors to hardwareMap
        topRight = hMap.dcMotor.get("topRight");
        topLeft = hMap.dcMotor.get("topLeft");
        backLeft = hMap.dcMotor.get("backLeft");
        backRight = hMap.dcMotor.get("backRight");
        teamMarker = hMap.dcMotor.get("teamMarker");

        //declare servos to hardwareMap
       // teamMarkerDump = hMap.servo.get("teamMarkerDump");

        //declare sensors to hardwareMap
        sensorRGB = hMap.colorSensor.get("sensorRGB");
        imu = hardwareMap.get(BNO055IMU.class, "imu"); //idk y


        topRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        topLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        topLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        //Just safe, REV sets ZeroPowerBehavior to FLOAT automatically
        topRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        topLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        topLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //left must be flipped (left must be a negative value while right is a positive value when trying to move forward)
        topLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //to be safe, set right side to FORWARD
        topRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    //Function to go forward when called
    public void goForward(double power){
        topLeft.setPower(power);
        topRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }
    //Function to go back (reverse),
    public void goBack(double power){
        topLeft.setPower(-power);
        topRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(-power);
    }

    public void stopMovement(){
        topLeft.setPower(0);
        topRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void turnRight(){
    }

    public void turnLeft(){

    }
        //Copied from provided SensorBNO055IMU example
    void composeTelemetry() {

        // At the beginning of each telemetry update, grab a bunch of data
        // from the IMU that we will then display in separate lines.
        telemetry.addAction(new Runnable() { @Override public void run()
        {
            // Acquiring the angles is relatively expensive; we don't want
            // to do that in each of the three items that need that info, as that's
            // three times the necessary expense.
            angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            gravity  = imu.getGravity();
        }
        });

        telemetry.addLine()
                .addData("status", new Func<String>() {
                    @Override public String value() {
                        return imu.getSystemStatus().toShortString();
                    }
                })
                .addData("calib", new Func<String>() {
                    @Override public String value() {
                        return imu.getCalibrationStatus().toString();
                    }
                });

        telemetry.addLine()
                .addData("heading", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.firstAngle);
                    }
                })
                .addData("roll", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.secondAngle);
                    }
                })
                .addData("pitch", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.thirdAngle);
                    }
                });

        telemetry.addLine()
                .addData("grvty", new Func<String>() {
                    @Override public String value() {
                        return gravity.toString();
                    }
                })
                .addData("mag", new Func<String>() {
                    @Override public String value() {
                        return String.format(Locale.getDefault(), "%.3f",
                                Math.sqrt(gravity.xAccel*gravity.xAccel
                                        + gravity.yAccel*gravity.yAccel
                                        + gravity.zAccel*gravity.zAccel));
                    }
                });
    }

    //----------------------------------------------------------------------------------------------
    // Formatting
    //----------------------------------------------------------------------------------------------

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}



