package org.firstinspires.ftc.teamcode.subsytems;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake {
    private DcMotor intakeboi;

    public Intake(DcMotor intakeboi){
        this.intakeboi = intakeboi;
    }

    public void intake(double speed){
        intakeboi.setPower(speed);
    }

    public void outtake(double speed){
        intakeboi.setPower(speed);
    }
}
