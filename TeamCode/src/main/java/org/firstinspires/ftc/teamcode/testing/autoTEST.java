package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "autoTEST")
public class autoTEST extends LinearOpMode {
 Robot robot = new Robot(); //calls the class

    @Override
    public void runOpMode() throws InterruptedException {
       robot.init(hardwareMap);//initializes the hardwareMap declared in the 'Robot' file

        waitForStart();

        robot.goForward(.5);//goForward function, goes forward at half speed for 5 seconds
        sleep(5000);
        robot.goBack(.5); //goBack function, goes back at half speed (-.5) for 5 seconds
        sleep(5000);
        robot.stop(); //stop function, stops all movement in the robot
    }
}
