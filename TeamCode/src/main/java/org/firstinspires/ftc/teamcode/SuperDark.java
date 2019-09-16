package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public abstract class SuperDark extends OpMode {
    DcMotor test = hardwareMap.get(DcMotor.class, "test");
    Drive drive = new Drive(hardwareMap);
    {
        test.setPower(0.5);
        drive.
    }
}

class Drive {
    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;

    //double frontLeftPower;
    //double frontRightPower;
    //double backLeftPower;
    //double backRightPower;


    Drive(HardwareMap hwMap) {
        frontLeft = hwMap.get(DcMotor.class,"frontLeft");
        frontRight = hwMap.get(DcMotor.class,"frontRight");
        backLeft = hwMap.get(DcMotor.class,"backLeft");
        backRight = hwMap.get(DcMotor.class,"backRight");
    }

    //Sets all motors to Zero power
    void stopAll() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }

    void powerLeft(double power) {
    }
    void powerRight(double power) {
    }
    void powerForward(double power) {
    }
    void powerBackwards(double power) {
    }
    void turnLeft(double power) {
    }
    void turnRight(double power) {
    }



}