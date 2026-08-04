package org.firstinspires.ftc.teamcode.autos;

import android.graphics.Point;

import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class Auto extends LinearOpMode {
    Robot robot;
    Timer timer;
    Pose startingPose = new Pose(14,106,Math.toRadians(0));
    Pose firstPickUpPose = new Pose(58,106,Math.toRadians(0));
    Pose firstBasketPose = new Pose(36,127, Math.toRadians(270));
    Pose secondPickUpPose = new Pose(83,106, Math.toRadians(300));
    Pose secondBasketPose = new Pose(83,127, Math.toRadians(270));
    Pose thirdPickUpPose = new Pose(107,106, Math.toRadians(300));
    Pose thirdBasketPose = new Pose(107,126, Math.toRadians(270));
    Pose parkPose = new Pose(95, 127, Math.toRadians(270));
    Pose controlPoint = new Pose(80, 54);
    PathChain firstPickUpPath, firstBasketPath, secondPickUpPath, secondBasketPath, thirdPickUpPath, thirdBasketPath, parkPath;

    boolean isFirstTimePath = true;
    public void runOpMode(){
        robot = new Robot(hardwareMap);
        timer = new Timer();
        robot.follower.setStartingPose(startingPose);
        waitForStart();
        while(opModeIsActive()){

        }
    }
    public void buildPaths(){
        firstPickUpPath = robot.follower.pathBuilder()
                .addPath(new BezierLine(startingPose, firstPickUpPose))
                .setConstantHeadingInterpolation(firstPickUpPose.getHeading())
                .build();
        firstBasketPath = robot.follower.pathBuilder()
                .addPath(new BezierLine(firstPickUpPose, firstBasketPose))
                .setLinearHeadingInterpolation(firstPickUpPose.getHeading(), firstBasketPose.getHeading())
                .build();
        secondPickUpPath = robot.follower.pathBuilder()
                .addPath(new BezierLine(firstBasketPose, secondPickUpPose))
                .setLinearHeadingInterpolation(firstBasketPose.getHeading(), secondPickUpPose.getHeading())
                .build();
        secondBasketPath = robot.follower.pathBuilder()
                .addPath(new BezierLine(secondPickUpPose, secondBasketPose))
                .setLinearHeadingInterpolation(secondPickUpPose.getHeading(), secondBasketPose.getHeading())
                .build();
        thirdPickUpPath = robot.follower.pathBuilder()
                .addPath(new BezierLine(secondBasketPose, thirdPickUpPose))
                .setLinearHeadingInterpolation(secondBasketPose.getHeading(), thirdPickUpPose.getHeading())
                .build();
        thirdBasketPath = robot.follower.pathBuilder()
                .addPath(new BezierLine(thirdPickUpPose, thirdBasketPose))
                .setLinearHeadingInterpolation(thirdPickUpPose.getHeading(), thirdBasketPose.getHeading())
                .build();
        parkPath = robot.follower.pathBuilder()
                .addPath(new BezierCurve(thirdBasketPose, controlPoint, parkPose))
                .setLinearHeadingInterpolation(thirdBasketPose.getHeading(), parkPose.getHeading())
                .build();

    }

}
