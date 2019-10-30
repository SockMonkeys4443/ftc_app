package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistanceControl {
    Rev2mDistanceSensor sensorFront = null;
    DistanceSensor sensorBack = null;
    DistanceSensor sensorLeft = null;
    DistanceSensor sensorRight = null;


    void init(HardwareMap hwMap) {
        sensorFront = hwMap.get(Rev2mDistanceSensor.class,"sensorFront");
        sensorBack = hwMap.get(DistanceSensor.class,"sensorBack");
        //sensorLeft = hwMap.get(DistanceSensor.class,"sensorLeft");
        //sensorRight = hwMap.get(DistanceSensor.class,"sensorRight");

    }

    double cmLeft() {
        return sensorLeft.getDistance(DistanceUnit.CM);
    }
    double cmRight() {
        return sensorRight.getDistance(DistanceUnit.CM);
    }
    double cmFront() {
        return sensorFront.getDistance(DistanceUnit.CM);
    }
    double cmBack() {
        return sensorBack.getDistance(DistanceUnit.CM);
    }

}