import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class Robot {
    //declare motors
    public DcMotor left, right, lift;
    //declare servos
    public Servo teamMarker;
    //declare sensors
    public ColorSensor sensorRGB;
    //declare HardwareMap variable
    private HardwareMap hMap;

    public Robot (){

    }

    public void init(HardwareMap map) {
        hMap = map;

        left = hMap.dcMotor.get("left");
        right = hMap.dcMotor.get("right");
        lift = hMap.dcMotor.get("lift");

        teamMarker = hMap.servo.get("teamMarker");

        sensorRGB = hMap.colorSensor.get("sensorRGB");


        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        //Just safe
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
   // public void
}
