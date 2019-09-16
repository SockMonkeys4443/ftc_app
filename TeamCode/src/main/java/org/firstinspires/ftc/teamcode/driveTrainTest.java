
/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.hardware.rev.*;


@TeleOp(name="Drive Train Test", group="Linear Opmode")
//@Disabled
public class driveTrainTest extends LinearOpMode {
//declaring the program

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    float slowRate = 1;
    float turnPower;
    boolean swingTurn = false;


    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //drive motors
        leftMotor = hardwareMap.dcMotor.get("left motor");
        rightMotor = hardwareMap.dcMotor.get("right motor");
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            turnUpdate();
            slowUpdate();
            driveUpdate();

            //sets the turn power
            turnPower = (gamepad1.right_stick_x);

        }
        //i lost the game
    }

    private void turnUpdate() {
        //changes turning mode
        if(gamepad1.left_trigger > 0.01) {
            swingTurn = true;
        } else {
            swingTurn = false;
        }

    }

    private void slowUpdate() {
        //slows down the robot based on how much the right bumper is pressed
        if(gamepad1.right_trigger > 0.8) {
            slowRate = 0.5f;
        } else if (gamepad1.right_trigger > 0.01) {
            slowRate = 0.75f;
        } else {
            slowRate = 1;
        }
    }

    private void driveUpdate() {

        if (swingTurn) {
            //changes to swing turn
            if (gamepad1.right_stick_x < 0) {
                leftMotor.setPower(((gamepad1.left_stick_y) - turnPower) * slowRate);
            } else if (gamepad1.right_stick_x > 0) {
                rightMotor.setPower(((gamepad1.left_stick_y) - turnPower) * slowRate);
            } else {
                leftMotor.setPower(((gamepad1.left_stick_y) - turnPower) * slowRate);
                rightMotor.setPower(((gamepad1.left_stick_y) - turnPower) * slowRate);
            }
        } else {
            //normal driving
            leftMotor.setPower(((gamepad1.left_stick_y) - turnPower) * slowRate);
            rightMotor.setPower(((gamepad1.left_stick_y) - turnPower) * slowRate);
        }

    }

}
