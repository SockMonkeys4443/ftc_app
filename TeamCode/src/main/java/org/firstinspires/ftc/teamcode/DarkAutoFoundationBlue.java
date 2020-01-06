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
        /*
        timer.restart();
        drive.goBackwards(0.8);
        while (opModeIsActive() && timer.check() < 1.33f && distance.cmBack() > 50); //TODO: make a method to do this part automatically.
        drive.stopAll();
        drive.goBackwards(0.3);
        while (opModeIsActive() && timer.check() < 1.33f && distance.cmBack() > 10);
        drive.stopAll();
        foundServo.setPosition(1);
        sleep(1000);
                */

        drive.driveDistance(DeadWheels.forward, -68.0f, 0.7, 2.25f);
        foundServo.setPosition(1);
        sleep(1000);

        //drive.strafeLeft(0.75);
        //timer.restart();
        //strafes to the right while the foundation is still in front of us
        //while (opModeIsActive() && timer.check() < 5 && distance.cmBack() < 25);
        //sleep(325);
        //goes for a little bit of extra time
        drive.goForwards(0.5);
        sleep(1000);
        drive.stopAll();


        //sleep(300);

        drive.newTurnTo(90,1, 8);

        foundServo.setPosition(0.17);
        //wiggle to let go of the servo
        drive.newTurnTo(-90 ,1, 2);
        sleep(1000);

        drive.newTurnTo(-90, 1, 8);

        drive.driveDistance(DeadWheels.sideways, -10, 1, 2);
        drive.driveDistance(DeadWheels.forward, 25, 1, 3);
        drive.newTurnTo(-90, 1, 8);
        drive.driveDistance(DeadWheels.forward, -70, 1, 5);
        drive.driveDistance(DeadWheels.sideways, 15, 0.5, 2);
        stop();
    }
}
