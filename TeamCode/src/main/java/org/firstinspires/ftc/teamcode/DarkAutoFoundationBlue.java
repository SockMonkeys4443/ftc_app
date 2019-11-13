package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Dark Auto Foundation Blue", group="test")
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

        drive.driveDistance(DeadWheels.forward, -60.5f, 0.8, 2);
        foundServo.setPosition(1);
        sleep(1000);

        drive.strafeLeft(0.75);
        timer.restart();
        //strafes to the right while the foundation is still in front of us
        //while (opModeIsActive() && timer.check() < 5 && distance.cmBack() < 25);
        sleep(325);
        //goes for a little bit of extra time
        drive.goForwards(1);
        sleep(500);
        drive.stopAll();


        //sleep(300);

        drive.turn(90,0.75);

        foundServo.setPosition(0.17);
        sleep(300);

        //TODO: add a timer failsafe of 2 seconds (maybe 3?)
        drive.newTurn(-180, 0.75);
        //drive.turnTo(0, 0.75);


        //deploy arm
        arm.armPower(0.5f);
        sleep(500);
        arm.armPower(0);
        sleep(500);
        arm.armPower(-0.5f);
        sleep(1000);
        arm.armPower(0);


        //15 forwards
        drive.driveDistance(DeadWheels.sideways, -10, 1, 2);
        drive.driveDistance(DeadWheels.forward, 25, 1, 3);
        //TODO: put a newTurnTo() here to get back to -180 degrees.
        drive.driveDistance(DeadWheels.forward, -70, 1, 5);
        drive.driveDistance(DeadWheels.sideways, 15, 0.5, 2);
        stop();
    }
}
