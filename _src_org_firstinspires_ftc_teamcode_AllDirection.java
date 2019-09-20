/*
Copyright (c) 2017 FIRST. All rights reserved.
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

package org.firstinspires.ftc.competitioncode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.android.AndroidTextToSpeech;
/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
**/


@TeleOp(name = "AllDirection", group = "Concept")


public class AllDirection extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
   
    private DcMotor frontleftmotor = null;
    private DcMotor frontrightmotor = null;
    private DcMotor backleftmotor = null;
    private DcMotor backrightmotor = null;
    private DcMotor hanging1 = null;
    private DcMotor hanging2 = null;
    private DcMotor directMotor1 = null;
    private DcMotor directMotor2 = null;
    private CRServo slider1 = null;
    private CRServo slider2 = null;
    private CRServo arm1 = null;
    private CRServo arm2 = null;
    private CRServo intake1 = null;
    private CRServo intake2 = null;
    

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "DHRUVBOT");
        telemetry.update();

        
       
        frontleftmotor = hardwareMap.get(DcMotor.class, "frontleftmotor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "frontrightmotor");
        backrightmotor = hardwareMap.get(DcMotor.class, "backrightmotor");
        backleftmotor = hardwareMap.get(DcMotor.class, "backleftmotor");
        hanging1 = hardwareMap.get(DcMotor.class, "hanging1");
        hanging2 = hardwareMap.get(DcMotor.class, "hanging2");
        directMotor1 = hardwareMap.get(DcMotor.class, "directmotor1");
        directMotor2 = hardwareMap.get(DcMotor.class, "directmotor2");
        slider1 = hardwareMap.get(CRServo.class, "slider1");
        slider2 = hardwareMap.get(CRServo.class, "slider2");
        arm1 = hardwareMap.get(CRServo.class, "arm1");
        arm2 = hardwareMap.get(CRServo.class, "arm2");
        intake1 = hardwareMap.get(CRServo.class, "intake1");
        intake2 = hardwareMap.get(CRServo.class, "intake2");
       
        
        
        frontleftmotor.setPower(0.0);
        frontrightmotor.setPower(0.0);
        backleftmotor.setPower(0.0);
        backrightmotor.setPower(0.0);
        frontleftmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontrightmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backrightmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleftmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hanging1.setPower(0.0);
        hanging1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hanging2.setPower(0.0);
        hanging2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        directMotor1.setPower(0.0);
        directMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        directMotor2.setPower(0.0);
        directMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slider1.setPower(0.0);
        slider2.setPower(0.0);
        arm1.setPower(0.0);
        arm2.setPower(0.0);
        intake1.setPower(0.0);
        intake2.setPower(0.0);
        
        
        
        
        
        
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double directPower;
            double armPower;
            float lTrigger;
            float rTrigger;

            
            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            
            directPower = -gamepad2.left_stick_y;
            armPower = gamepad2.right_stick_y;
            lTrigger = gamepad2.left_trigger;
            rTrigger = gamepad2.right_trigger;
            
            //This moves the base
            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = r * Math.sin(robotAngle) - rightX;
            final double v2 = -r * Math.cos(robotAngle) - rightX;
            final double v3 = r * Math.cos(robotAngle) - rightX;
            final double v4 = -r * Math.sin(robotAngle) - rightX;

            frontleftmotor.setPower(v1);
            frontrightmotor.setPower(v2);
            backleftmotor.setPower(v3);
            backrightmotor.setPower(v4);
            
            
            // if(gamepad1.left_trigger>0){
            //     frontleftmotor.setPower(gamepad1.left_trigger);
            //     backleftmotor.setPower(-gamepad1.left_trigger);
            //     frontrightmotor.setPower(gamepad1.left_trigger);
            //     backrightmotor.setPower(-gamepad1.left_trigger);
            // }
            // else if(gamepad1.right_trigger>0){
            //     frontleftmotor.setPower(-gamepad1.right_trigger);
            //     backleftmotor.setPower(gamepad1.right_trigger);
            //     frontrightmotor.setPower(-gamepad1.right_trigger);
            //     backrightmotor.setPower(gamepad1.right_trigger);
            // }
            // else{
            //     double ramping = 0.2;
            //     while((ramping < leftMove || ramping < rightMove) && (leftMove > 0.0 || rightMove >0.0)){
            //         ramping += 0.02;
            //         frontleftmotor.setPower(leftMove * ramping);
            //         backleftmotor.setPower(leftMove * ramping);
            //         frontrightmotor.setPower(rightMove * ramping);
            //         backrightmotor.setPower(rightMove * ramping);   
            //     }
            // }
            
            //this controls hanging mechanism
            if(gamepad1.right_bumper){
                hanging1.setPower(-0.75);
                hanging2.setPower(0.75);
            }
            else if(gamepad1.left_bumper){
                hanging1.setPower(0.75);
                hanging2.setPower(-0.75);
            }
            else{
                hanging1.setPower(0.0);
                hanging1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                hanging2.setPower(0.0);
                hanging2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
            
            //this moves the arm that controls the entire input
            if(directPower>0.0 && directPower<=0.7){
                directMotor1.setPower(directPower * 0.5);
                directMotor2.setPower(directPower * -0.5);
                telemetry.addData("Power", directMotor1.getPower());
            }
            else if(directPower<0.0 && directPower>=-0.7){
                directMotor1.setPower(directPower * 0.5);
                directMotor2.setPower(directPower * -05);
                telemetry.addData("Power", directMotor1.getPower());
            }
            else if(directPower>0.7){
                directMotor1.setPower(directPower*0.6);
                directMotor2.setPower(directPower * -0.8);
                telemetry.addData("Power", directMotor1.getPower());
            }
            else if(directPower<-0.7){
                directMotor1.setPower(directPower*0.6);
                directMotor2.setPower(directPower * -0.8);
                telemetry.addData("Power", directMotor1.getPower());
            }
            //this is for holding the arm in position for putting blocks
            //in the lander
            else{
                if(gamepad2.right_bumper == true){
                    directMotor1.setPower(0.2);
                    directMotor2.setPower(-0.2);
                }
                else if(gamepad2.left_bumper == true){
                    directMotor1.setPower(-0.2);
                    directMotor2.setPower(0.2);
                }
                else if(gamepad2.y == true){
                    directMotor1.setPower(-0.25);
                    directMotor2.setPower(0.25);
                }
                else{
                directMotor1.setPower(0.0);
                directMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                directMotor2.setPower(0.0);
                directMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                }
            }
            
            //this controls the second part of the arm
            if(armPower>0.0){
                arm1.setPower(-0.79);
                arm2.setPower(0.79);
            }
            else if(armPower<0.0){
                arm1.setPower(0.79);
                arm2.setPower(-0.79);
            }
            else{
                arm1.setPower(0.0);
                arm2.setPower(0.0);
            }
            //this controls the intake
            if(lTrigger>0.0){
                intake1.setPower(1.0);
                intake2.setPower(-1.0);
            }
            else if(rTrigger>0.0){
                intake1.setPower(-1.0);
                intake2.setPower(1.0);
            }
            else{
                intake1.setPower(0.0);
                intake2.setPower(0.0);
            }
            //this controls the extrusion slider mechanism
            if(gamepad2.a == true){
                slider1.setPower(0.79);
                slider2.setPower(-0.79);
            }
            else if(gamepad2.b == true){
                slider1.setPower(-0.79);
                slider2.setPower(0.79);
            }
            else{
                slider1.setPower(0.0);
                slider2.setPower(0.0);
            }
            
            
            
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "one (%.2f), two (%.2f), servoone (%.2f), servotwo (%.2f)", onePower, twoPower, servo1pos, servo2pos);
            telemetry.update();
        }
    }
}
