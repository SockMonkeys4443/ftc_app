package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Drive {
    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;

    SuperDark opMode;

    IMUController imuController;
    //double frontLeftPower;
    //double frontRightPower;
    //double backLeftPower;
    //double backRightPower;

    void init(SuperDark opMode, HardwareMap hwMap, IMUController imuController) {
        frontLeft = hwMap.get(DcMotor.class,"frontLeft");
        frontRight = hwMap.get(DcMotor.class,"frontRight");
        backLeft = hwMap.get(DcMotor.class,"backLeft");
        backRight = hwMap.get(DcMotor.class,"backRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //TODO: change how this works? I'm not sure if this will actually be able to check during loops and stuff the state of the opMode.
        this.opMode = opMode;


        this.imuController = imuController;
    }

    //Sets all motors to Zero power
    void stopAll() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    void strafeLeft(double power) {
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(-power);
    }
    void strafeRight(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(power);
    }
    void goForwards(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }
    void goBackwards(double power) {
        frontLeft.setPower(-power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(-power);
    }
    void turnLeft(double power) {
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(power);
    }

    //needs better names


    void turn(float angle, double power) {
        //TODO: find a way to put in the failsafe timing.
        float targetAngle = imuController.getAngle() + angle;
        if (targetAngle - imuController.getAngle() > 0) {
            //TODO: test that this will actually STOP when requested.
            while (targetAngle > imuController.getAngle() && opMode.opModeIsActive()) {
                turnLeft(power);
            }
            stopAll();
            while (targetAngle < imuController.getAngle() && opMode.opModeIsActive() ) {
                turnRight(power);
            }
        } else if (targetAngle - imuController.getAngle() < 0) {
            while (targetAngle < imuController.getAngle() && opMode.opModeIsActive() ) {
                turnRight(power);
            }
            stopAll();
            while (targetAngle > imuController.getAngle() && opMode.opModeIsActive()) {
                turnLeft(power);
            }
        }
        stopAll();
    }

    void turnTo(float point, double power) {
        turn(imuController.testDirection(point), power);
    }

   void turnRight(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(-power);
    }



}