/* Copyright (c) 2017 FIRST. All rights reserved.
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
import com.qualcomm.robotcore.hardware.CRServo;
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
 */

@TeleOp(name = "HolonmoicTeleopCodeOneGamepad", group = "Concept")
public class HolonomicDriveTrainV2_Copy extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor oneMotor = null;
    private CRServo inputArm = null;
    private CRServo input = null;
    private DcMotor leftArm = null;
    private DcMotor rightArm = null;
    private DcMotor frontleftmotor = null;
    private DcMotor frontrightmotor = null;
    private DcMotor backleftmotor = null;
    private DcMotor backrightmotor = null;
    
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        //oneMotor  = hardwareMap.get(DcMotor.class, "oneMotor");
        oneMotor  = hardwareMap.get(DcMotor.class, "twoMotor");
        leftArm = hardwareMap.get(DcMotor.class, "leftArm");
        rightArm = hardwareMap.get(DcMotor.class, "rightArm");
        input = hardwareMap.get(CRServo.class, "input2");
        inputArm = hardwareMap.get(CRServo.class, "in");
        frontleftmotor = hardwareMap.get(DcMotor.class, "frontleftmotor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "frontrightmotor");
        backrightmotor = hardwareMap.get(DcMotor.class, "backrightmotor");
        backleftmotor = hardwareMap.get(DcMotor.class, "backleftmotor");
        leftArm.setPower(0.0);
        rightArm.setPower(0.0);
        input.setPower(0.0);
        inputArm.setPower(0.0);
        oneMotor.setPower(0.0);
        frontleftmotor.setPower(0.0);
        frontrightmotor.setPower(0.0);
        backleftmotor.setPower(0.0);
        backrightmotor.setPower(0.0);
        
        frontleftmotor.setDirection(DcMotor.Direction.REVERSE);
        backleftmotor.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double frontleftPower;
            double frontrightPower;
            double backleftPower;
            double backrightPower;
            double oneMotorPower;
            //double twoMotorPower;
            double intakeArmPower;
            double intakePower;
            double leftArmPower;
            double rightArmPower;
            //double inputPower;
            //double inputReversePower;
            // left stick controls direction
        // right stick X controls rotation

        //float gamepad1LeftY = -gamepad1.left_stick_y;
        //float gamepad1LeftX = gamepad1.left_stick_x;
        //float gamepad1RightX = gamepad1.right_stick_x;
        //float gamepad1RightY = -gamepad1.right_stick_y;
        
        //formulas for holonomic drive
        
        frontleftPower =  -gamepad1.left_stick_y;
        frontrightPower = -gamepad1.right_stick_y;
        backrightPower = -gamepad1.right_stick_y;
        backleftPower = -gamepad1.left_stick_y;
        oneMotorPower = -gamepad2.left_stick_y;
        
        //frontleftPower =  -gamepad1.right_stick_x;
        frontrightPower = gamepad1.right_stick_x;
        //backrightPower = gamepad1.right_stick_x;
        backleftPower = -gamepad1.right_stick_x;
        //inputPower = gamepad1.right_trigger;
        //inputReversePower = gamepad1.left_trigger;
        //intakePower = gamepad2.right_stick_y;
        //intakeArmPower = gamepad2.left_stick_y;
        // frontrightPower= gamepad1.right_stick_x;
        // backleftPower= gamepad1.right_stick_x;
        
        if (gamepad1.left_bumper){
                inputArm.setPower(0.5);
                //rightArm.setPower(-0.5);
            }
            else if (gamepad1.right_bumper){
                inputArm.setPower(-0.5);
                //rightArm.setPower(0.5);
            }
            else{
                inputArm.setPower(0.0);
                //rightArm.setPower(0.0);
            }
            if(gamepad1.a){
                input.setPower(1.0);
            }
            else if(gamepad1.b){
                input.setPower(-1.0);
            }
            else{
                input.setPower(0.0);
            }
            if (gamepad1.left_trigger>0.0){
                oneMotor.setPower(gamepad1.left_trigger);
                
            }
            else if (gamepad1.right_trigger>0.0){
               oneMotor.setPower(-gamepad1.right_trigger);
               
            }
            else{
                oneMotor.setPower(0.0);
                
            }
            if (gamepad1.x){
               leftArm.setPower(0.6);
                rightArm.setPower(-0.6);
            }
            else if(gamepad1.y){
                leftArm.setPower(-0.6);
                rightArm.setPower(0.6);
            }
            else{
                leftArm.setPower(0.0);
                rightArm.setPower(-0.0);
            }
        //float leftArm = gamepad1.

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            //double drive = gamepad1.left_stick_y;
            //double turn  =  gamepad1.right_stick_x;
            //leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            //rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;
            frontleftmotor.setPower(frontleftPower * 0.5);
            frontrightmotor.setPower(frontrightPower * 0.5);
            backrightmotor.setPower(backrightPower * 0.5);
            backleftmotor.setPower(backleftPower * 0.5);
            oneMotor.setPower(oneMotorPower);
            //oneMotor.setPower(oneMotorPower);
            //intake.setPower(inputPower);
            //intake.setPower(-inputReversePower);
            
            //for values not to exceed -1 or 1
            //frontleftmotor = Range.clip(frontleftmotor, -1,1);
            //frontrightmotor = Range.clip(frontrightmotor, -1,1);
            //backleftmotor = Range.clip(backleftmotor, -1,1);
            //backrightmotor = Range.clip(backrightmotor, -1,1);
            

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels
            //leftDrive.setPower(leftPower);
            //rightDrive.setPower(rightPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        
    }
}}
