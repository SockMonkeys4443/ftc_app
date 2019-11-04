package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import java.math.RoundingMode;

public class IMUController {
    public BNO055IMU imu;
    Telemetry telemetry;
    public Orientation angles = new Orientation();
    private float lastHeading;
    BNO055IMU.Parameters parameters;
    Position position;
    Velocity velocity;

    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        parameters = new BNO055IMU.Parameters();
        position = new Position();
        velocity = new Velocity();

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        this.telemetry=telemetry;

        imu.initialize(parameters);

        telemetry.addData("Status", "calibrating imu...");
        telemetry.update();
    }

    //Angles

    public float getAngle() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        lastHeading = AngleUnit.DEGREES.normalize(AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle));
        return lastHeading;
    }

    public float getAngle360() {
        float angle = getAngle() - ((float)java.lang.Math.floor(getAngle() / 360) * 360);
        return angle;
    }

    public float testDirection(float target) {
        float angle;

        if (target - getAngle360() < 180) {
            angle = target - getAngle360();
        } else {
            angle = target - getAngle360() - 360;
        }

        return angle;

    }


    //Tracking

    public void startTracking2() {
        imu.startAccelerationIntegration(position, velocity, 300);
    }
    public void stopTracking() {
        imu.stopAccelerationIntegration();
    }

    public void startTracking() {
        imu.startAccelerationIntegration(new Position(), new Velocity(), 300);
    }
    public Position updatePosition()
    {
        Position newPosition = imu.getPosition();
        telemetry.addData("IMU X:",newPosition.x);
        telemetry.addData("IMU Y:",newPosition.y);
        telemetry.addData("IMU Z:",newPosition.z);

        return newPosition;
    }

    /*
    public void resetAngle() {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        globalAngle = 0;
    }*/
}
