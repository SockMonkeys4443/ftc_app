package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Park Left", group="park")
public class DarkParkLeft extends SuperDark {
    @Override
    public void darkInit() {
    }

    @Override
    public void darkRunning() {
        //THE CAMERA IS AT THE FRONT FOR THIS OPMODE
        timer.restart();

        drive.driveDistance(DeadWheels.sideways, 10, 0.5, 1);

        drive.driveDistance(DeadWheels.forward, 60, 0.5, 2); //will probably need adjusting

        stop();

    }
}