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

    /**
     *
     * may or may not work
     * @author - Jordan
     *
     */
    void newTurn(float angle, double power) {
        //positive is left
        //find a way to do failsafe time
        boolean direction;
        float targetAngle = imuController.getAngle() + angle;
        //true is left ( i think )
        if(angle>0) {direction = true;}
        else if(angle<0) {direction = false;}
        else {return;}

        //turn left
        if(direction) {
            float startDifference = targetAngle-imuController.getAngle();
            while(imuController.getAngle() < targetAngle) {
                //robot is still not far enough to the left
                float difference = targetAngle-imuController.getAngle();
                float ratioComplete = difference/startDifference;
                //will always make sure the power modifier is (equal to or) above .2
                float ratioAdjusted = Math.max(ratioComplete,0.2f);
                //will always make sure the power modifier is (equal to or) below 1
                ratioAdjusted = Math.min(ratioAdjusted,1f);
                turnLeft(power*ratioAdjusted);
            }
            stopAll();
        }

        //turn right
        if(!direction) {
            float startDifference = imuController.getAngle()-targetAngle;
            while(imuController.getAngle() > targetAngle) {
                //robot is still not far enough to the right
                float difference = imuController.getAngle()-targetAngle;
                float ratioComplete = difference/startDifference;
                //will always make sure the power modifier is (equal to or) above .2
                float ratioAdjusted = Math.max(ratioComplete,0.2f);
                //will always make sure the power modifier is (equal to or) below 1
                ratioAdjusted = Math.min(ratioAdjusted,1f);
                turnRight(power*ratioAdjusted);
            }
            stopAll();
        }
    }

    void turn(float angle, double power) {
        //TODO: find a way to put in the failsafe timing.
        float targetAngle = imuController.getAngle() + angle;
        if (angle > 0) {
            while (targetAngle > imuController.getAngle() && opMode.opModeIsActive()) {
                turnLeft(power);
            }
            stopAll();
            while (targetAngle < imuController.getAngle() && opMode.opModeIsActive() ) {
                turnRight(power/2);
            }
        } else if (angle < 0) {
            while (targetAngle < imuController.getAngle() && opMode.opModeIsActive() ) {
                turnRight(power);
            }
            stopAll();
            while (targetAngle > imuController.getAngle() && opMode.opModeIsActive()) {
                turnLeft(power/2);
            }
        }
        stopAll();
    }

    void turnTo(float point, double power) {
        turn(imuController.testDirection(point), power);
    }

    void turnTo(float point, double power, double adjust) {

        turn(imuController.testDirection(point), power);
        //power =
    }

   void turnRight(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(-power);
    }



}