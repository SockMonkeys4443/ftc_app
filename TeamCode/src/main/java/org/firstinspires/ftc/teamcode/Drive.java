package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Drive {

    public enum Direction {
        LEFT,
        RIGHT
    }

    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;

    SuperDark opMode;

    IMUController imuController;
    DeadWheels deadWheels;
    //double frontLeftPower;
    //double frontRightPower;
    //double backLeftPower;
    //double backRightPower;

    void init(SuperDark opMode) {
        this.opMode = opMode;


        frontLeft = opMode.hardwareMap.get(DcMotor.class,"frontLeft");
        frontRight = opMode.hardwareMap.get(DcMotor.class,"frontRight");
        backLeft = opMode.hardwareMap.get(DcMotor.class,"backLeft");
        backRight = opMode.hardwareMap.get(DcMotor.class,"backRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        this.imuController = opMode.imuController;
        //this.deadWheels = deadWheels;
        this.deadWheels = opMode.deadWheels;
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


   void turnRight(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(-power);
    }

    void newTurn(float angle, double power) {
        //positive is left
        //find a way to do failsafe time
        Direction direction;
        float targetAngle = imuController.getAngle() + angle;
        //true is left ( i think )
        if(angle>0) {direction = Direction.LEFT;}
        else if(angle<0) {direction = Direction.RIGHT;}
        else {return;}

        //turn left
        if(direction == Direction.LEFT) {
            float startDifference = targetAngle-imuController.getAngle();
            while(imuController.getAngle() < targetAngle && opMode.opModeIsActive()) {
                //robot is still not far enough to the left
                float difference = targetAngle-imuController.getAngle();
                float differenceRatio = difference/startDifference;

                //makes an upside down parabola for the turn
                float modifier = -1 * (float) Math.pow(1-differenceRatio,4) + 1;

                //will always make sure the power modifier is (equal to or) above .2
                modifier = Math.max(modifier, 0.2f);
                //will always make sure the power modifier is (equal to or) below 1
                modifier = Math.min(modifier, 1f);

                turnLeft(power * modifier);
            }
            stopAll();
        }

        //turn right
        if(direction == Direction.RIGHT) {
            float startDifference = imuController.getAngle()-targetAngle;
            while(imuController.getAngle() > targetAngle && opMode.opModeIsActive()) {
                //robot is still not far enough to the right
                float difference = imuController.getAngle()-targetAngle;
                float differenceRatio = difference/startDifference;

                //makes an upside down parabola for the turn
                float modifier =  -1 * (float) Math.pow(1-differenceRatio,4) +1;

                //will always make sure the power modifier is (equal to or) above .2
                modifier = Math.max(modifier, 0.2f);
                //will always make sure the power modifier is (equal to or) below 1
                modifier = Math.min(modifier, 1f);

                turnRight(power * modifier);
            }
            stopAll();
        }
    }

    void testTurnTo(Direction direction, double power) {

    }


    /**
     * Do not pass in a negative power, this method automatically handles the power being negative or positive
     * @param axis
     * @param distanceCM
     * @param basePower - a positive number
     */
    void driveDistance(boolean axis, float distanceCM, double basePower, float timeoutSeconds) {

        Timer driveTimer = new Timer(opMode.runtime);


        //the robot will be satisfied with being within 1 cm of the target
        final float PRECISION_CM = 1;

        basePower = Math.abs(basePower);
        int startTicks = deadWheels.getTicks(axis);
        int distanceTicks = deadWheels.CMtoticks(distanceCM);
        int targetTicks = startTicks + distanceTicks;

        boolean finished = false;
        driveTimer.restart();
        while(!finished && opMode.opModeIsActive() && driveTimer.check() < timeoutSeconds) {
            double power = basePower;

            int currentTicks = deadWheels.getTicks(axis);
            int offsetFromTarget = targetTicks - currentTicks;



            float offsetRatio = offsetFromTarget/distanceTicks;

            //makes an upside down parabola for the power
            float modifier = -1 * (float) Math.pow(offsetRatio,4) + 1;

            //will always make sure the power modifier is (equal to or) above .2
            modifier = Math.max(modifier, 0.2f);
            //will always make sure the power modifier is (equal to or) below 1
            modifier = Math.min(modifier, 1f);

            //need to go forward
            if(offsetFromTarget > 0) {
                power = basePower * modifier;
            }

            //need to reverse
            if(offsetFromTarget < 0) {
                power = basePower * modifier * -1;
            }

            //set drive power accordingly
            if (axis == DeadWheels.forward) {
                goForwards(power);
            }
            //sideways
            else {
                strafeRight(power);
            }

            //if the robot is close enough to the target, dictated by the PRECISION_CM variable
            if(Math.abs(offsetFromTarget) < deadWheels.CMtoticks(PRECISION_CM) ) {finished = true; break;}
        }
    }

}