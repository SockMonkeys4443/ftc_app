package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="✡ Dark Auto Foundation Red Distance Sensor", group="working")
public class DarkAutoFoundationRedSensor extends SuperDark {

    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {

        timer.restart();
        drive.goBackwards(0.8);
        while (opModeIsActive() && timer.check() < 1.33f && distance.cmBack() > 50) {
            if(telemetryEnabled)
                telemetry.addData("Distance: ",distance.cmBack()); telemetry.update();
        } //TODO: make a method to do this part automatically.

        drive.stopAll();
        drive.goBackwards(0.3);
        while (opModeIsActive() && timer.check() < 1.33f && distance.cmBack() > 10) {
            if(telemetryEnabled)
                telemetry.addData("Distance: ",distance.cmBack()); telemetry.update();
        }
        drive.stopAll();
        //drive.driveDistance(DeadWheels.forward, -67.5f, 0.3, 10);
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


        drive.turn(-90,0.75);


        foundServo.setPosition(0.17); //~30 degrees from the 0 point - that being the top
        sleep(300);

        drive.newTurn(-90, 0.75);
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
        drive.driveDistance(DeadWheels.sideways, -70, 1, 6);
        drive.driveDistance(DeadWheels.forward, 15, 0.5, 2);
        stop();

    }
}
