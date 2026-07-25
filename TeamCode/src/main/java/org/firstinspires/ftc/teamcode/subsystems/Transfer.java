package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Transfer {
    IntakeStates intakeState = IntakeStates.HOLD;
    enum IntakeStates{
        INTAKE, SPEW, HOLD, RELEASE
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
                intakes.setPower(0);
                spits.setPower(0);
                break;
            case INTAKE:
                intakes.setPower(1);
                spits.setPower(-0.1);
                break;
            case SPEW:
                intakes.setPower(1);
                spits.setPower(1);
                break;
            case RELEASE:
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


    DcMotorEx intakes;
    DcMotorEx spits;
    // change to port specific when have driver station
    double speed;
    public Transfer(HardwareMap hwMap){
        intakes = hwMap.get(DcMotorEx.class, "intakes");
        spits = hwMap.get(DcMotorEx.class, "spits");
        speed = 1;

        intakes.setDirection(DcMotorSimple.Direction.FORWARD);
        spits.setDirection(DcMotorSimple.Direction.FORWARD);

        intakes.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        spits.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intakes.setPower(0);
        spits.setPower(0);

    }



}
