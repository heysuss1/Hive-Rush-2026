package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Transfer {
    IntakeStates intakeState = IntakeStates.HOLD;
    DcMotorEx intakes;
    DcMotorEx spits;
    Servo ballBlockerLeft;
    Servo ballBlockerRight;
    RevColorSensorV3 leftColorSensor;
    RevColorSensorV3 rightColorSensor;
    final double HAS_BALL_THRESHOLD = 6.7;
    double blockPosition;
    double openPosition;
    // change to port specific when have driver station
    double speed;
    public Transfer(HardwareMap hwMap){
        intakes = hwMap.get(DcMotorEx.class, "intakes");
        spits = hwMap.get(DcMotorEx.class, "spits");
        ballBlockerLeft = hwMap.get(Servo.class, " leftBlocker");
        ballBlockerRight = hwMap.get(Servo.class, "rightBlocker");

        leftColorSensor = hwMap.get(RevColorSensorV3.class, "leftSensor");
        rightColorSensor = hwMap.get(RevColorSensorV3.class, "rightSensor");


        intakes.setDirection(DcMotorSimple.Direction.FORWARD);
        spits.setDirection(DcMotorSimple.Direction.FORWARD);

        intakes.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        spits.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intakes.setPower(0);
        spits.setPower(0);

    }
    enum IntakeStates{
        INTAKE, SPEW, HOLD, UNMINOR, RELEASE
    }
    public IntakeStates getIntakeState(){
        return intakeState;
    }
    public void setIntakeState(IntakeStates intakeState){
        this.intakeState = intakeState;
    }
    public void intakeUpdate(){
        switch(intakeState) {
            case HOLD:
                setBallBlockerPosition();
                intakes.setPower(0);
                spits.setPower(0);
                break;
            case INTAKE:
                setBallBlockerPosition();
                intakes.setPower(1);
                spits.setPower(1);
                break;
            case SPEW:
                setOpenPosition();
                intakes.setPower(1);
                spits.setPower(1);
                break;
            case UNMINOR:
                setBallBlockerPosition();
                intakes.setPower(-1);
                spits.setPower(0.37);
            case RELEASE:
                setOpenPosition();
                intakes.setPower(-1);
                spits.setPower(-1);
                break;
        }
    }
    public void setHoldMode(){
        setIntakeState(IntakeStates.HOLD);
    }
    public void setIntakeMode(){
        setIntakeState(IntakeStates.INTAKE);
    }
    public void setSpewMode(){
        setIntakeState(IntakeStates.SPEW);
    }
    public void setReleaseMode(){
        setIntakeState(IntakeStates.RELEASE);
    }
    public void setUnminorMode(){ setIntakeState(IntakeStates.UNMINOR); }
    public void setBallBlockerPosition(){
        ballBlockerLeft.setPosition(blockPosition);
        ballBlockerRight.setPosition(blockPosition);
    }
    public void setOpenPosition(){
        ballBlockerLeft.setPosition(openPosition);
        ballBlockerRight.setPosition(openPosition);
    }
    public boolean hasBall(){
        if(rightColorSensor.getDistance(DistanceUnit.INCH) < HAS_BALL_THRESHOLD || leftColorSensor.getDistance(DistanceUnit.INCH) < HAS_BALL_THRESHOLD) {
            return true;
        }
        return false;


    }



}
