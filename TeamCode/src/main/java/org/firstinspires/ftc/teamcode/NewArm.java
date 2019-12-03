package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class NewArm {
    DcMotor pitchMotor = null;
    DcMotor extendMotor = null;
    Servo grabServo = null;

    void init(HardwareMap hardwareMap) {
        pitchMotor = hardwareMap.get(DcMotor.class, "pitchMotor");
            //if(pitchMotor==null) {pitchMotor = hardwareMap.get(DcMotor.class, "armMotor");}
        extendMotor = hardwareMap.get(DcMotor.class, "extendMotor");
            //if(extendMotor==null) {extendMotor = hardwareMap.get(DcMotor.class, "clawMotor");}
        grabServo = hardwareMap.get(Servo.class, "grabServo");
        pitchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
}
