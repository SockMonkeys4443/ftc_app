package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;

import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

//looks like they have a package we don't have
//import org.firstinspires.ftc.teamcode.DbgLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.graphics.Bitmap.createBitmap;
import static android.graphics.Bitmap.createScaledBitmap;

public class DarkCamera {

    //thanks mark and recharged for old code!

    OpenGLMatrix lastLocation = null; // WARNING: VERY INACCURATE, USE ONLY TO ADJUST TO FIND IMAGE AGAIN! DO NOT BASE MAJOR MOVEMENTS OFF OF THIS!!
    double tX; // X value extracted from our the offset of the traget relative to the robot.
    double tZ; // Same as above but for Z
    double tY; // Same as above but for Y
    // -----------------------------------
    double rX; // X value extracted from the rotational components of the tartget relitive to the robot
    double rY; // Same as above but for Y
    double rZ; // Same as above but for Z

    VuforiaLocalizer vuforia;
    WebcamName webcam;

    CircuitBreakersVuforia circuitBreakers;

    SuperDark opMode;

    public DarkCamera() {

    }

    public enum SkystonePosition {
        LEFT, MIDDLE, RIGHT
    }

    public void initVuforia(SuperDark opMode) {
        this.opMode = opMode;
        webcam = opMode.hardwareMap.get(WebcamName.class, "Logitech");

        //Setting the options of the camera:
        VuforiaLocalizer.Parameters Vparameters = new VuforiaLocalizer.Parameters();
        Vparameters.cameraName = webcam;
        //Vparameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;// recommended camera direction
        Vparameters.vuforiaLicenseKey = "Ac/t6RH/////AAABmfSRIWWHBEWntprh0zIaUXpQzTfLvb5MmN19+de6V1Y+tXVn8DYLuas2adfkhgiLvTWimvlr4uhDSlLj4skpvdpbdhq0AMLRfp6Wz6plttgcAtk16ptnpCe6Nr/JYZo0IkXdwTt/PyMtoMZptiE2ImeAEum08Lc5B2NTyS4z91+lMQ9GaRzbmS3CLnZhhRD0Wdsw31tunr5nRS/istt0CERUk6gFWJEUCoqVpuEbKWbvsHyfqeJ8LQHZrT5vPSQswpSWKcIqEU/7N83Yje5FLZt7ctw011KR7dR2bwTrX08+Z2/LGrJpLgwbcM9iq3uKwBt6VxIz8ePQG3KN3x7XwehgJXlbHs4LFaX65JaPDzxU";
        vuforia = ClassFactory.getInstance().createVuforia(Vparameters);
        circuitBreakers = new CircuitBreakersVuforia(vuforia);
    }

    public SkystonePosition ScanSkystones() {
        SkystonePosition position = null;
        Image rgbImage = null;
        int rgbTries = 0;

        double yellowCountL = 1;
        double yellowCountC = 1;
        double yellowCountR = 1;

        double blackCountL = 1;
        double blackCountC = 1;
        double blackCountR = 1;

        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        VuforiaLocalizer.CloseableFrame closeableFrame = null;
        this.vuforia.setFrameQueueCapacity(1);


        return position;
    }

    public CircuitBreakersVuforia.skystonePos circuitScan() {
        return circuitBreakers.vuforiascan(false, false);
    }

}
