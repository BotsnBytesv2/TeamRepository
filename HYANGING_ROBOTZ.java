package org.firstinspires.ftc.teamcode;

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


@Autonomous(name = "HYANGING_ROBOTZ", group = "Concept")

public class HYANGING_ROBOTZ extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor upMotorHanging = null;
    private DcMotor downMotorHanging = null;
    
    @Override
    public void runOpMode(){
        upMotorHanging = hardwareMap.get(DcMotor.class, "hangingGoUp");
        downMotorHanging = hardwareMap.get(DcMotor.class, "hangingGoDown");
        
        upMotorHanging.setPower(0.0);
        downMotorHanging.setPower(0.0);
        
        upMotorHanging.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        downMotorHanging.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        
        waitForStart();
        
        if (opModeIsActive()) {
            gooUp(1120, 0.4);
            gooDown(1120, 0.4);
        }
    }
    
    public void gooUp(int distance, double power){
        upMotorHanging.setMode(DcMotor.RunMode.RESET_ENCODERS);
        upMotorHanging.setTargetPosition(distance);
        upMotorHanging.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        upMotorHanging.setPower(power);
        upMotorHanging.setMode(DcMotor.RunMode.RESET_ENCODERS);
        upMotorHanging.setTargetPosition(-distance);
        upMotorHanging.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        upMotorHanging.setPower(-power);
        while(upMotorHanging.isBusy() && downMotorHanging.isBusy()){
            
        }
        stopMotors();
    }
    public void gooDown(int distance, double power){
        downMotorHanging.setMode(DcMotor.RunMode.RESET_ENCODERS);
        downMotorHanging.setTargetPosition(-distance);
        downMotorHanging.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        downMotorHanging.setPower(-power);
        downMotorHanging.setMode(DcMotor.RunMode.RESET_ENCODERS);
        downMotorHanging.setTargetPosition(distance);
        downMotorHanging.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        downMotorHanging.setPower(power);
        while(upMotorHanging.isBusy() && downMotorHanging.isBusy()){
            
        }
        stopMotors();
    }
     public void stopMotors(){
        upMotorHanging.setPower(0.0);
        downMotorHanging.setPower(0.0);
        sleep(10000);
    }
    
}
