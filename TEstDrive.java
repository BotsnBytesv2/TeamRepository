
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;


@TeleOp(name = "Concept: TensorFlow Object Detection 3", group = "Concept")

public class TEstDrive extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final int LEFT =0;
    private static final int CENTER =1;
    private static final int RIGHT =2;
    
    


    private static final String VUFORIA_KEY = "ATWusJz/////AAABmYzqHQl+0kl4p5WlsUb9jL2BkOURyN7eotmsvaYqZ6vfFe2fO9YSQMQaKp8/5z4EOFjr0ZVXCYhJO95G3zsPbrVfcAgCxPtDQ7QiGY+ykVMCw5UuI/nQ4Zh7+dvKIT4CFwNnG4KaWnYXRT9NW2J7noRWQ/vLcEC5tXjlY2inAy7eC0BanVf5aMX/SMl1qm+pWu6TonjVDQlcuBIwGlFWI6nJzNkCyDyQIqgbLrSMMn9kvQgj3lUyxK3HxqqX/XW2Xup/mr7fwQxj6gvTFUlj21CDlx1r9RqizEuUDrN5UwuguNFyc7qbRo44RM98L/Ab81kXVCmfG9JPCGZ5c2mfQ+z0YTjRscHTlWRMBsom83RT";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;
    
    //Initialize motors
    private ElapsedTime runtime = new ElapsedTime();
    //private DcMotor oneMotor = null;
    private DcMotor twoMotor = null;
    private DcMotor leftArm = null;
    private DcMotor rightArm = null;
    private DcMotor frontleftmotor = null;
    private DcMotor frontrightmotor = null;
    private DcMotor backleftmotor = null;
    private DcMotor backrightmotor = null;
    //private Servo oneServo = null;
    //private Servo twoServo =null;
    double servo1pos;
    double servo2pos;
    private int position = -1;
    double SPEED = 0.0;
    
    
    
    @Override
    public void runOpMode() {
      waitForStart();
      initDriveTrain();
      while (opModeIsActive()) {
      
       
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        //initVuforia();
        
        //teammarker.setPosition(0.5);
        
       //checkDeviceCompatibility()
        
       SPEED =  -gamepad1.left_stick_y;
        moveForward();
        //sleep(2000);
          //move to yellow mineral based on position(left,right,center)
          //pathLeft();
          //wait(3000);
          //pathCenter();
          //wait(3000);
          /*
          leftArm.setPower(0.5);
          sleep(2000);
          
          rightArm.setPower(-0.5);
          sleep(2000);
          */
          //teammarker.setPosition(0);
        
          
          //wait(3000);
          
          
          
           //moveToMineral();
          //move to team depot
          //moveToDepot();
          //move to crater
         // moveToCrater();
         
         
      }
      
    }
    
    private void checkDeviceCompatibility(){
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
  }

    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine
    }
    
    private void readPosition(){
        
    }
    private void moveForward(){
      //double SPEED = 0.2;
      frontrightmotor.setPower(SPEED);
      frontleftmotor.setPower(SPEED);
      backrightmotor.setPower(SPEED);
      backleftmotor.setPower(SPEED);
    }
    private void moveBackward(){
      //double SPEED = 0.2;
      frontrightmotor.setPower(-SPEED);
      frontleftmotor.setPower(-SPEED);
      backrightmotor.setPower(-SPEED);
      backleftmotor.setPower(-SPEED);
    }
    private void moveLeft(){
      frontrightmotor.setPower(-0.2);
      frontleftmotor.setPower(0.2);
      backrightmotor.setPower(0.2);
      backleftmotor.setPower(-0.2);
    }
    private void moveRight(){
      frontrightmotor.setPower(0.2);
      frontleftmotor.setPower(-0.2);
      backrightmotor.setPower(-0.2);
      backleftmotor.setPower(0.2);
    }
    private void moveLeftDiagnol(){
      moveRight();
      sleep(500);
      moveForward();
      sleep(1000);
      
      
    }
    private void moveRightDiagnol(){
      moveLeft();
      sleep(500);
      moveForward();
      sleep(1000);
      
    }
    private void spin(){
      frontrightmotor.setPower(1.0);
      frontleftmotor.setPower(1.0);
      backrightmotor.setPower(1.0);
      backleftmotor.setPower(1.0);
    }
    private void moveToMineral(){
      if (position == LEFT){
        pathLeft();
        
        //sleep(3000);
      }
      else if (position == CENTER){
        pathCenter();
        
        //sleep(3000);
      }else if (position == RIGHT){
        pathLeft();
        //sleep(3000);
      }else{
        moveRight();
        sleep(200);
        moveRightDiagnol();
        sleep(3000);
        
      }
    telemetry.addData("Moving:",position);
    telemetry.update();
    }
    private void moveToDepot(){
      if (position == LEFT){
        pathLeft();
      }
      else if (position == CENTER){
        pathCenter();
      }else if (position == RIGHT){
        pathRight();
      }else{
        moveLeftDiagnol();
        sleep(3000);
        moveLeft();
        sleep(1000);
      }
      }
    private void moveToCrater(){
      spin();
      //spin 135 degrees
      sleep(2000);
      moveForward();
      sleep(4000);
    }
    private void pathLeft(){
      moveLeftDiagnol();
      moveForward();
      sleep(200);
      moveLeft();
      sleep(400);
      moveForward();
      sleep(700);
    }
    private void pathCenter(){
      moveForward();
      sleep(1700);
      
    }
    private void pathRight(){
      moveRightDiagnol();
      moveForward();
      sleep(200);
      moveRight();
      sleep(400);
      moveForward();
      sleep(700);
    }
    private void unHook(){
      //Please code for unhooking the robot
    }
    private void initDriveTrain() {
      // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone)
        //oneMotor  = hardwareMap.get(DcMotor.class, "oneMotor");
        twoMotor = hardwareMap.get(DcMotor.class, "oneMotor");
        leftArm = hardwareMap.get(DcMotor.class, "leftArm");
        rightArm = hardwareMap.get(DcMotor.class, "rightArm");
        frontleftmotor = hardwareMap.get(DcMotor.class, "frontleftmotor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "frontrightmotor");
        backrightmotor = hardwareMap.get(DcMotor.class, "backrightmotor");
        backleftmotor = hardwareMap.get(DcMotor.class, "backleftmotor");
        //oneServo = hardwareMap.get(Servo.class,"oneServo");
        //twoServo = hardwareMap.get(Servo.class,"twoServo");
        
        frontleftmotor.setDirection(DcMotor.Direction.REVERSE);
        //backleftmotor.setDirection(DcMotor.Direction.REVERSE);
        backleftmotor.setDirection(DcMotor.Direction.REVERSE);
        //arm.setDirection(DcMotor.Direction.REVERSE);
    }
    

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        //TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters();
        //Use above code in game to save battery
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        //tfodParameters.useObjectTracker = true;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}
