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

    public CircuitBreakersVuforia.skystonePos circuitScan(boolean save) {
        return circuitBreakers.vuforiascan(save, false);
    }

    public enum StoneLocation {
        LEFT, RIGHT
    }

    public StoneLocation scanTwoStones(boolean saveImages) {
        Image image = null;

        StoneLocation result = StoneLocation.LEFT; //just the default

        double yellowCountLeft = 0, yellowCountRight = 0;
        double blackCountLeft = 0, blackCountRight = 0;

        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        VuforiaLocalizer.CloseableFrame closeableFrame = null;
        this.vuforia.setFrameQueueCapacity(1);

        //acquire the image
        while (image == null) {
            try {
                closeableFrame = this.vuforia.getFrameQueue().take();
                long numImages = closeableFrame.getNumImages();

                for (int i = 0; i < numImages; i++) {
                    if (closeableFrame.getImage(i).getFormat() == PIXEL_FORMAT.RGB565) {
                        image = closeableFrame.getImage(i);
                        if (image != null) {
                            break;
                        }
                    }
                }
            } catch (InterruptedException exc) {

            } finally {
                if (closeableFrame != null) closeableFrame.close();
            }
        }
        //done acquiring the image

        // copy the bitmap from the Vuforia frame
        Bitmap bitmap = Bitmap.createBitmap(image.getWidth(), image.getHeight(), Bitmap.Config.RGB_565);
        bitmap.copyPixelsFromBuffer(image.getPixels());

        String path = Environment.getExternalStorageDirectory().toString();
        FileOutputStream out = null;

        String bitmapName;
        String croppedBitmapName;

        bitmapName = "Bitmap.png";
        croppedBitmapName = "BitmapCropped.png";

        //Todo: these numbers should be changed to fit where we see the stones at.
        int cropStartX = (int) ((370.0 / 1280.0) * bitmap.getWidth());
        int cropStartY = (int) ((130.0 / 720.0) * bitmap.getHeight());
        int cropWidth = (int) ((890.0 / 1280.0) * bitmap.getWidth());
        int cropHeight = (int) ((165.0 / 720.0) * bitmap.getHeight());

        //REMOVE THIS LATER, saves a picture of the stones
        if (saveImages) {
            try {
                File file = new File(path, croppedBitmapName);
                out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        bitmap = createBitmap(bitmap, cropStartX, cropStartY, cropWidth, cropHeight); //Cropped Bitmap to show only stones


        int height;
        int width;
        int pixel;
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        for (height = 0; height < bitmapHeight; ++height) {
            for (width = 0; width < bitmapWidth; ++width) {
                pixel = bitmap.getPixel(width, height);
                if (Color.red(pixel) < 200 || Color.green(pixel) < 200 || Color.blue(pixel) < 200) {
                    if(width < bitmapWidth/2) {
                        yellowCountLeft += Color.red(pixel);
                        blackCountLeft += Color.blue(pixel);
                    } else {
                        yellowCountRight += Color.red(pixel);
                        blackCountRight += Color.blue(pixel);
                    } //right side

                }
            }
        }

        double blackYellowRatioLeft = blackCountLeft / yellowCountLeft;
        double blackYellowRatioRight = blackCountRight / yellowCountRight;

        //higher black ratio over yellow means a likely black stone
        if (blackYellowRatioRight > blackYellowRatioLeft) {
            result = StoneLocation.RIGHT;
        } else
        {
            result = StoneLocation.LEFT;
        }

        return result;
    }

}
