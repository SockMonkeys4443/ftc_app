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
        CircuitBreakersVuforia.skystonePos skystonePosition;

        skystonePosition = camera.circuitScan(true);
        telemetry.addData("Skystone position:", skystonePosition );
        telemetry.update();

        //drive to skystone


        //drive to scoop skystone

        drive.driveDistance(DeadWheels.sideways, 15, 1, 2);

        arm.gotoGrabLocation(0.4);

        arm.setClaw(true); //opens claw


        drive.driveDistance(DeadWheels.sideways, 20, 1, 2);

        arm.setClaw(false);

    }
}
