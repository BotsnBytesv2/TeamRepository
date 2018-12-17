package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "FinaleTeleops2Code", group = "Concept")


public class HolonomicTeleopswitharmandspinner extends LinearOpMode {

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
        //input.setPower(0);
        inputArm.setPower(0.0);
        oneMotor.setPower(0.0);
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
            
            
            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            
            
          
            //This section is for the motor that lifts the input arm
            if(gamepad2.left_stick_y<0.0){
                oneMotor.setPower(0.3 * -gamepad2.left_stick_y);
            }
            else if(gamepad2.left_stick_y>0.0){
                oneMotor.setPower(0.3 * -gamepad2.left_stick_y);
            }       
            else{
                oneMotor.setPower(0.0);
            }
            //This section is for the servo that controls the input
            if(gamepad2.left_bumper){
                input.setPower(1.0);
                telemetry.addData("Pressed", "LeftTrigger");
                
            }
            
            else if(gamepad2.right_bumper){
                input.setPower(-1.0);
                telemetry.addData("Pressed", "RightTrigger");
                
            }
            else{
                sleep(160);
                input.setPower(0.0);
            }
            // This section is for the lift system
            if (gamepad2.left_trigger>0.0){
                leftArm.setPower(gamepad2.left_trigger * 0.75);
                rightArm.setPower(gamepad2.left_trigger * -0.75);
            }
            else if (gamepad2.right_trigger>0.0){
                leftArm.setPower(gamepad2.right_trigger * -0.75);
                rightArm.setPower(gamepad2.right_trigger * 0.75);
            }
            else{
                leftArm.setPower(0.0);
                rightArm.setPower(0.0);
            }
            //This entire section is for the drivetrain
            //This area is for spinning 
            if(gamepad1.left_trigger>0){
                frontleftmotor.setPower(gamepad1.left_trigger * 0.3);
                backleftmotor.setPower(gamepad1.left_trigger * 0.3);
                frontrightmotor.setPower(gamepad1.left_trigger * 0.3);
                backrightmotor.setPower(gamepad1.left_trigger * 0.3);
            }
            else if(gamepad1.right_trigger>0){
                frontleftmotor.setPower(-gamepad1.right_trigger * 0.3);
                backleftmotor.setPower(-gamepad1.right_trigger * 0.3);
                frontrightmotor.setPower(-gamepad1.right_trigger * 0.3);
                backrightmotor.setPower(-gamepad1.right_trigger * 0.3);
            }
            //This area is for forward and backward
            else if(gamepad1.left_stick_y<0.0){
                frontleftmotor.setPower(gamepad1.left_stick_y*0.55);
                backleftmotor.setPower(-gamepad1.left_stick_y*0.55);
                frontrightmotor.setPower(gamepad1.left_stick_y*0.55);
                backrightmotor.setPower(-gamepad1.left_stick_y*0.55);
            }
            else if(gamepad1.left_stick_y>0.0){
                frontleftmotor.setPower(gamepad1.left_stick_y*0.55);
                backleftmotor.setPower(-gamepad1.left_stick_y*0.55);
                frontrightmotor.setPower(gamepad1.left_stick_y*0.55);
                backrightmotor.setPower(-gamepad1.left_stick_y*0.55);
            }
            //This area is for strafing
            else if(gamepad1.right_stick_x>0.0){
                frontleftmotor.setPower(gamepad1.right_stick_x*0.55);
                backleftmotor.setPower(gamepad1.right_stick_x*0.55);
                frontrightmotor.setPower(-gamepad1.right_stick_x*0.55);
                backrightmotor.setPower(-gamepad1.right_stick_x*0.55);
            }
            else if(gamepad1.right_stick_x<0.0){
                frontleftmotor.setPower(gamepad1.right_stick_x*0.55);
                backleftmotor.setPower(gamepad1.right_stick_x*0.55);
                frontrightmotor.setPower(-gamepad1.right_stick_x*0.55);
                backrightmotor.setPower(-gamepad1.right_stick_x*0.55);
            }
            // THis is what happens when i don't do anythin in life
            else {
                frontleftmotor.setPower(0.0);
                backleftmotor.setPower(0.0);
                frontrightmotor.setPower(0.0);
                backrightmotor.setPower(0.0);
            }
            if(gamepad2.a){
                inputArm.setPower(1.0);
            }
            else if(gamepad2.b){
                inputArm.setPower(-1.0);
            }
            else{
                inputArm.setPower(0.0);
            }
            
        
            /* Tank Mode uses one stick to control each wheel.
            - This requires no math, but it is hard to drive forward slowly and keep straight.
            leftPower  = -gamepad1.left_stick_y ;
            rightPower = -gamepad1.right_stick_y ;
            */

            // Send calculated power to wheels
            //This is for the back of the pinion arm for the input system
            
            

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
        
        
    }
}
