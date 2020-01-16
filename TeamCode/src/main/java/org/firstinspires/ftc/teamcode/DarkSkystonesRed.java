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

        drive.driveDistance(DeadWheels.sideways, 15, 0.5, 3);

        arm.gotoGrabLocation(0.4);

        arm.setClaw(true); //opens claw

        float distanceSkystone;

        if(skystonePosition == CircuitBreakersVuforia.skystonePos.LEFT) {distanceSkystone = 20;}
        else if (skystonePosition == CircuitBreakersVuforia.skystonePos.RIGHT) {distanceSkystone = -20;}
        else {distanceSkystone = 0;}

        //strafe to face skystone
        drive.driveDistance(DeadWheels.forward, distanceSkystone-20, 0.35, 3);
        //drive forwards to 'eat' skystone
        drive.driveDistance(DeadWheels.sideways, 30, 0.5, 3);

        arm.setClaw(false);

        sleep(2000);

        //drives backwards after picking up the skystone
        drive.driveDistance(DeadWheels.sideways, -30, 0.5, 3);

        //drives to park, taking into account where we went to grab the skystone
        drive.driveDistance(DeadWheels.forward, -100 -distanceSkystone , 0.5, 4);

        stop();

    }
}
