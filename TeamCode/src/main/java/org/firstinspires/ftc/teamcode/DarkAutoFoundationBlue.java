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
        drive.turn(90,0.5);

        //drive.strafeLeft(0.5);
        //timeSince = getRuntime();
        /*
        while (opModeIsActive() && distance.cmFront() > 20) {
            telemetry.addData("Time:", timeSince - getRuntime());
            telemetry.addData("Distance", distance.cmFront());
            telemetry.update();
        }
        telemetry.addData("Distance", distance.cmFront());
        sleep(200);
        drive.stopAll();*/
        stop();
    }
}
