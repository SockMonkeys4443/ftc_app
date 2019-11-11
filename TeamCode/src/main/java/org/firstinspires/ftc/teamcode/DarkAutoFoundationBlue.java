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
        timer.restart();
        drive.goBackwards(0.8);
        while (opModeIsActive() && timer.check() < 4 && distance.cmBack() > 50); //TODO: make a method to do this part automatically.
        drive.stopAll();
        drive.goBackwards(0.3);
        while (opModeIsActive() && timer.check() < 5 && distance.cmBack() > 10);
        drive.stopAll();
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

        drive.turn(90,1);

        foundServo.setPosition(0.17);
        sleep(300);

        drive.turnTo(0, 0.75);

        //deploy arm
        arm.armPower(0.5f);
        sleep(500);
        arm.armPower(0);
        sleep(500);
        arm.armPower(-0.5f);
        sleep(200);

        //go "backwards" behind the bridge using timing
        drive.goForwards(1);
        sleep(250);

        drive.strafeLeft(1);
        sleep(500);
        stop();
    }
}
