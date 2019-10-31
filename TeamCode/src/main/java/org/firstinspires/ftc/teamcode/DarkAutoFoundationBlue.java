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
        drive.goBackwards(0.5);
        while (opModeIsActive() && timer.check() < 4 && distance.cmBack() > 40);
        drive.stopAll();
        drive.goBackwards(0.3);
        while (opModeIsActive() && timer.check() < 5 && distance.cmBack() > 10);
        drive.stopAll();
        foundServo.setPosition(1);
        sleep(1500);
        drive.strafeLeft(0.35);
        timer.restart();
        //strafes to the right while the foundation is still in front of us
        while (opModeIsActive() && timer.check() < 5 && distance.cmBack() < 25);
        drive.stopAll();

        //TODO: make the turn method stop when the opMode stops
        //Ok, I think I got it to work... still needs testing - Jordan.
        drive.turn(90,0.5);

        foundServo.setPosition(0.17); //~30 degrees from the 0 point - that being the top

        drive.turnTo(0, 0.5);

        //deploy arm
        arm.armPower(0.5f);
        sleep(500);
        arm.armPower(0);
        sleep(500);
        arm.armPower(-0.5f);
        sleep(500);

        stop();
    }
}
