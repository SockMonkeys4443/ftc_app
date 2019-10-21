package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Dark Auto Go Right", group="test")
public class DarkAutoTestRight extends SuperDark {

    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {
        double timeSince = getRuntime();
        drive.goForwards(0.5);
        while(opModeIsActive() && getRuntime()-timeSince<4000000 && distance.cmFront() > 20) {
            telemetry.addData("Time:",timeSince-getRuntime());
            telemetry.addData("Distance",distance.cmFront());
            telemetry.update();
        }
        drive.stopAll();
        sleep(2000);
        drive.strafeLeft(0.5);
        timeSince = getRuntime();
        while(opModeIsActive() && getRuntime()-timeSince<4000000 && distance.cmFront() < 40) {
            telemetry.addData("Time:",timeSince-getRuntime());
            telemetry.addData("Distance",distance.cmFront());
            telemetry.update();
        }
        foundServo.setPosition(0.5);
        sleep(100);
        drive.stopAll();
        sleep(500);
        drive.strafeRight(0.5);
        //timeSince = getRuntime();
        while(opModeIsActive() && distance.cmFront() > 20) {
            telemetry.addData("Time:",timeSince-getRuntime());
            telemetry.addData("Distance",distance.cmFront());
            telemetry.update();
        }
        telemetry.addData("Distance",distance.cmFront());
        sleep(200);
        drive.stopAll();
        stop();
    }
}
