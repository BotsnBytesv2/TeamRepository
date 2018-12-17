package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;


@Autonomous(name = "MecanumSuperTest", group = "Concept")

public class MecanumSuperTest extends LinearOpMode {
    
  
    

   
    private ElapsedTime runtime = new ElapsedTime();
    
    
    private DcMotor frontleftmotor = null;
    private DcMotor frontrightmotor = null;
    private DcMotor backleftmotor = null;
    private DcMotor backrightmotor = null;
    
    
  

    @Override
    public void runOpMode() {
        frontleftmotor = hardwareMap.get(DcMotor.class, "frontleftmotor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "frontrightmotor");
        backrightmotor = hardwareMap.get(DcMotor.class, "backrightmotor");
        backleftmotor = hardwareMap.get(DcMotor.class, "backleftmotor");
        //frontleftmotor.setDirection(DcMotor.Direction.REVERSE);
        //backleftmotor.setDirection(DcMotor.Direction.REVERSE);
        
        waitForStart();
        test();
        
       }
    

   
    private void moveForward(){
      frontrightmotor.setPower(0.4);
      frontleftmotor.setPower(-0.4);
      backrightmotor.setPower(0.4);
      backleftmotor.setPower(-0.4);
      
    }
    
    
    
    private void spinleft(){
      frontrightmotor.setPower(0.4);
      frontleftmotor.setPower(0.4);
      backrightmotor.setPower(0.4);
      backleftmotor.setPower(0.4);
    }
    
    private void spinright(){
      frontrightmotor.setPower(-0.4);
      frontleftmotor.setPower(-0.4);
      backrightmotor.setPower(-0.4);
      backleftmotor.setPower(-0.4);
    }
    
    private void straferight() {
      frontleftmotor.setPower(-0.5);
      backleftmotor.setPower(0.5);
      frontrightmotor.setPower(-0.5);
      backrightmotor.setPower(0.5);
    }
    
    private void strafeleft() {
      frontleftmotor.setPower(0.5);
      backleftmotor.setPower(-0.5);
      frontrightmotor.setPower(0.5);
      backrightmotor.setPower(-0.5);
      }
      
    private void test() {
        //moveForward();
      moveForward();
      sleep(300);
      stop();
        
    }
}
