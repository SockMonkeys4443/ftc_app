package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="TEST camera blue", group="working")
public class BlueSkystoneAuto extends SuperDark {

    @Override
    public void darkInit() {
        camera.initVuforia(this);
    }

    @Override
    public void darkRunning() {

        timer.restart();

        //there are 29 inches between where the closest edge of the robot starts to the stones

        //drive to where all 3 stones fit into view
        CircuitBreakersVuforia.skystonePos skystonePosition;

        while(true) {
            skystonePosition = camera.circuitScan();
            telemetry.addData("Skystone position:", skystonePosition );
        }

    }

}
