package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import static org.firstinspires.ftc.teamcode.IMUController.angleDiff;
import static org.firstinspires.ftc.teamcode.IMUController.getAngle360;


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

    IMUController   imuController;
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
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);


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
    void turnRight(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }
    void turnLeft(double power) {
        frontLeft.setPower(-power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(-power);
    }
    void goBackwards(double power) {
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(power);
    }

    void goForwards(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(-power);
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

    /*
    void newTurn(float degreesToTravel, double basePower) {


        //TODO: fix going over 360 degrees
        //the robot will be satisfied with being within 2 degrees of the target
        final float PRECISION_DEGREES = 3;

        Timer driveTimer = new Timer(opMode.runtime);
        //positive is left
        Direction direction;
        basePower = Math.abs(basePower);
        float startAngle = imuController.getAngle();
        float targetAngle = imuController.getAngle() + degreesToTravel;

        //TODO: configure timed failsafe
        while(opMode.opModeIsActive()) {
            ///
            float currentAngle = imuController.getAngle();
            float degreesTravelled = currentAngle-startAngle;
            float ratioIncomplete = degreesTravelled/degreesToTravel;
            if(ratioIncomplete<0) ratioIncomplete=0;
            float modifier = 1-ratioIncomplete;
            double power = basePower;

            if(targetAngle-currentAngle<0) {
                power=-1*basePower;
            }

            power = power*modifier;

            if(power > 0) {
                //will always make sure the power is (equal to or) above .15
                power = Math.max(power, 0.15f);
                //will always make sure the power is (equal to or) below 1
                power = Math.min(power, 1f);
            }
            else {
                //will always make sure the power is (equal to or) above .15
                power = Math.min(power, -0.15f);
                //will always make sure the power is (equal to or) below 1
                power = Math.max(power, -1f);
            }

            turnLeft(power);

            if(Math.abs(targetAngle-currentAngle)<PRECISION_DEGREES) {break;}
        }

        stopAll();
    }
     */

    void newTurn(float degreesToTravel, double basePower, float seconds) {
        float startAngle = imuController.getAngle360();
        float targetAngle = startAngle+degreesToTravel;
        newTurnTo(targetAngle, basePower, seconds);
    }

    /**
     * @author Jordan
     * @date 11-26-19
     * @param targetAngle180 Angle between -180 and 180 that the robot should travel to
     * @param basePower
     * @param seconds
     */
    void newTurnTo(float targetAngle180, double basePower, float seconds) {
        float startAngle = imuController.getAngle360();
        float targetAngle = getAngle360(targetAngle180);
        basePower = Math.abs(basePower);

        //the robot will be satisfied with being within 2 degrees of the target
        final float PRECISION_DEGREES = 2;
        final double MIN_SPEED = 0.2;

        Timer turnTimer = new Timer(opMode.runtime);
        turnTimer.restart();

        while(opMode.opModeIsActive() && turnTimer.check()<seconds) {
            float currentAngle = imuController.getAngle360();
            float difference = angleDiff(currentAngle, targetAngle);

            double power = basePower;

            double modifier = (Math.abs(angleDiff(currentAngle, targetAngle))) / 90;

            modifier = Math.min(modifier, 1); //modifier cannot be greater than 1

            if(difference>0) {power = modifier*basePower;}
            if(difference<0) {power = modifier*-basePower;}

            if(power > 0) {
                //will always make sure the power is (equal to or) above .15
                power = Math.max(power, MIN_SPEED);
                //will always make sure the power is (equal to or) below 1
                power = Math.min(power, 1);
            }
            else {
                //will always make sure the power is (equal to or) above .15
                power = Math.min(power, -MIN_SPEED);
                //will always make sure the power is (equal to or) below 1
                power = Math.max(power, -1);
            }

            turnLeft(power);

            if(Math.abs(difference) < PRECISION_DEGREES) {break;}

        }

        stopAll();
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

        driveTimer.restart();
        while(opMode.opModeIsActive() && driveTimer.check() < timeoutSeconds) {
            double power = basePower;

            int currentTicks = deadWheels.getTicks(axis);
            int offsetFromTarget = targetTicks - currentTicks;

            float offsetRatio = offsetFromTarget/distanceTicks;

            //offsetRatio = 1 - offsetRatio;

            float modifier = 1-offsetRatio;

            //will always make sure the power modifier is (equal to or) above .2
            //modifier = Math.max(modifier, 0.05f);
            //will always make sure the power modifier is (equal to or) below 1
            //modifier = Math.min(modifier, 1f);

            //need to go forward
            if(offsetFromTarget > 0) {
                power = basePower * modifier;
                power = Math.min(power, 1f);
                power = Math.max(power, 0.25f);
            }

            //need to reverse
            //TODO: issue?
            if(offsetFromTarget < 0) {
                power = basePower * modifier * -1;
                power = Math.max(power, -1f);
                power = Math.min(power, -0.25f);
            }

            //set drive power accordingly
            if (axis == DeadWheels.forward) {
                goForwards(power);
            }
            //sideways
            else {
                strafeRight(power);
            }


            if(opMode.telemetryEnabled) {
                opMode.telemetry.addData("Distance Travelled: ",currentTicks-targetTicks);
                opMode.telemetry.addData("Modifier: ", modifier);
                opMode.telemetry.addData("Offset:",offsetFromTarget);
                opMode.telemetry.addData("Power: ", power);
                opMode.telemetry.update();
            }

            //if the robot is close enough to the target, dictated by the PRECISION_CM variable
            if(Math.abs(offsetFromTarget) < deadWheels.CMtoticks(PRECISION_CM) ) {break;}
        }
        stopAll();
    }



}