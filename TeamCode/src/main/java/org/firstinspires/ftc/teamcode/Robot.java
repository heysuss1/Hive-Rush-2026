package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Transfer;

public class Robot {
    public DriveTrain driveTrain;
    public Transfer transfer;
    public Elevator elevator;
    public Follower follower;

    public Robot(HardwareMap hwMap){
        driveTrain = new DriveTrain(hwMap);
        transfer = new Transfer(hwMap);
        elevator = new Elevator(hwMap);
        follower = Constants.createFollower(hwMap);
    }

}
