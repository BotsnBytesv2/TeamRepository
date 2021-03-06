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
@Autonomous(name = "Concept: TensorFlow Object Detection Test 1", group = "Concept")

public class AutonomousTest1 extends LinearOpMode {
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
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontleftmotor = null;
    private DcMotor frontrightmotor = null;
    private DcMotor backleftmotor = null;
    private DcMotor backrightmotor = null;
    
    private int position = -1;
    

    @Override
    public void runOpMode() {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initDriveTrain();
        initVuforia();
        checkDeviceCompatibility();

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            /** Activate Tensor Flow Object Detection. */
            
            if (tfod != null) {
                tfod.activate();
            }
              while (opModeIsActive()) {
                  unHook();
                  //detect the postion of minerals
                  //move to yellow mineral based on position(left,right,center
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                      telemetry.addData("# Object Detected", updatedRecognitions.size());
                      if (updatedRecognitions.size() == 3) {
                        int goldMineralX = -1;
                        int silverMineral1X = -1;
                        int silverMineral2X = -1;
                        for (Recognition recognition : updatedRecognitions) {
                          if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                          } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                          } else {
                            silverMineral2X = (int) recognition.getLeft();
                          }
                        }
                        if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                          if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                            telemetry.addData("Gold Mineral Position", "Left");
                          } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                            telemetry.addData("Gold Mineral Position", "Right");
                          } else {
                            telemetry.addData("Gold Mineral Position", "Center");
                            moveForward();
                            
                          }
                        }
                      }
                      telemetry.update();
                    }
                
                sleep(3000);
                moveToMineral();
                //move to team depot
                //moveToDepot();
                //move to crater
                //moveToCrater();
          }  
        }
          
        }
      
        if (tfod != null) {
            tfod.shutdown();
        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initDriveTrain() {
      // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone)
        frontleftmotor  = hardwareMap.get(DcMotor.class, "frontleftmotor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "frontrightmotor");
        backleftmotor = hardwareMap.get(DcMotor.class, "backleftmotor");
        backrightmotor = hardwareMap.get(DcMotor.class, "backrightmotor");
        //arm = hardwareMap.get(DcMotor.class, "armmotor1");
        
        frontleftmotor.setDirection(DcMotor.Direction.REVERSE);
        backleftmotor.setDirection(DcMotor.Direction.REVERSE);
        //frontrightmotor.setDirection(DcMotor.Direction.REVERSE);
        //arm.setDirection(DcMotor.Direction.REVERSE);
    }
    private void checkDeviceCompatibility(){
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
  }
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }
    private void moveForward(){
      frontrightmotor.setPower(1.0);
      frontleftmotor.setPower(1.0);
      backrightmotor.setPower(1.0);
      backleftmotor.setPower(1.0);
      
    }
    private void moveBackward(){
      frontrightmotor.setPower(-1.0);
      frontleftmotor.setPower(-1.0);
      backrightmotor.setPower(-1.0);
      backleftmotor.setPower(-1.0);
    }
    private void moveLeft(){
      frontrightmotor.setPower(-1.0);
      frontleftmotor.setPower(1.0);
      backrightmotor.setPower(1.0);
      backleftmotor.setPower(-1.0);
    }
    private void moveRight(){
      frontrightmotor.setPower(0.2);
      frontleftmotor.setPower(-0.2);
      backrightmotor.setPower(-0.2);
      backleftmotor.setPower(0.2);
    }
    private void moveRightDiagnol(){
      frontrightmotor.setPower(0.0);
      frontleftmotor.setPower(0.5);
      backrightmotor.setPower(0.5);
      backleftmotor.setPower(0.0);
    }
    private void moveLeftDiagnol(){
      frontrightmotor.setPower(0.5);
      frontleftmotor.setPower(0.0);
      backrightmotor.setPower(0.0);
      backleftmotor.setPower(0.5);
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
    }
    private void pathLeft(){
      moveLeftDiagnol();
      moveForward();
      sleep(200);
      moveLeft();
      sleep(200);
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
      sleep(200);
      moveForward();
      sleep(700);
    }
    private void moveToDepot(){
      if (position == LEFT){
        moveRightDiagnol();
        sleep(3000);
      }
      else if (position == CENTER){
        moveForward();
        sleep(3000);
      }else if (position == RIGHT){
        moveLeftDiagnol();
        sleep(3000);
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
    private void unHook(){
      //Please code for unhooking the robot
    }

    //Initialize the Tensor Flow Object Detection engine
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}
