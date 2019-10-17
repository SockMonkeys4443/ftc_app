package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name="Dark Auto Funny", group="test")
public class DarkFunnyAuto extends SuperDark {
    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {
        while(opModeIsActive()&&!gamepad1.a) {
            if (distance.cmFront()<8) {}
        }
    }
}
