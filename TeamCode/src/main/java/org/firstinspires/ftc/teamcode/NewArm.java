package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;


public class NewArm {
    DcMotor pitchMotor = null;
    DcMotor extendMotor = null;
    Servo grabServo = null;
    CRServo positionServo = null;

    DigitalChannel pitchLimit;
    DigitalChannel extendLimit;



    void init(HardwareMap hardwareMap) {
        pitchMotor = hardwareMap.get(DcMotor.class, "pitchMotor");
            //if(pitchMotor==null) {pitchMotor = hardwareMap.get(DcMotor.class, "armMotor");}
        extendMotor = hardwareMap.get(DcMotor.class, "extendMotor");
            //if(extendMotor==null) {extendMotor = hardwareMap.get(DcMotor.class, "clawMotor");}
        grabServo = hardwareMap.get(Servo.class, "grabServo");
        positionServo = hardwareMap.get(CRServo.class, "positionServo");
        //pitchMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        pitchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pitchLimit = hardwareMap.get(DigitalChannel.class, "pitchLimit");
        extendLimit = hardwareMap.get(DigitalChannel.class, "extendLimit");

        pitchLimit.setMode(DigitalChannel.Mode.INPUT);
        extendLimit.setMode(DigitalChannel.Mode.INPUT);

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

    void positionServoPower(float power) { positionServo.setPower(power);}

    int getPitchPosition() {
        return pitchMotor.getCurrentPosition();
    }

    void goToAngle(float angle) {
        angle = Math.max(80, angle);
        angle = Math.min(-15, angle);

        int anglePos = Math.round( ((9 * 1440) * (angle / 360)) );

        pitchMotor.setTargetPosition(anglePos);

        pitchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    void updateMode() {
        if (pitchMotor.getMode() == DcMotor.RunMode.RUN_TO_POSITION && pitchMotor.getCurrentPosition() == pitchMotor.getTargetPosition()) {
            pitchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    int getPitchAngle() {
        return ( pitchMotor.getCurrentPosition() / (9 * 1440) ) * 360;
    }

    /*
    void updateClawRotation() {
        positionServo.setPosition((int) ((9 * 280) * (pitchMotor.getCurrentPosition() / 360)));
    }
    */

    void gotoGrabLocation(boolean active) {
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
    }

}
