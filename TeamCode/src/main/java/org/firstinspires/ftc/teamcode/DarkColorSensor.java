package org.firstinspires.ftc.teamcode;

//importing arrayList class for reading a color's values
import java.util.ArrayList;

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

    void observeColorValues(int mSeconds, Telemetry telemetry) {
        ArrayList<Integer> redValues = new ArrayList<>();
        ArrayList<Integer> greenValues = new ArrayList<>();
        ArrayList<Integer> blueValues = new ArrayList<>();

        for (int i = 0; i <= mSeconds / 10; i++) {
            redValues.add(colorSensor.red());
            greenValues.add(colorSensor.green());
            blueValues.add(colorSensor.blue());

            opMode.sleep(10);
        }


        int redTotal = 0;
        for (int value: redValues) {
            redTotal += value;
        }
        int redAverage = redTotal / redValues.size();
        telemetry.addData("Red Average", redAverage);


        int greenTotal = 0;
        for (int value: greenValues) {
            greenTotal += value;
        }
        int greenAverage = greenTotal / greenValues.size();
        telemetry.addData("Green Average", greenAverage);


        int blueTotal = 0;
        for (int value: blueValues) {
            blueTotal += value;
        }
        int blueAverage = blueTotal / blueValues.size();
        telemetry.addData("Blue Average", blueAverage);

    }

}
