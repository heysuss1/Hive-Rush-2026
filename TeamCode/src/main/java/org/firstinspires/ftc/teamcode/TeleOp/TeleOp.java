package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
public class TeleOp extends LinearOpMode {
    Robot robot;
    Gamepad currentGamepad;
    Gamepad previousGamepad;
    public void runOpMode(){
        while (opModeIsActive()){
            robot = new Robot(hardwareMap);
            currentGamepad = new Gamepad();
            previousGamepad = new Gamepad();
            previousGamepad.copy(currentGamepad);
            currentGamepad.copy(gamepad1);
            waitForStart();
            if(currentGamepad.square && !previousGamepad.square){
                robot.transfer.setSpewMode();
            }
            if(currentGamepad.circle && !previousGamepad.circle){
                robot.transfer.setReleaseMode();
            }
            if(currentGamepad.right_bumper && !previousGamepad.right_bumper){
                robot.transfer.setIntakeMode();
            }
            if(currentGamepad.dpad_left && !previousGamepad.dpad_left){
                robot.elevator.looseServo();
            }
            if(currentGamepad.dpad_right && !previousGamepad.dpad_right){
                robot.elevator.grippyServo();
            }
            if(currentGamepad.right_trigger > 0.1){
                robot.driveTrain.setSpeed(0.25);
            }else {
                robot.driveTrain.setSpeed(1);
                //dpad up slide up   down down  lright trigger slow mode
            }


        }
    }
}
