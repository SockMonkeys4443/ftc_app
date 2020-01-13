package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Skystones Red", group="test")
public class DarkSkystonesRed extends SuperDark {
    @Override
    public void darkInit() {
        initCamera();
    }

    @Override
    public void darkRunning() {
        timer.restart();

        //there are 29 inches between where the closest edge of the robot starts to the stones

        //drive to where all 3 stones fit into view
        DarkCamera.StoneLocation skystonePosition;

        skystonePosition = camera.scanTwoStones();
        telemetry.addData("Skystone position:", skystonePosition );

        sleep(8000); //time to read telemetry

    }
}
