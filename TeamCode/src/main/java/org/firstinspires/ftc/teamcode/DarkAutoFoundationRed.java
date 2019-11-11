package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Dark Auto Foundation Red", group="test")
public class DarkAutoFoundationRed extends SuperDark {

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
        drive.strafeRight(0.70);
        timer.restart();
        //strafes to the right while the foundation is still in front of us
        //while (opModeIsActive() && timer.check() < 5 && distance.cmBack() < 25);
        sleep(325);
        //goes for a little bit of extra time
        drive.goForwards(1);
        sleep(500);
        drive.stopAll();


        drive.turn(-90,1);


        foundServo.setPosition(0.17); //~30 degrees from the 0 point - that being the top
        sleep(300);

        //something broke here
        sleep(2500);

        drive.newTurn(90, 0.75);
        //drive.turnTo(0, 0.75);


        //deploy arm
        arm.armPower(0.5f);
        sleep(500);
        arm.armPower(0);
        sleep(500);
        arm.armPower(-0.5f);
        sleep(200);


        //15 forwards
        drive.driveDistance(DeadWheels.forward, 15, 1, 5);
        //50 to the right
        drive.driveDistance(DeadWheels.sideways, 50, 1, 8);

        //go "backwards" behind the bridge using timing
        //drive.goForwards(0.5);
        //sleep(500);



        //go "backwards" behind the bridge using timing
        //drive.goForwards(1);
        //sleep(250);

        //drive.strafeRight(1);
        //sleep(500);
        stop();
    }
}
