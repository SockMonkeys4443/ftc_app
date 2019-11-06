package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelImpl;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="Encoder Test", group="test")
public class EncoderTest extends LinearOpMode {
    DeadWheels deadWheels = new DeadWheels();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        deadWheels.init(hardwareMap);

        waitForStart();
        runtime.reset();


        while(opModeIsActive()) {
            telemetry.addData("Encoder:",deadWheels.getTicks(deadWheels.forward));
            telemetry.addData("CM Travelled:", deadWheels.getCM(deadWheels.forward));
            telemetry.addData("Rotations:",deadWheels.getRotations(deadWheels.forward));
            telemetry.update();
        }
    }
}
