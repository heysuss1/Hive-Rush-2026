package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

public class DriveTrain {
    DcMotorEx lf;
    DcMotorEx lb;
    DcMotorEx rf;
    DcMotorEx rb;


    private double speed;

    public DriveTrain(HardwareMap hwMap){
        lf = hwMap.get(DcMotorEx.class, "lf");
        lb = hwMap.get(DcMotorEx.class, "lb");
        rf = hwMap.get(DcMotorEx.class, "rf");
        rb = hwMap.get(DcMotorEx.class, "rb");
        speed = 1;

        lf.setDirection(DcMotorSimple.Direction.REVERSE);
        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        rf.setDirection(DcMotorSimple.Direction.FORWARD);
        rb.setDirection(DcMotorSimple.Direction.FORWARD);

        lf.setPower(0);
        lb.setPower(0);
        rf.setPower(0);
        rb.setPower(0);
    }

    public double getSpeed(){
        return speed;
    }
    public void setSpeed(double speed){
        this.speed = Range.clip(speed, -1, 1);
    }
    public void setPower(double lfPower, double lbPower, double rfPower, double rbPower){
        lf.setPower(lfPower);
        lb.setPower(lbPower);
        rf.setPower(rfPower);
        rb.setPower(rbPower);
    }
    public void drive(Gamepad gamepad1){
        double forward =
                -(Math.atan(5 * gamepad1.left_stick_y) / Math.atan(5));

        double sideways =
                Math.atan(5 * gamepad1.left_stick_x) / Math.atan(5);

        double turning =
                (Math.atan(5 * gamepad1.right_stick_x) / Math.atan(5)) * 0.5;

        double frontLeft = forward - sideways - turning;
        double backLeft = forward + sideways - turning;
        double backRight = forward + sideways + turning;
        double frontRight = forward - sideways + turning;

        double max = Math.max(
                Math.abs(frontLeft),
                Math.max(
                        Math.abs(backLeft),
                        Math.max(
                                Math.abs(backRight),
                                Math.abs(frontRight)
                        )
                )
        );

        double scaleFactor = speed / Math.max(1.0, max);

        setPower(
                frontLeft * scaleFactor,
                backLeft * scaleFactor,
                backRight * scaleFactor,
                frontRight * scaleFactor
        );
    }
    public void setBrakeMode(){
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

}
