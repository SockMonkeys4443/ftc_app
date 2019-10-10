package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class OldArm {
    DcMotor armMotor = null;
    DcMotor clawMotor = null;

    void init(HardwareMap hardwareMap) {
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        clawMotor = hardwareMap.get(DcMotor.class, "clawMotor");

    }

    void setPower(float power) {
        armMotor.setPower(power);
    }

    void stop() {
        armMotor.setPower(0);
    }
}
