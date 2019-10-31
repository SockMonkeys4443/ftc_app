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
        while (opModeIsActive() && timer.check() < 4 && distance.cmBack() > 40); //TODO: make a method to do this part automatically.
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
        //goes for a little bit of extra time
        drive.goForwards(0.5);
        sleep(1000);
        drive.stopAll();

        //TODO: make the turn method stop when the opMode stops
        //Ok, I think I got it to work... still needs testing - Jordan.
        drive.turn(90,0.5);


        foundServo.setPosition(0.17); //~30 degrees from the 0 point - that being the top

        sleep(500); //TODO: Andrew, this changed so idk if itll be weird still

        drive.turnTo(0, 0.5);
        drive.turn(10, 0.2);

        //TODO: Andrew, this is the number one thing you need to change. the turn methods don't keep the robot
        //TODO: very square as it is, so you need to fix them so the robot is square against the bridge
        //TODO: right now, the robot is not close enough to the center bridge for another robot to park

        //deploy arm
        arm.armPower(0.5f);
        sleep(500);
        arm.armPower(0);
        sleep(500);
        arm.armPower(-0.5f);
        sleep(500);

        //TODO: Andrew, the arm should be put back down before we go under the bridge

        //go "backwards" behind the bridge using timing
        drive.goForwards(0.5);
        sleep(500);

        drive.strafeLeft(0.3); //TODO: change this to a faster speed.. temporary for testing
        timer.restart();
        while(opModeIsActive() && timer.check() < 3 && distance.cmFront() > 30);
        drive.stopAll();

        stop();
    }
}
