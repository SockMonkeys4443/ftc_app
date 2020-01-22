package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;



public class NewArm {
    DcMotor pitchMotor = null;
    DcMotor extendMotor = null;
    Servo grabServo = null;
    Servo positionServo = null;

    DigitalChannel pitchLimit;
    DigitalChannel extendLimit;


    boolean pitchReset = false;


    SuperDark opMode;


    void init(SuperDark parentOpMode) {
        this.opMode = parentOpMode;
        HardwareMap hardwareMap = opMode.hardwareMap;
        pitchMotor = hardwareMap.get(DcMotor.class, "pitchMotor");
            //if(pitchMotor==null) {pitchMotor = hardwareMap.get(DcMotor.class, "armMotor");}
        extendMotor = hardwareMap.get(DcMotor.class, "extendMotor");
            //if(extendMotor==null) {extendMotor = hardwareMap.get(DcMotor.class, "clawMotor");}
        grabServo = hardwareMap.get(Servo.class, "grabServo");
        positionServo = hardwareMap.get(Servo.class, "positionServo");
        //pitchMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        pitchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        extendMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        pitchLimit = hardwareMap.get(DigitalChannel.class, "pitchLimit");
        extendLimit = hardwareMap.get(DigitalChannel.class, "extendLimit");

        pitchLimit.setMode(DigitalChannel.Mode.INPUT);
        extendLimit.setMode(DigitalChannel.Mode.INPUT);
        pitchLimit.setState(false);
        pitchLimit.setState(false);

        reset();
    }

    void reset() {
        pitchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pitchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    void pitchPower(float power) {
        pitchMotor.setPower(power);
    }
    void extendPower(float power) {extendMotor.setPower(power);}

    void setClaw(boolean open) {
        if(open)
            grabServo.setPosition(1);
        else
            grabServo.setPosition(0);
    }
    void toggleClaw() {
        if(grabServo.getPosition()!=0)
            grabServo.setPosition(1);
        else
            grabServo.setPosition(0);
    }

    void stopPitch() {
        pitchMotor.setPower(0);
    }

    void updatePositionServo() {

    }

    int getPitchPosition() {
        return pitchMotor.getCurrentPosition();
    }

    void goToAngle(float angle, double power) {
        angle = Math.max(80, angle);
        angle = Math.min(-15, angle);

        int anglePos = Math.round( ((9 * 1440) * (angle / 360)) );

        pitchMotor.setTargetPosition(anglePos);

        pitchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        pitchMotor.setPower(power);
        while(pitchMotor.isBusy()); //wait
        pitchMotor.setPower(0);
    }

    void updateMode() {
        if (pitchMotor.getMode() == DcMotor.RunMode.RUN_TO_POSITION && pitchMotor.getCurrentPosition() == pitchMotor.getTargetPosition()) {
            pitchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    int getPitchAngle() {
        return pitchMotor.getCurrentPosition() / 9 * 360;
    }

    boolean extendState() {
        return extendLimit.getState();
    }

    boolean pitchState() {
        return pitchLimit.getState();
    }

    /*
    void updateClawRotation() {
        positionServo.setPosition((int) ((9 * 280) * (pitchMotor.getCurrentPosition() / 360)));
    }
    */

    /**void gotoGrabLocation(boolean active) {
        if (active) {
            if (pitchLimit.getState()) {
                pitchPower(0);
                if (extendLimit.getState()) {
                    extendPower(0);
                } else {
                    extendPower(0.5f);
                }
            } else {
                pitchPower(-0.5f);
            }
        }
    }**/

    void gotoGrabLocation(double power) {
        goToAngle(10f, power);
        pitchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        extendMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while(pitchState() && opMode.opModeIsActive()) {
            pitchMotor.setPower(power);
        }
        pitchMotor.setPower(0);

        while(extendState() && opMode.opModeIsActive()) {
            extendMotor.setPower(1);
        }
        extendMotor.setPower(0);

    }

    void resetPitchEncoder() {
        if (pitchMotor.getMode() == DcMotor.RunMode.RUN_WITHOUT_ENCODER) {
            pitchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        } else {
            pitchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    void updateArmServoPosition() {
        //positionServo.setPosition();
    }

}
