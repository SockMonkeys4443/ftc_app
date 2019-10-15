package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class OldArm {
    DcMotor armMotor = null;
    DcMotor clawMotor = null;

    void init(HardwareMap hardwareMap) {
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        clawMotor = hardwareMap.get(DcMotor.class, "clawMotor");
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clawMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    void armPower(float power) {
        armMotor.setPower(power);
    }
    void clawPower(float power) {clawMotor.setPower(power);}

    void setClaw(boolean opened) {
        //clawMotor.setPower(power);
        //TODO: set to open or closed
        //TODO: use encoders!!!
    }
    void toggleClaw() {
        //TODO: make this a toggle using the above method
    }



    void stop() {
        armMotor.setPower(0);
    }
}
