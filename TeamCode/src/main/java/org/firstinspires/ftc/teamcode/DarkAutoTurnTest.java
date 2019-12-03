package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="(DEBUG) Dark Auto Turn Test", group="test")
public class DarkAutoTurnTest extends SuperDark {

    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {
        telemetryEnabled=true;
        //drive.newTurn(-90, 0.4);
        //sleep(2000);
        drive.newTurnTo(90,0.5, 5);
        sleep(2000);
        drive.newTurnTo(180,0.5, 5);
        sleep(2000);
        drive.newTurnTo(-90,0.5, 5);
        sleep(2000);
        drive.newTurnTo(0,0.5, 5);
        sleep(2000);
        drive.newTurnTo(-90, 0.5, 5);

        stop();
    }
}
