package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.controllers.PidfController;

public class Elevator {
    DcMotorEx misumiLeft;
    DcMotorEx misumiRight;
    Servo leftServo;
    Servo rightServo;
    double kP, kD, kI;
    double leftGrippyPosition;
    double rightGrippyPosition;
    double leftLoosePosition;
    double rightLoosePosition;

    int targetPosition;
    final int ELEVATOR_TOLERANCE = 50;
    PidfController elevatorPID;

    public Elevator(HardwareMap hwMap){
        misumiLeft = hwMap.get(DcMotorEx.class, "misumiLeft");
        misumiRight = hwMap.get(DcMotorEx.class, "misumiRight");
        leftServo = hwMap.get(Servo.class, "leftServo");
        rightServo = hwMap.get(Servo.class, "rightServo");

        misumiLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        misumiLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        misumiLeft.setPower(0);

        misumiRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        misumiRight.setDirection(DcMotorSimple.Direction.FORWARD);
        misumiRight.setPower(0);


        elevatorPID = new PidfController(kP, kI, kD);
    }
    public int getTargetPosition(){
        return targetPosition;
    }
    public void setTargetPosition(int targetPosition){
        this.targetPosition = targetPosition;
    }
    public boolean isFinished(){
        return Math.abs(targetPosition - misumiLeft.getCurrentPosition())<= ELEVATOR_TOLERANCE;
    }
    public void grippyServo(){
        leftServo.setPosition(leftGrippyPosition);
        rightServo.setPosition(rightGrippyPosition);
    }

    public void looseServo(){
        leftServo.setPosition(leftLoosePosition);
        rightServo.setPosition(rightLoosePosition);
    }
    public void elevatorTask(){
        double output = elevatorPID.calculate(targetPosition, misumiLeft.getCurrentPosition());
        misumiLeft.setPower(output);
        misumiRight.setPower(output);
    }


}
