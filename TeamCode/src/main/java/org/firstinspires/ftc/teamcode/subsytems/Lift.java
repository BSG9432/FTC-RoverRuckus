package org.firstinspires.ftc.teamcode.subsytems;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Lift {

    private DcMotor liftyboi, pivotboi;

    public Lift(DcMotor liftyboi, DcMotor pivotboi){
        this.liftyboi = liftyboi;
        this.pivotboi = pivotboi;
    }
     public void liftUp(double speed){
        liftyboi.setPower(speed);
     }
     public void pivot(double speed){
        pivotboi.setPower(speed);
     }


}
