package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

@Autonomous(name = "TeamMarker", group = "Concept")

public class TeamMarker extends LinearOpMode{
    private ElapsedTime runtime = new ElapsedTime();
    private Servo tm = null;

    @Override

    public void runOpMode() {
        telemetry.addLine("HEYO!!!THISPROGRAMWORKS!");
        telemetry.update();
        tm = hardwareMap.get(Servo.class, "teammarker");
        tm.setPosition(0.0);
        waitForStart();
        runtime.reset();
    
        while (opModeIsActive()) {
            tm.setPosition(0.75);
        }
    }
}
