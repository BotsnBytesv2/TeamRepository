package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "HighSpeedServo", group = "Concept")

public class ServoTest extends LinearOpMode {
    private CRServo input = null;
    
    @Override
    public void runOpMode(){
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        input = hardwareMap.get(CRServo.class, "input");
        
        
        waitForStart();
        //runtime.reset();
        
        while (opModeIsActive()) {

            input.setPower(-1.0);
    }
    

    // todo: write your code here
}
}
