package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="✡ Dark Auto Foundation Red", group="working")
public class DarkAutoFoundationRed extends SuperDark {

    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {


        drive.driveDistance(DeadWheels.forward, -68.0f, 0.7, 2.25f);
        foundServo.setPosition(1);
        sleep(1000);


        //drive.strafeRight(0.70);
        //timer.restart();
        //strafes to the right while the foundation is still in front of us
        //while (opModeIsActive() && timer.check() < 5 && distance.cmBack() < 25);
        //sleep(325);
        //goes for a little bit of extra time
        drive.goForwards(0.5);
        sleep(1000);
        drive.stopAll();



        drive.newTurnTo(-90,1, 8);


        foundServo.setPosition(0.17); //~30 degrees from the 0 point - that being the top
        //wiggle to let go of the servo
        drive.newTurnTo(-90 ,1, 2);
        sleep(1000);

        drive.newTurnTo(90, 1, 8);

        drive.driveDistance(DeadWheels.sideways, 10, 1, 2);
        drive.driveDistance(DeadWheels.forward, 25, 1, 3);
        drive.newTurnTo(90, 1, 8);
        drive.driveDistance(DeadWheels.forward, -70, 1, 5);
        drive.driveDistance(DeadWheels.sideways, -15, 0.5, 2);
        stop();

    }
}
