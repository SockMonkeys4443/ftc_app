package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class SuperDark extends LinearOpMode {

    public boolean telemetryEnabled = false;

    Drive drive = new Drive();
    NewArm arm = new NewArm();
    DistanceControl distance = new DistanceControl();
    Servo foundServo;
    DeadWheels deadWheels = new DeadWheels();
    DarkColorSensor colorSensor = new DarkColorSensor();

    DarkCamera camera = new DarkCamera();

    IMUController imuController = new IMUController();
    public ElapsedTime runtime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
    Timer timer = new Timer(runtime);

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initializing Dark Meronde...");
        telemetry.update();

        drive.init(this);
        deadWheels.init(hardwareMap);
        arm.init(this);
        distance.init(hardwareMap);


        foundServo=hardwareMap.get(Servo.class,"foundServo");
        //newArmServo=hardwareMap.get(Servo.class, "armServo");

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

    void initCamera() {
        camera.initVuforia(this);
    }

    abstract public void darkInit();
    abstract public void darkRunning();

}

