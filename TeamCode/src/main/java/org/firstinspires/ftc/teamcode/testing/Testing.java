package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Testing")
public class Testing extends OpMode{
    public DcMotor topRight;
    public DcMotor backRight;
    public DcMotor topLeft;
    public DcMotor backLeft;


    public DcMotor lift;
    public Servo phoneServo;

    @Override
    public void init() {
        topRight = hardwareMap.dcMotor.get ("topRight");
        backRight = hardwareMap.dcMotor.get ("backRight");
        topLeft = hardwareMap.dcMotor.get ("topLeft");
        backLeft = hardwareMap.dcMotor.get ("backLeft");


        lift = hardwareMap.dcMotor.get ("lift");
        phoneServo = hardwareMap.servo.get ("phoneServo");
    }

    @Override
    public void loop() {

        }

       // telemetry.addData("Servo Value: ", teamMarker.getPosition() );
        }



