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
        //drive.newTurnVTwo(90,0.8);
        sleep(1000);
        //drive.newTurnVTwo(180,0.8);
        sleep(1000);
        //drive.newTurnVTwo(270,0.8);
        sleep(1000);
        //drive.newTurnVTwo(-90,0.8);
        sleep(1000);
        //drive.newTurnVTwo(-180,0.8);
        sleep(1000);
        //drive.newTurnVTwo(-270,0.8);
        sleep(1000);

        stop();
    }
}
