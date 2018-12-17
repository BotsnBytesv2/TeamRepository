package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="BackofthePinion", group="Concept")

public class BackofthePinion extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftarm = null;
    private DcMotor rightarm = null;

    
    @Override
    public void runOpMode(){
        leftarm = hardwareMap.get(DcMotor.class, "leftarm");
        rightarm = hardwareMap.get(DcMotor.class, "rightarm");
        
        leftarm.setPower(0);
        rightarm.setPower(0);
        
        waitForStart();
        runtime.reset();
        
        while (opModeIsActive()) {
            
        double leftarmPower;
        double rightarmPower;
        
        leftarmPower = gamepad1.right_stick_y ;
        rightarmPower = gamepad1.right_stick_y;
        
        leftarm.setPower(leftarmPower);
        rightarm.setPower(-rightarmPower);
        
        // Show the elapsed game time and wheel power.
            telemetry.addData("Hanging Initialized", "Run Time: " + runtime.toString());
            
            telemetry.addData("Motors", "Ready to Hang");
            telemetry.update();
            
    }

}
}
