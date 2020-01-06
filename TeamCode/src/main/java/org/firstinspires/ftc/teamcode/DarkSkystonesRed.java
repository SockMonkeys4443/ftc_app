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
        telemetry.addData("Skystone position:", camera.circuitScan());
        telemetry.update();
        //
    }
}
