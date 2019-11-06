package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DeadWheels {

    final boolean forward = false;
    final boolean sideways = true;

    DcMotor forwardEncoder, sideEncoder;

    float wheelDiameterMM = 78;
    float wheelCircumfrenceMM = (float) Math.PI * wheelDiameterMM;
    float pulsesPerRotation = 360 * 4;
    float pulsesPerMM = pulsesPerRotation/wheelCircumfrenceMM;
    float mmPerPulses = wheelCircumfrenceMM/pulsesPerRotation;

    public void init(HardwareMap hardwareMap) {
        forwardEncoder = hardwareMap.get(DcMotor.class, "backLeft"); //slot 0
        sideEncoder = hardwareMap.get(DcMotor.class, "frontLeft"); //slot 1
        reset();
    }

    public void reset() {
        forwardEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sideEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        forwardEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        sideEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public float getTicks(boolean motor) {
        if(motor) {return sideEncoder.getCurrentPosition();}
        else {return forwardEncoder.getCurrentPosition();}
    }

    public float getCM(boolean motor) {
        if(motor) {return ticksToCM(sideEncoder.getCurrentPosition());}
        else {return ticksToCM(forwardEncoder.getCurrentPosition());}
    }

    public float getRotations(boolean motor) {
        if(motor) {return cmToRotations(ticksToCM(sideEncoder.getCurrentPosition()));}
        else {return cmToRotations(ticksToCM(forwardEncoder.getCurrentPosition()));}
    }

    private float cmToRotations(float cm) {
        return cm/wheelCircumfrenceMM;
    }

    private float ticksToCM(int ticks ) {
        return ticks * mmPerPulses*.1f;
    }

    private int CMtoticks(float cm) {
        return Math.round(cm*pulsesPerMM) * 10;
    }


    
}

