/* Copyright (c) 2018 FIRST. All rights reserved.
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

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.CRServo;
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

/**
 * This 2018-2019 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the gold and silver minerals.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
@Autonomous(name = "Final Mecanum BOB Closer", group = "Concept")
@Disabled
public class MecanumBandBCloser extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final int LEFT =0;
    private static final int CENTER =1;
    private static final int RIGHT =2;
    
    

    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY = "ATWusJz/////AAABmYzqHQl+0kl4p5WlsUb9jL2BkOURyN7eotmsvaYqZ6vfFe2fO9YSQMQaKp8/5z4EOFjr0ZVXCYhJO95G3zsPbrVfcAgCxPtDQ7QiGY+ykVMCw5UuI/nQ4Zh7+dvKIT4CFwNnG4KaWnYXRT9NW2J7noRWQ/vLcEC5tXjlY2inAy7eC0BanVf5aMX/SMl1qm+pWu6TonjVDQlcuBIwGlFWI6nJzNkCyDyQIqgbLrSMMn9kvQgj3lUyxK3HxqqX/XW2Xup/mr7fwQxj6gvTFUlj21CDlx1r9RqizEuUDrN5UwuguNFyc7qbRo44RM98L/Ab81kXVCmfG9JPCGZ5c2mfQ+z0YTjRscHTlWRMBsom83RT";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the Tensor Flow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;
    
    //Initialize motors
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontleftmotor = null;
    private DcMotor frontrightmotor = null;
    private DcMotor backleftmotor = null;
    private DcMotor backrightmotor = null;
    private CRServo teammarker = null;
    
    private int position = -1;
    
    private static double SPEED = 0.3;
    @Override
    public void runOpMode() {
        
        initDriveTrain();
       
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();
        
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
        
        teammarker = hardwareMap.get(CRServo.class, "teammarker");
        teammarker.setPower(0.0);
        
       // checkDeviceCompatibility();

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        //telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
          
          if (tfod != null) {
            tfod.activate();
        }
          //unHook();
          //detect the postion of minerals
          while (opModeIsActive()){
          readPosition();
          
          if(position!=-1){
            break;
            
          }
          }
        
    }
    
            telemetry.addData("Found position", position);
            //telemetry.update();
        
          //move to yellow mineral based on position(left,right,center)
          //moveForward();
           moveToMineral();
          //move to team depot
          //moveToDepot();
          //move to crater
         // moveToCrater();
         
         
      if (tfod != null) {
            tfod.shutdown();
        }
    
      
    }
    
    private void checkDeviceCompatibility(){
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
  }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine
    }
    
    private void readPosition(){
        
      if (tfod != null) {
         //position = LEFT;
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                      telemetry.addData("# Object Detected by BnB is ", updatedRecognitions.size());
                      if (updatedRecognitions.size() == 3) {
                        int goldMineralX = -1;
                        int silverMineral1X = -1;
                        int silverMineral2X = -1;
                        for (Recognition recognition : updatedRecognitions) {
                          //telemetry.addData("Value of object place:", recognition.getLeft());
                          if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getRight();
                            telemetry.addData("Value of object place:", recognition.getLeft());
                          } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                            
                          } else {
                            silverMineral2X = (int) recognition.getLeft();
                          }
                        }
                        if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                          
                          telemetry.addData("Gold Mineral goldMineralX ",goldMineralX);
                          telemetry.addData("Gold Mineral silverMineral1X ", silverMineral1X);
                          telemetry.addData("Gold Mineral silverMineral2X ",silverMineral2X );
                          
                          
                          if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                            telemetry.addData("Gold Mineral Position", "Left");
                            position = LEFT;
                            
                          } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                            telemetry.addData("Gold Mineral Position", "Right");
                            position = RIGHT;
                          } else {
                            telemetry.addData("Gold Mineral Position", "Center");
                            position = CENTER;
                          }
                        }
                      }
                      telemetry.update();
                    }
                }
    }
    private void stayPut(){
      frontrightmotor.setPower(0);
      frontleftmotor.setPower(0);
      backrightmotor.setPower(0);
      backleftmotor.setPower(0);
    }
    private void moveForward(){
      frontrightmotor.setPower(-SPEED);
      frontleftmotor.setPower(SPEED);
      backrightmotor.setPower(-SPEED);
      backleftmotor.setPower(SPEED);
      
    }
    private void moveBackward(){
      frontrightmotor.setPower(SPEED);
      frontleftmotor.setPower(-SPEED);
      backrightmotor.setPower(SPEED);
      backleftmotor.setPower(-SPEED);
    }
    private void moveRight(){
      frontrightmotor.setPower(-SPEED);
      frontleftmotor.setPower(SPEED);
      backrightmotor.setPower(SPEED);
      backleftmotor.setPower(-SPEED);
    }
    private void moveLeft(){
      frontrightmotor.setPower(-SPEED);
      frontleftmotor.setPower(-SPEED);
      backrightmotor.setPower(SPEED);
      backleftmotor.setPower(SPEED);
    }
    private void spinLeft(){
      frontrightmotor.setPower(SPEED);
      frontleftmotor.setPower(SPEED);
      backrightmotor.setPower(SPEED);
      backleftmotor.setPower(SPEED);
    }
    private void spinRight(){
      frontrightmotor.setPower(-SPEED);
      frontleftmotor.setPower(-SPEED);
      backrightmotor.setPower(-SPEED);
      backleftmotor.setPower(-SPEED);
    }
    private void moveToMineral(){
      if (position == LEFT){
        pathLeft();
        
        //sleep(3000);
      }
      else if (position == CENTER){
        pathCenter();
        //moveForward();
        //sleep(500);
        ///spinLeft();
        //sleep(700);
        //moveForward();
        //sleep(1500);
        
        //sleep(3000);
      }else if (position == RIGHT){
        pathRight();
        //moveForward();
        //sleep(300);
        //moveLeft();
        //sleep(500);
        //moveForward();
        //sleep(200);
        //moveLeft();
        //sleep(500);
        //moveForward();
        //sleep(1000);
        
        
        
        //sleep(3000);
      }else{
        moveRight();
        sleep(200);
        //moveRightDiagnol();
        sleep(3000);
      }
      }
    private void pathLeft(){
      spinLeft();
      sleep(270);
      moveForward();
      sleep(1500);
      spinLeft();
      sleep(550);
      moveForward();
      sleep(1300);
      spinLeft();
      sleep(430);
      moveForward();
      sleep(2500);
      spinRight();
      sleep(120);
      moveForward();
      sleep(1000);
      stayPut();
      sleep(1000);
      teammarker.setPower(1.0);
      moveBackward();
      sleep(500);
      spinLeft();
      sleep(250);
      moveBackward();
      sleep(3500);
    }
    private void pathCenter(){
      //spinLeft();
      //sleep(5);
      moveForward();
      sleep(1500);
      moveBackward();
      sleep(700);
      spinLeft();
      sleep(700);
      moveForward();
      sleep(1400);
      spinLeft();
      sleep(300);
      moveForward();
      sleep(2500);
      stayPut();
      sleep(1000);
      teammarker.setPower(1.0);
      spinLeft();
      sleep(50);
      moveBackward();
      sleep(2000);
      spinLeft();
      sleep(20);
      moveBackward();
      sleep(1400);
      
    }
    private void pathRight(){
      spinRight();
      sleep(120);
      moveForward();
      sleep(1700);
      moveBackward();
      sleep(1700);
      spinLeft();
      sleep(120);
      moveForward();
      sleep(800);
      spinLeft();
      sleep(700);
      moveForward();
      sleep(1450);
      spinLeft();
      sleep(350);
      moveForward();
      sleep(2500);
      stayPut();
      sleep(1000);
      teammarker.setPower(1.0);
      spinLeft();
      sleep(50);
      moveBackward();
      sleep(2000);
      spinLeft();
      sleep(20);
      moveBackward();
      sleep(1400);
    }
    private void moveToCrater(){
      spinLeft();
      //spin 135 degrees
      sleep(2000);
      moveForward();
      sleep(4000);
    }
    private void unHook(){
      //Please code for unhooking the robot
    }
    private void initDriveTrain() {
      // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone)
        frontleftmotor  = hardwareMap.get(DcMotor.class, "frontleftmotor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "frontrightmotor");
        backleftmotor = hardwareMap.get(DcMotor.class, "backleftmotor");
        backrightmotor = hardwareMap.get(DcMotor.class, "backrightmotor");
        //arm = hardwareMap.get(DcMotor.class, "armmotor1");
        
        //frontleftmotor.setDirection(DcMotor.Direction.REVERSE);
        //backleftmotor.setDirection(DcMotor.Direction.REVERSE);
        //backleftmotor.setDirection(DcMotor.Direction.REVERSE);
        //arm.setDirection(DcMotor.Direction.REVERSE);
    }
    

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
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
