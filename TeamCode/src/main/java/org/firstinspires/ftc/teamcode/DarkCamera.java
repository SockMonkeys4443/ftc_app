package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;

import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

//looks like they have a package we don't have
//import org.firstinspires.ftc.teamcode.DbgLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.graphics.Bitmap.createBitmap;
import static android.graphics.Bitmap.createScaledBitmap;

public class DarkCamera {

    private VuforiaLocalizer vuforia;

    public DarkCamera(VuforiaLocalizer vuforia) {
        this.vuforia = vuforia;
    }

    public enum SkystonePosition {
        LEFT, MIDDLE, RIGHT
    }


    public SkystonePosition ScanSkystones() {
        SkystonePosition position = null;
        Image rgbImage = null;
        int rgbTries = 0;

        return position;
    }

}
