package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name="Dark Auto Test", group="test")
public class DarkAutoTest extends SuperDark {

    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {
        double timeSince = getRuntime();
        foundServo.setPosition(30);
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
        sleep(100);
        drive.stopAll();
        //deploy arm
        arm.armPower(0.5f);
        sleep(500);
        arm.armPower(0);
        sleep(500);
        drive.strafeLeft(0.5);
        //timeSince = getRuntime();
        while(opModeIsActive() && distance.cmFront() > 20) {
            telemetry.addData("Time:",timeSince-getRuntime());
            telemetry.addData("Distance",distance.cmFront());
            telemetry.update();
        }
        telemetry.addData("Distance",distance.cmFront());
        drive.stopAll();
        stop();
    }
}
