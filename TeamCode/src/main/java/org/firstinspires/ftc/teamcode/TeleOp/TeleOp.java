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
        robot = new Robot(hardwareMap);
        robot.driveTrain.setBrakeMode();
        currentGamepad = new Gamepad();
        previousGamepad = new Gamepad();
        previousGamepad.copy(currentGamepad);
        currentGamepad.copy(gamepad1);
        waitForStart();

        while (opModeIsActive()){


            robot.driveTrain.drive(gamepad1);

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
            }
            if(currentGamepad.cross && !previousGamepad.cross){
                robot.transfer.setHoldMode();
            }
            if(currentGamepad.dpad_up && !previousGamepad.dpad_up){
                robot.elevator.setUpPosition();
            }
            if(currentGamepad.dpad_down && !previousGamepad.dpad_down){
                robot.elevator.setDownPosition();
            }
            robot.elevator.elevatorTask();
            robot.transfer.intakeUpdate();
            telemetry.addData("current position", robot.elevator.getCurrentPosition());
            telemetry.addData("target position", robot.elevator.getTargetPosition());
            telemetry.update();


        }
    }
}
