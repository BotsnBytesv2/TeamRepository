package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp(name="ServoTest1", group="Linear Opmode")

public class ClawTest extends LinearOpMode{
    
    private ElapsedTime runtime = new ElapsedTime();
    private Servo rightservo = null;
    private Servo leftservo = null;
    
        @Override
    public void runOpMode(){
        rightservo = hardwareMap.get(Servo.class, "oneServo");
        leftservo = hardwareMap.get(Servo.class, "twoServo");
        
        rightservo.setPosition(0.0);
        leftservo.setPosition(0.0);
        
        waitForStart();
        runtime.reset();
        
        while (opModeIsActive()) {
        
        if (gamepad1.x){
            rightservo.setPosition(0.7);
            leftservo.setPosition(0.3);
        }
        else if (gamepad1.y){
            rightservo.setPosition(0.6);
            leftservo.setPosition(0.4);
        }

        // Show the elapsed game time and wheel power.
            telemetry.addData("Sponge bob is lit", "Run Time: " + runtime.toString());
            telemetry.addData("Servos", "right%5.2f");
            telemetry.update();
        }
    }
}
