package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class SuperDark extends LinearOpMode {
    Drive drive = new Drive();
    OldArm arm = new OldArm();
    DistanceControl distance = new DistanceControl();
    Servo foundServo;

    IMUController imuController = new IMUController();
    private ElapsedTime runtime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
    Timer timer = new Timer(runtime);

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initializing Dark Meronde...");
        telemetry.update();

        drive.init(this, hardwareMap, imuController);
        arm.init(hardwareMap);
        distance.init(hardwareMap);

        foundServo=hardwareMap.get(Servo.class,"foundServo");

        imuController.init(hardwareMap, telemetry);
        while(!imuController.imu.isGyroCalibrated() && !isStopRequested()) {
            sleep(50);
            idle();
        }
        telemetry.addData("IMU calib status", imuController.imu.getCalibrationStatus().toString());

        telemetry.addData("Status", "Initializing OpMode...");
        telemetry.update();
        darkInit();

        telemetry.addData("Status", "Initialization Complete!");
        telemetry.update();


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

