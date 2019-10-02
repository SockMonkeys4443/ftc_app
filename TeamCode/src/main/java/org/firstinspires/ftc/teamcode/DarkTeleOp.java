package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Dark TeleOp Test", group="test")
public class DarkTeleOp extends SuperDark {


    enum DriveMode {NORMAL, WEIRD}

    DriveMode driveMode = DriveMode.NORMAL;

    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {

        if (driveMode==driveMode.NORMAL) {
            driveNormal(1);
        }
        else if (driveMode==driveMode.WEIRD) {
            driveWeird(1);
        }
        else {
            driveNormal(1);
        }

        buttonUpdate();

        if (yPressed()) {
            toggleDrive();
        }

    }


    boolean aWasPressed;
    boolean bWasPressed;
    boolean xWasPressed;
    boolean yWasPressed;

    boolean aPressed() {
        if(!aWasPressed && gamepad1.a) {
            aWasPressed = true;
            return true;
        } else {return false;}
    }
    boolean bPressed() {
        if(!bWasPressed && gamepad1.b) {
            bWasPressed = true;
            return true;
        } else {return false;}
    }
    boolean xPressed() {
        if(!xWasPressed && gamepad1.x) {
            xWasPressed = true;
            return true;
        } else {return false;}
    }
    boolean yPressed() {
        if(!yWasPressed && gamepad1.y) {
            yWasPressed = true;
            return true;
        } else {return false;}
    }

    void buttonUpdate() {
        if(aWasPressed&&!gamepad1.a){aWasPressed=false;}
        if(bWasPressed&&!gamepad1.b){bWasPressed=false;}
        if(xWasPressed&&!gamepad1.x){xWasPressed=false;}
        if(yWasPressed&&!gamepad1.y){yWasPressed=false;}

    }

    void toggleDrive() {
        if (driveMode==DriveMode.NORMAL) {driveMode=DriveMode.WEIRD;}
        else if (driveMode==DriveMode.WEIRD) {driveMode=DriveMode.NORMAL;}
    }

    void driveNormal(double power) {

        double frontPower = -gamepad1.left_stick_y;
        double sidePower = gamepad1.left_stick_x;

        double turnPower = gamepad1.right_stick_x;

        drive.frontLeft.setPower((frontPower +sidePower) +turnPower);
        drive.frontRight.setPower((frontPower-sidePower) -turnPower);
        drive.backLeft.setPower((frontPower-sidePower) +turnPower);
        drive.backRight.setPower((frontPower+sidePower) -turnPower);

    }

    void driveWeird(double power) {

        double frontPower = -gamepad1.left_stick_y;
        double sidePower = gamepad1.left_stick_x;
        double turnPower = gamepad1.right_stick_x;

        if(frontPower>0.5) {frontPower=1;} else if (frontPower<-0.5) {frontPower=-1;} else {frontPower=0;}
        if(sidePower>0.5) {sidePower=1;} else if (sidePower<-0.5) {sidePower=-1;} else {sidePower=0;}
        //if(turnPower>0.5) {turnPower=1;} else if (turnPower<-0.5) {turnPower=-1;} else {turnPower=0;}


        drive.frontLeft.setPower((frontPower +sidePower) +turnPower);
        drive.frontRight.setPower((frontPower-sidePower) -turnPower);
        drive.backLeft.setPower((frontPower-sidePower) +turnPower);
        drive.backRight.setPower((frontPower+sidePower) -turnPower);

    }
}

