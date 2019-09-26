package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class SuperDark extends LinearOpMode {
    Drive drive = new Drive();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        darkInit();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        while(opModeIsActive()) {
            darkRunning();
        }

    }

    abstract public void darkInit();
    abstract public void darkRunning();

}

