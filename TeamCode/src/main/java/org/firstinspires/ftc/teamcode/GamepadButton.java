package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadButton extends SuperDark {

    Gamepad gamepad;
    Boolean aWasPressed;
    Boolean bWasPressed;
    Boolean xWasPressed;
    Boolean yWasPressed;

    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {

    }

    public GamepadButton(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public void updateButtons() {
        if(aWasPressed&&!gamepad.a){aWasPressed=false;}
        if(bWasPressed&&!gamepad.b){bWasPressed=false;}
        if(xWasPressed&&!gamepad.x){xWasPressed=false;}
        if(yWasPressed&&!gamepad.y){yWasPressed=false;}
    }

    boolean aPressed() {
        if (!aWasPressed && gamepad.a) {
            aWasPressed = true;
            return true;
        } else {
            return false;
        }
    }
    boolean bPressed() {
        if (!bWasPressed && gamepad.b) {
            bWasPressed = true;
            return true;
        } else {
            return false;
        }
    }
    boolean xPressed() {
        if (!xWasPressed && gamepad.x) {
            xWasPressed = true;
            return true;
        } else {
            return false;
        }
    }
    boolean yPressed() {
        if (!yWasPressed && gamepad.y) {
            yWasPressed = true;
            return true;
        } else {
            return false;
        }
    }
}
