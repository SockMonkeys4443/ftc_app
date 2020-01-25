package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Park 2 ft Forward", group="park")
public class DarkParkForward extends SuperDark {
    @Override
    public void darkInit() {
    }

    @Override
    public void darkRunning() {
        //THE CAMERA IS AT THE FRONT FOR THIS OPMODE
        timer.restart();

        drive.driveDistance(DeadWheels.sideways, 60, 0.5, 1);

        stop();

    }
}