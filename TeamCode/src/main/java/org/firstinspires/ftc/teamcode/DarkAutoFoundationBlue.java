package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="✡ Dark Auto Foundation Blue", group="working")
public class DarkAutoFoundationBlue extends SuperDark {

    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {

        //Drive forward to the foundation
        drive.driveDistance(DeadWheels.forward, -68.0f, 0.7, 2.25f);
        foundServo.setPosition(1);
        sleep(1000);

        //drives backwards (the robot is facing backwards) for a second
        drive.goForwards(0.5);
        sleep(1000);
        drive.stopAll();

        //turns to put the foundation into its position
        drive.newTurnTo(90,1, 8);

        foundServo.setPosition(0.17); //let go of the foundation
        //wiggle to let go of the servo
        drive.newTurnTo(-90 ,1, 2);
        //turn around before parking
        sleep(1000);
        //double check that we turned correctly
        drive.newTurnTo(-90, 1, 8);

        //push foundation further into the building zone
        drive.driveDistance(DeadWheels.sideways, -10, 1, 2);
        drive.driveDistance(DeadWheels.forward, 25, 1, 3);
        //correct orientation once more, and park
        drive.newTurnTo(-90, 1, 8);
        drive.driveDistance(DeadWheels.forward, -80, 1, 5);
        drive.driveDistance(DeadWheels.sideways, 15, 0.5, 2);
        stop();

    }
}