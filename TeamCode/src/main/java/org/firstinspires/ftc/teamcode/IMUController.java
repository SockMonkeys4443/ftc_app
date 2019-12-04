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

    //this method gets the robot's angle
    public float getAngle360() {
        float angle = getAngle() - ((float)java.lang.Math.floor(getAngle() / 360) * 360);
        return angle;
    }


    /**
     * @param angle An angle between -180 to 180
     * @return The corresponding angle between 0 and 360
     */
    public static float getAngle360(float angle) {
        angle = angle - ((float) Math.floor(angle / 360) * 360);
        return angle;
    }

    //public float getAngle180() {
    //    return getAngle360(getAngle())-180;
    //}

    //public static float getAngle180(float angle) {
    //    return getAngle360(angle)-180;
    //}

    public float testDirection(float target) {
        float angle = target - getAngle360();



        return angle;
    }

    // who's joe? joe biden for president!

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

    /**
     * @see <a href="https://stackoverflow.com/questions/7570808/how-do-i-calculate-the-difference-of-two-angle-measures/30887154">Stackoverflow helped me</a>
     * @param angle1
     * @param angle2
     * @return
     */
    public static float angleDiff(float angle1, float angle2) {
        float phi = Math.abs(angle2 - angle1) % 360;       // This is either the distance or 360 - distance
        float distance = phi > 180 ? 360 - phi : phi;
        int sign = (angle1 - angle2 >= 0 && angle1 - angle2 <= 180) || (angle1 - angle2 <= -180 && angle1 - angle2>= -360) ? -1 : 1;
        return distance*sign;
    }

    /*
    public void resetAngle() {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        globalAngle = 0;
    }*/
}
