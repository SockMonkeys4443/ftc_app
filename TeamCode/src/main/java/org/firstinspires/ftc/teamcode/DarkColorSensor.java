package org.firstinspires.ftc.teamcode;

//importing arrayList class for reading a color's values
import java.util.ArrayList;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DarkColorSensor {

    SuperDark opMode;
    ColorSensor colorSensor;

    boolean ledState;

    void init(SuperDark opMode) {
        this.opMode = opMode;
        colorSensor = opMode.hardwareMap.get(ColorSensor.class, "colorSensor");
    }


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

            try {
                Thread.sleep(10);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
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

    boolean checkForColor(int red, int green, int blue, int range) {
        //these booleans might be less efficient but they make the code easier to read
        boolean redPassed;
        boolean greenPassed;
        boolean bluePassed;

        if (colorSensor.red() > red - range &&  colorSensor.red() < red + range) {
            redPassed = true;
        } else {
            redPassed = false;
        }

        if (colorSensor.green() > green - range && colorSensor.green() < green + range) {
            greenPassed = true;
        } else {
            greenPassed = false;
        }

        if (colorSensor.blue() > blue - range && colorSensor.blue() < blue + range) {
            bluePassed = true;
        } else {
            bluePassed = false;
        }

        if (redPassed && greenPassed && bluePassed) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkForBlack() {
        if (checkForColor(15, 15, 15, 15)) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkForYellow() {
        if (checkForColor(550, 375,175, 40)) {
            return true;
        } else {
            return false;
        }
    }

}
