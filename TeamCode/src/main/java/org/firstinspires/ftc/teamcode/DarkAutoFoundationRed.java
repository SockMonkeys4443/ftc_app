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


        drive.driveDistance(DeadWheels.forward, -60.5f, 0.8, 2);
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


        drive.newTurnTo(-90,2, 8);


        foundServo.setPosition(0.17); //~30 degrees from the 0 point - that being the top
        sleep(500);

        drive.newTurnTo(90, 2, 8);
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
        drive.driveDistance(DeadWheels.forward, -10, 1, 2);
        //50 to the right
        drive.driveDistance(DeadWheels.sideways, 25, 1, 3);
        //TODO: put a newTurnTo() here to get back to -180 degrees.
        drive.newTurnTo(90, 2, 8);
        drive.driveDistance(DeadWheels.sideways, -70, 1, 6);
        drive.driveDistance(DeadWheels.forward, 15, 0.5, 2);
        stop();

    }
}
