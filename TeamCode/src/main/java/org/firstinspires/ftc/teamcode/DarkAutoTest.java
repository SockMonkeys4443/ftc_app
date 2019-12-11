package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name="Dark Auto Go Left", group="test")
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
        while(opModeIsActive() && getRuntime()-timeSince<4 && distance.cmFront() > 20) {
            telemetry.addData("Time:",timeSince-getRuntime());
            telemetry.addData("Distance",distance.cmFront());
            telemetry.update();
        }
        drive.stopAll();
        sleep(2000);
        drive.strafeLeft(0.5);
        timeSince = getRuntime();
        while(opModeIsActive() && getRuntime()-timeSince<4 && distance.cmFront() < 40) {
            telemetry.addData("Time:",timeSince-getRuntime());
            telemetry.addData("Distance",distance.cmFront());
            telemetry.update();
        }
        foundServo.setPosition(0.5);
        sleep(100);
        drive.stopAll();
        //deploy arm
        arm.pitchPower(0.5f);
        sleep(500);
        arm.pitchPower(0);
        sleep(500);
        arm.pitchPower(-0.5f);
        sleep(1000);
        arm.pitchPower(0);
        drive.strafeLeft(0.5);
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
