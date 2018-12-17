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

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

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


@TeleOp(name = "FinaleTeleopsCode", group = "Concept")


public class FinaleHolonomicTeleopsCode extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor oneMotor = null;
    private DcMotor twoMotor = null;
    private DcMotor leftArm = null;
    private DcMotor rightArm = null;
    private DcMotor frontleftmotor = null;
    private DcMotor frontrightmotor = null;
    private DcMotor backleftmotor = null;
    private DcMotor backrightmotor = null;
    private Servo oneServo = null;
    private Servo twoServo =null;
    double servo1pos;
    double servo2pos;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        oneMotor  = hardwareMap.get(DcMotor.class, "oneMotor");
        twoMotor = hardwareMap.get(DcMotor.class, "oneMotor");
        leftArm = hardwareMap.get(DcMotor.class, "leftArm");
        rightArm = hardwareMap.get(DcMotor.class, "rightArm");
        frontleftmotor = hardwareMap.get(DcMotor.class, "frontleftmotor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "frontrightmotor");
        backrightmotor = hardwareMap.get(DcMotor.class, "backrightmotor");
        backleftmotor = hardwareMap.get(DcMotor.class, "backleftmotor");
        oneServo = hardwareMap.get(Servo.class,"oneServo");
        twoServo = hardwareMap.get(Servo.class,"twoServo");
        oneServo.setPosition(0.0);
        twoServo.setPosition(0.0);
        oneMotor.setPower(0.0);
        twoMotor.setPower(0.0);
        leftArm.setPower(0.0);
        rightArm.setPower(0.0);
        frontleftmotor.setPower(0.0);
        frontrightmotor.setPower(0.0);
        backleftmotor.setPower(0.0);
        backrightmotor.setPower(0.0);
        
        //frontleftmotor.setDirection(DcMotor.Direction.REVERSE);
        //backleftmotor.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double onePower;
            double twoPower;
            
            

            
            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            onePower = -gamepad2.left_stick_y;
            twoPower = -gamepad2.right_stick_y;
            
            
            
            
            if (gamepad2.x){
                servo1pos = 0.7;
                servo2pos = 0.3;
                oneServo.setPosition(servo1pos);
                twoServo.setPosition(servo2pos);
                telemetry.addLine("OPENED THE CLAW");
            }
            if (gamepad2.y){
                servo1pos = 0.6;
                servo2pos = 0.4;
                oneServo.setPosition(servo1pos);
                twoServo.setPosition(servo2pos);
                telemetry.addLine("ClOSED THE CLAW");
            }
            if (gamepad2.left_bumper){
                leftArm.setPower(0.5);
                rightArm.setPower(-0.5);
            }
            else{
                leftArm.setPower(0.0);
                rightArm.setPower(0.0);
            }
            if (gamepad2.right_bumper){
                leftArm.setPower(-0.5);
                rightArm.setPower(0.5);
            }
            else{
                leftArm.setPower(0.0);
                rightArm.setPower(0.0);
            }
            if(gamepad1.left_stick_y<0.0){
                frontleftmotor.setPower(gamepad1.left_stick_y);
                frontrightmotor.setPower(-gamepad1.left_stick_y);
                backleftmotor.setPower(gamepad1.left_stick_y);
                backrightmotor.setPower(-gamepad1.left_stick_y);
            }
            else{
                frontleftmotor.setPower(0.0);
                frontrightmotor.setPower(0.0);
                backleftmotor.setPower(0.0);
                backrightmotor.setPower(0.0);
            }
            if(gamepad1.left_stick_y>0.0){
                frontleftmotor.setPower(gamepad1.left_stick_y);
                frontrightmotor.setPower(-gamepad1.left_stick_y);
                backleftmotor.setPower(gamepad1.left_stick_y);
                backrightmotor.setPower(-gamepad1.left_stick_y);
            }
            else{
                frontleftmotor.setPower(0.0);
                frontrightmotor.setPower(0.0);
                backleftmotor.setPower(0.0);
                backrightmotor.setPower(0.0);
            }
            if(gamepad1.right_stick_x<0){
                frontleftmotor.setPower(-gamepad1.right_stick_x);
                frontrightmotor.setPower(-gamepad1.right_stick_x);
                backleftmotor.setPower(gamepad1.right_stick_x);
                backrightmotor.setPower(gamepad1.right_stick_x);
            }
            else{
                frontleftmotor.setPower(0.0);
                frontrightmotor.setPower(0.0);
                backleftmotor.setPower(0.0);
                backrightmotor.setPower(0.0);
            }
            if(gamepad1.right_stick_x>0){
                frontleftmotor.setPower(-gamepad1.right_stick_y);
                frontrightmotor.setPower(-gamepad1.right_stick_y);
                backleftmotor.setPower(gamepad1.right_stick_y);
                backrightmotor.setPower(gamepad1.right_stick_y);
            }
            else{
                frontleftmotor.setPower(0.0);
                frontrightmotor.setPower(0.0);
                backleftmotor.setPower(0.0);
                backrightmotor.setPower(0.0);
            }
            if(gamepad1.right_trigger>0){
                frontleftmotor.setPower(-0.75);
                frontrightmotor.setPower(-0.75);
                backleftmotor.setPower(-0.75);
                backrightmotor.setPower(-0.75);
            }
            else{
                frontleftmotor.setPower(0.0);
                frontrightmotor.setPower(0.0);
                backleftmotor.setPower(0.0);
                backrightmotor.setPower(0.0);
            }
            if(gamepad1.left_trigger>0){
                frontleftmotor.setPower(0.75);
                frontrightmotor.setPower(0.75);
                backleftmotor.setPower(0.75);
                backrightmotor.setPower(0.75);
            }
            else{
                frontleftmotor.setPower(0.0);
                frontrightmotor.setPower(0.0);
                backleftmotor.setPower(0.0);
                backrightmotor.setPower(0.0);
            }
            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels
            oneMotor.setPower(onePower);
            twoMotor.setPower(twoPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "one (%.2f), two (%.2f), servoone (%.2f), servotwo (%.2f)", onePower, twoPower, servo1pos, servo2pos);
            telemetry.update();
        }
    }
}
