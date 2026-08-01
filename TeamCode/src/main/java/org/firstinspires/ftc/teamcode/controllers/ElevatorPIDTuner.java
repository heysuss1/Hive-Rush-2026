package org.firstinspires.ftc.teamcode.controllers;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;
@Config
@TeleOp(name = "elevator tuner")
public class ElevatorPIDTuner extends LinearOpMode {
    Robot robot;
    Telemetry telemetryA;
    public static double kP, kI, kD;
    public static int targetPosition;
    FtcDashboard dashboard;
    public void runOpMode(){
        robot = new Robot(hardwareMap);
        dashboard = FtcDashboard.getInstance();
        telemetryA = new MultipleTelemetry(this.telemetry, dashboard.getTelemetry());
        waitForStart();
        while(opModeIsActive()){
            robot.elevator.elevatorPID.setPidCoefficients(kP, kI, kD);
            robot.elevator.setTargetPosition(targetPosition);
            robot.elevator.elevatorTask();
            telemetryA.addData("target position", robot.elevator.getTargetPosition());
            telemetryA.addData("current position", robot.elevator.getCurrentPosition());
            telemetryA.update();
        }
    }
}