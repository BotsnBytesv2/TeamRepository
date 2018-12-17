package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

@Autonomous(name = "MEGABYTES_DRIVETRAIN", group = "Concept")

public class MEGABYTES_DRIVETRAIN extends LinearOpMode{
    
    //private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontright = null;
    private DcMotor frontleft = null;
    private DcMotor backleft = null;
    private DcMotor backright = null;
    
    @Override
    public void runOpMode(){
        frontleft = hardwareMap.get(DcMotor.class, "frontleftmotor");
        frontright = hardwareMap.get(DcMotor.class, "frontrightmotor");
        backright = hardwareMap.get(DcMotor.class, "backrightmotor");
        backleft = hardwareMap.get(DcMotor.class, "backleftmotor");
        
        frontleft.setDirection(DcMotor.Direction.REVERSE);
        backleft.setDirection(DcMotor.Direction.REVERSE);
    /*
        frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    */    
        frontleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        
        waitForStart();
        frontleft.setPower(0.0);
        frontright.setPower(0.0);
        backleft.setPower(0.0);
        backright.setPower(0.0);

        if (opModeIsActive()) {
        telemetry.addLine("HI");
        moveForward(1120, 0.4);
        telemetry.addLine("HI");
        //stopMotors();
        
        }
        
    }
    
    //distance should be in ticks
    //1120 ticks per rotation
    public void moveForward(int distance, double power){
        frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        int newtargetfl = frontleft.getTargetPosition() + (int)distance;
        int newtargetfr = frontright.getTargetPosition() + (int)distance;
        int newtargetbl = backleft.getTargetPosition() + (int)distance;
        int newtargetbr = backright.getTargetPosition() + (int)distance;
        
        frontleft.setTargetPosition(newtargetfl);
        frontright.setTargetPosition(newtargetfr);
        backleft.setTargetPosition(newtargetbl);
        backright.setTargetPosition(newtargetbr);
        
        frontleft.setPower(power);
        frontright.setPower(0-power);
        backleft.setPower(power);
        backright.setPower(0-power);

        frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        while(frontleft.isBusy() && frontright.isBusy() && backleft.isBusy() && backright.isBusy()){
        
        }
        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
    }
    public void moveBackward(int distance, double power){
        frontleft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        frontright.setMode(DcMotor.RunMode.RESET_ENCODERS);
        backleft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        backright.setMode(DcMotor.RunMode.RESET_ENCODERS);
        
        frontleft.setTargetPosition(distance);
        frontright.setTargetPosition(distance);
        backleft.setTargetPosition(distance);
        backright.setTargetPosition(distance);

        frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        frontleft.setPower(-power);
        frontright.setPower(power);
        backleft.setPower(-power);
        backright.setPower(power);
        
        while(frontleft.isBusy() && frontright.isBusy() && backleft.isBusy() && backright.isBusy()){
        
    }
        stopMotors();
        
    }
    public void moveRight(int distance, double power){
        frontleft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        frontright.setMode(DcMotor.RunMode.RESET_ENCODERS);
        backleft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        backright.setMode(DcMotor.RunMode.RESET_ENCODERS);
        
        frontleft.setTargetPosition(distance);
        frontright.setTargetPosition(distance);
        backleft.setTargetPosition(distance);
        backright.setTargetPosition(distance);

        frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        frontleft.setPower(-power);
        frontright.setPower(-power);
        backleft.setPower(power);
        backright.setPower(power);
        
        while(frontleft.isBusy() && frontright.isBusy() && backleft.isBusy() && backright.isBusy()){
        
    }
        //stopMotors();
        

    }
    public void moveLeft(int distance, double power){
        frontleft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        frontright.setMode(DcMotor.RunMode.RESET_ENCODERS);
        backleft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        backright.setMode(DcMotor.RunMode.RESET_ENCODERS);
        
        frontleft.setTargetPosition(distance);
        frontright.setTargetPosition(distance);
        backleft.setTargetPosition(distance);
        backright.setTargetPosition(distance);

        frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(frontleft.isBusy() && frontright.isBusy() && backleft.isBusy() && backright.isBusy()){
        
    }
        //stopMotors();
        
    }
    
    public void stopMotors(){
        frontleft.setPower(0.0);
        frontright.setPower(0.0);
        backleft.setPower(0.0);
        backright.setPower(0.0);
        sleep(10000);
        frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}

