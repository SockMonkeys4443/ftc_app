package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DarkColorSensor {

    SuperDark opMode;
    ColorSensor colorSensor;

    boolean ledState;


    void toggleLED() {
        colorSensor.enableLed(!ledState);
    }

    //returns an int array: 0 -> red   1 -> blue   2 -> green
    int[] getColors() {
        int[] colors = new int[] {
            colorSensor.red(),
            colorSensor.green(),
            colorSensor.blue()
        };
        return colors;
    }

    void addColorTelemetry(Telemetry telemetry) {
        telemetry.addData("Red", colorSensor.red());
        telemetry.addData("Green", colorSensor.green());
        telemetry.addData("Blue", colorSensor.blue());
    }
}
