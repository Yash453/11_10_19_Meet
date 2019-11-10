///* Copyright (c) 2017 FIRST. All rights reserved.
// *
// * Redistribution and use in source and binary forms, with or without modification,
// * are permitted (subject to the limitations in the disclaimer below) provided that
// * the following conditions are met:
// *
// * Redistributions of source code must retain the above copyright notice, this list
// * of conditions and the following disclaimer.
// *
// * Redistributions in binary form must reproduce the above copyright notice, this
// * list of conditions and the following disclaimer in the documentation and/or
// * other materials provided with the distribution.
// *
// * Neither the name of FIRST nor the names of its contributors may be used to endorse or
// * promote products derived from this software without specific prior written permission.
// *
// * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
// * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
// * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
// * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
// * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
// * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
// * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
// */
//
//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
//import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.GyroSensor;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.util.Range;
//
//import org.firstinspires.ftc.robotcontroller.external.samples.*;
//import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//
//// Neha was here!
//// Redbots 4 lyfe.
//// Ayush wants to write something here but I'm telling him no.
//// Ayush is a FRESHIE
//// Aneesh is the freshie prince of Bell Air
//// Reema is the freshiest of them all
//
///**
// * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
// * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
// * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
// * class is instantiated on the Robot Controller and executed.
// *
// * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
// * It includes all the skeletal structure that all linear OpModes contain.
// *
// * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
// * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
// */
//
//@TeleOp(name="BadTeleop", group="Linear Opmode")
//@Disabled
//public class AmeerLinearTeleOp extends LinearOpMode {
//
//    // Declare OpMode members.
//    HardwareRedbot robot = new HardwareRedbot();
//    private ElapsedTime runtime = new ElapsedTime();
//    GyroSensor sensorGyro;
////    ModernRoboticsI2cGyro gyro    = null;
////    ModernRoboticsI2cRangeSensor rangeSensor;
//    static final double     movementMotorSpeed          = 0.6;
//    @Override
//    public void runOpMode() throws InterruptedException {
//        robot.init(hardwareMap);
//
////        sensorGyro = hardwareMap.gyroSensor.get("gyro");  //Point to the gyro in the configuration file
////        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "sensor_range");
////        gyro = (ModernRoboticsI2cGyro) sensorGyro;      //ModernRoboticsI2cGyro allows us to .getIntegratedZValue()
//        // Send telemetry message to signify robot waiting;
////        telemetry.addData("Say", "Make sure the \"MODE\" light is off. ");    //
////        updateTelemetry(telemetry);
//
//        robot.frontLeft.setDirection(DcMotor.Direction.REVERSE);
//        robot.frontRight.setDirection(DcMotor.Direction.FORWARD);
//        robot.backLeft.setDirection(DcMotor.Direction.REVERSE);
//        robot.backRight.setDirection(DcMotor.Direction.FORWARD);
//
//        robot.servoJewelUpDown.setPosition(.137);
//        robot.servoJewelLeftRight.setPosition(.392);
//
//        // Wait for the game to start (driver presses PLAY)
//        waitForStart();
////        gyro.calibrate();
////        while (gyro.isCalibrating()) {
////            idle();
////        }
////        robot.servoJewelLeftRight.setPosition(0.235);//2
////        robot.servoJewelUpDown.setPosition(0.80);//1
//        // run until the end of the match (driver presses STOP)
//        while (opModeIsActive()) {
//
//            while (gamepad1.dpad_up) {
//                robot.frontLeft.setPower(movementMotorSpeed);
//                robot.backLeft.setPower(movementMotorSpeed);
//                robot.frontRight.setPower(movementMotorSpeed);
//                robot.backRight.setPower(movementMotorSpeed);
//                while (gamepad2.y) {
//                    robot.contServoGlyphRight.setPosition(0);
//                    robot.contServoGlyphLeft.setPosition(1);
//                    robot.contServoGlyphLeftUp.setPosition(1);
//                    robot.contServoGlyphRightUp.setPosition(0);
//                    if (!gamepad1.dpad_up) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.y) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                }
//                while (gamepad2.a) {
//                    robot.contServoGlyphRight.setPosition(1);
//                    robot.contServoGlyphLeft.setPosition(0);
//                    robot.contServoGlyphLeftUp.setPosition(0);
//                    robot.contServoGlyphRightUp.setPosition(1);
//                    if (!gamepad1.dpad_up) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.a) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                }
//                while (gamepad2.dpad_right) {
//                    robot.rightIntake.setPower(-.5);
//                    robot.leftIntake.setPower(.5);
//                    if (!gamepad1.dpad_up) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.dpad_right) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                }
//                if (!gamepad1.dpad_up) {
//                    robot.frontLeft.setPower(0);
//                    robot.backLeft.setPower(0);
//                    robot.frontRight.setPower(0);
//                    robot.backRight.setPower(0);
//                }
//            }
//            if (!gamepad2.dpad_right) {
//                robot.leftIntake.setPower(0);
//                robot.rightIntake.setPower(0);
//            }
//            if (!gamepad2.y) {
//                robot.contServoGlyphRight.setPosition(.5);
//                robot.contServoGlyphLeft.setPosition(.5);
//                robot.contServoGlyphLeftUp.setPosition(.5);
//                robot.contServoGlyphRightUp.setPosition(.5);
//            }
//            if (!gamepad2.a) {
//                robot.contServoGlyphRight.setPosition(.5);
//                robot.contServoGlyphLeft.setPosition(.5);
//                robot.contServoGlyphLeftUp.setPosition(.5);
//                robot.contServoGlyphRightUp.setPosition(.5);
//            }
//            while (gamepad1.dpad_down) {
//                robot.frontLeft.setPower(-movementMotorSpeed);
//                robot.backLeft.setPower(-movementMotorSpeed);
//                robot.frontRight.setPower(-movementMotorSpeed);
//                robot.backRight.setPower(-movementMotorSpeed);
//                while (gamepad2.y) {
//                    robot.contServoGlyphRight.setPosition(0);
//                    robot.contServoGlyphLeft.setPosition(1);
//                    robot.contServoGlyphLeftUp.setPosition(1);
//                    robot.contServoGlyphRightUp.setPosition(0);
//                    if (!gamepad1.dpad_down) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.y) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                }
//                while (gamepad2.a) {
//                    robot.contServoGlyphRight.setPosition(1);
//                    robot.contServoGlyphLeft.setPosition(0);
//                    robot.contServoGlyphLeftUp.setPosition(0);
//                    robot.contServoGlyphRightUp.setPosition(1);
//                    if (!gamepad1.dpad_down) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.a) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                }
//
//                while (gamepad2.dpad_right) {
//                    robot.rightIntake.setPower(-.5);
//                    robot.leftIntake.setPower(.5);
//                    if (!gamepad1.dpad_down) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.dpad_right) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                }
////                if (!gamepad2.dpad_right) {
////                    robot.rightIntake.setPower(0);
////                    robot.leftIntake.setPower(0);
////                }
//                if (!gamepad1.dpad_down) {
//                    robot.frontLeft.setPower(0);
//                    robot.backLeft.setPower(0);
//                    robot.frontRight.setPower(0);
//                    robot.backRight.setPower(0);
//                }
//            }
//            while (gamepad1.right_bumper) {
//                robot.frontLeft.setPower(movementMotorSpeed);
//                robot.backLeft.setPower(movementMotorSpeed);
//                robot.frontRight.setPower(-movementMotorSpeed);
//                robot.backRight.setPower(-movementMotorSpeed);
//                while (gamepad2.y) {
//                    robot.contServoGlyphRight.setPosition(0);
//                    robot.contServoGlyphLeft.setPosition(1);
//                    robot.contServoGlyphLeftUp.setPosition(1);
//                    robot.contServoGlyphRightUp.setPosition(0);
//                    if (!gamepad1.right_bumper) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.y) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                }
//                while (gamepad2.a) {
//                    robot.contServoGlyphRight.setPosition(1);
//                    robot.contServoGlyphLeft.setPosition(0);
//                    robot.contServoGlyphLeftUp.setPosition(0);
//                    robot.contServoGlyphRightUp.setPosition(1);
//                    if (!gamepad1.right_bumper) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.a) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                }
////                if (!gamepad2.y)
////                {
////                    robot.contServoGlyphRight.setPosition(.5);
////                    robot.contServoGlyphLeft.setPosition(.5);
////                }
////                if (!gamepad2.a)
////                {
////                    robot.contServoGlyphRight.setPosition(.5);
////                    robot.contServoGlyphLeft.setPosition(.5);
////                }
//                while (gamepad2.dpad_right) {
//                    robot.rightIntake.setPower(-.5);
//                    robot.leftIntake.setPower(.5);
//                    if (!gamepad1.right_bumper) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.dpad_right) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                }
//                if (!gamepad1.right_bumper) {
//                    robot.frontLeft.setPower(0);
//                    robot.backLeft.setPower(0);
//                    robot.frontRight.setPower(0);
//                    robot.backRight.setPower(0);
//                }
//            }
//            while (gamepad1.left_bumper) {
//                robot.frontLeft.setPower(-movementMotorSpeed);
//                robot.backLeft.setPower(-movementMotorSpeed);
//                robot.frontRight.setPower(movementMotorSpeed);
//                robot.backRight.setPower(movementMotorSpeed);
//                while (gamepad2.y) {
//                    robot.contServoGlyphRight.setPosition(0);
//                    robot.contServoGlyphLeft.setPosition(1);
//                    robot.contServoGlyphLeftUp.setPosition(1);
//                    robot.contServoGlyphRightUp.setPosition(0);
//                    if (!gamepad1.left_bumper) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.y) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                }
//                while (gamepad2.a) {
//                    robot.contServoGlyphRight.setPosition(1);
//                    robot.contServoGlyphLeft.setPosition(0);
//                    robot.contServoGlyphLeftUp.setPosition(0);
//                    robot.contServoGlyphRightUp.setPosition(1);
//                    if (!gamepad1.left_bumper) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.a) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                }
//                while (gamepad2.dpad_right) {
//                    robot.rightIntake.setPower(-.5);
//                    robot.leftIntake.setPower(.5);
//                    if (!gamepad1.left_bumper) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.dpad_right) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                }
//                if (!gamepad1.left_bumper) {
//                    robot.frontLeft.setPower(0);
//                    robot.backLeft.setPower(0);
//                    robot.frontRight.setPower(0);
//                    robot.backRight.setPower(0);
//                }
//            }
//            while (gamepad1.dpad_right) {
//                robot.frontLeft.setPower(movementMotorSpeed);
//                robot.backLeft.setPower(-movementMotorSpeed);
//                robot.frontRight.setPower(-movementMotorSpeed);
//                robot.backRight.setPower(movementMotorSpeed);
//                while (gamepad2.y) {
//                    robot.contServoGlyphRight.setPosition(0);
//                    robot.contServoGlyphLeft.setPosition(1);
//                    robot.contServoGlyphLeftUp.setPosition(1);
//                    robot.contServoGlyphRightUp.setPosition(0);
//                    if (!gamepad1.dpad_right) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.y) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                }
//                while (gamepad2.a) {
//                    robot.contServoGlyphRight.setPosition(1);
//                    robot.contServoGlyphLeft.setPosition(0);
//                    robot.contServoGlyphLeftUp.setPosition(0);
//                    robot.contServoGlyphRightUp.setPosition(1);
//                    if (!gamepad1.dpad_right) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.a) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                }
//                while (gamepad2.dpad_right) {
//                    robot.rightIntake.setPower(-.5);
//                    robot.leftIntake.setPower(.5);
//                    if (!gamepad1.dpad_right) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.dpad_right) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                }
//                if (!gamepad1.dpad_right) {
//                    robot.frontLeft.setPower(0);
//                    robot.backLeft.setPower(0);
//                    robot.frontRight.setPower(0);
//                    robot.backRight.setPower(0);
//                }
//            }
//            while (gamepad1.dpad_left) {
//                robot.frontLeft.setPower(-movementMotorSpeed);
//                robot.backLeft.setPower(movementMotorSpeed);
//                robot.frontRight.setPower(movementMotorSpeed);
//                robot.backRight.setPower(-movementMotorSpeed);
//                while (gamepad2.y) {
//                    robot.contServoGlyphRight.setPosition(0);
//                    robot.contServoGlyphLeft.setPosition(1);
//                    robot.contServoGlyphLeftUp.setPosition(1);
//                    robot.contServoGlyphRightUp.setPosition(0);
//                    if (!gamepad1.dpad_left) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.y) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                }
//                while (gamepad2.a) {
//                    robot.contServoGlyphRight.setPosition(1);
//                    robot.contServoGlyphLeft.setPosition(0);
//                    robot.contServoGlyphLeftUp.setPosition(0);
//                    robot.contServoGlyphRightUp.setPosition(1);
//                    if (!gamepad1.dpad_left) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.a) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                }
//                while (gamepad2.dpad_right) {
//                    robot.rightIntake.setPower(-.5);
//                    robot.leftIntake.setPower(.5);
//                    if (!gamepad1.dpad_left) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                    if (!gamepad2.dpad_right) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                }
//                if (!gamepad1.dpad_left) {
//                    robot.frontLeft.setPower(0);
//                    robot.backLeft.setPower(0);
//                    robot.frontRight.setPower(0);
//                    robot.backRight.setPower(0);
//                }
//            }
//            while (gamepad2.dpad_right) {
//                robot.rightIntake.setPower(-.5);
//                robot.leftIntake.setPower(.5);
//                while (gamepad1.dpad_up) {
//                    robot.frontLeft.setPower(movementMotorSpeed);
//                    robot.backLeft.setPower(movementMotorSpeed);
//                    robot.frontRight.setPower(movementMotorSpeed);
//                    robot.backRight.setPower(movementMotorSpeed);
//                    if (!gamepad2.dpad_right) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                    if (!gamepad1.dpad_up) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
//                while (gamepad1.dpad_down) {
//                    robot.frontLeft.setPower(-movementMotorSpeed);
//                    robot.backLeft.setPower(-movementMotorSpeed);
//                    robot.frontRight.setPower(-movementMotorSpeed);
//                    robot.backRight.setPower(-movementMotorSpeed);
//                    if (!gamepad2.dpad_right) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                    if (!gamepad1.dpad_down) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
//                while (gamepad1.right_bumper) {
//                    robot.frontLeft.setPower(movementMotorSpeed);
//                    robot.backLeft.setPower(movementMotorSpeed);
//                    robot.frontRight.setPower(-movementMotorSpeed);
//                    robot.backRight.setPower(-movementMotorSpeed);
//                    if (!gamepad2.dpad_right) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                    if (!gamepad1.right_bumper) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
//                while (gamepad1.left_bumper) {
//                    robot.frontLeft.setPower(-movementMotorSpeed);
//                    robot.backLeft.setPower(-movementMotorSpeed);
//                    robot.frontRight.setPower(movementMotorSpeed);
//                    robot.backRight.setPower(movementMotorSpeed);
//                    if (!gamepad2.dpad_right) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                    if (!gamepad1.left_bumper) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
//                while (gamepad1.dpad_right) {
//                    robot.frontLeft.setPower(movementMotorSpeed);
//                    robot.backLeft.setPower(-0.5);
//                    robot.frontRight.setPower(-movementMotorSpeed);
//                    robot.backRight.setPower(0.8);
//                    if (!gamepad2.dpad_right) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                    if (!gamepad1.dpad_right) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
//                while (gamepad1.dpad_left) {
//                    robot.frontLeft.setPower(-movementMotorSpeed);
//                    robot.backLeft.setPower(.5);
//                    robot.frontRight.setPower(movementMotorSpeed);
//                    robot.backRight.setPower(-.8);
//                    if (!gamepad2.dpad_right) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                    if (!gamepad1.dpad_left) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
//                if (!gamepad2.dpad_right) {
//                    robot.rightIntake.setPower(0);
//                    robot.leftIntake.setPower(0);
//                }
//            }
//            while (gamepad2.dpad_left) {
//                robot.rightIntake.setPower(.5);
//                robot.leftIntake.setPower(-.5);
//                while (gamepad1.dpad_up) {
//                    robot.frontLeft.setPower(movementMotorSpeed);
//                    robot.backLeft.setPower(movementMotorSpeed);
//                    robot.frontRight.setPower(movementMotorSpeed);
//                    robot.backRight.setPower(movementMotorSpeed);
//                    if (!gamepad2.dpad_left) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                    if (!gamepad1.dpad_up) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
//                while (gamepad1.dpad_down) {
//                    robot.frontLeft.setPower(-movementMotorSpeed);
//                    robot.backLeft.setPower(-movementMotorSpeed);
//                    robot.frontRight.setPower(-movementMotorSpeed);
//                    robot.backRight.setPower(-movementMotorSpeed);
//                    if (!gamepad2.dpad_left) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                    if (!gamepad1.dpad_down) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
//                while (gamepad1.right_bumper) {
//                    robot.frontLeft.setPower(movementMotorSpeed);
//                    robot.backLeft.setPower(movementMotorSpeed);
//                    robot.frontRight.setPower(-movementMotorSpeed);
//                    robot.backRight.setPower(-movementMotorSpeed);
//                    if (!gamepad2.dpad_left) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                    if (!gamepad1.right_bumper) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
//                while (gamepad1.left_bumper) {
//                    robot.frontLeft.setPower(-movementMotorSpeed);
//                    robot.backLeft.setPower(-movementMotorSpeed);
//                    robot.frontRight.setPower(movementMotorSpeed);
//                    robot.backRight.setPower(movementMotorSpeed);
//                    if (!gamepad2.dpad_left) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                    if (!gamepad1.left_bumper) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
//                while (gamepad1.dpad_right) {
//                    robot.frontLeft.setPower(movementMotorSpeed);
//                    robot.backLeft.setPower(-0.5);
//                    robot.frontRight.setPower(-movementMotorSpeed);
//                    robot.backRight.setPower(0.8);
//                    if (!gamepad2.dpad_left) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                    if (!gamepad1.dpad_right) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
//                while (gamepad1.dpad_left) {
//                    robot.frontLeft.setPower(-movementMotorSpeed);
//                    robot.backLeft.setPower(0.5);
//                    robot.frontRight.setPower(movementMotorSpeed);
//                    robot.backRight.setPower(-0.8);
//                    if (!gamepad2.dpad_left) {
//                        robot.rightIntake.setPower(0);
//                        robot.leftIntake.setPower(0);
//                    }
//                    if (!gamepad1.dpad_left) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
//                if (!gamepad2.dpad_left) {
//                    robot.rightIntake.setPower(0);
//                    robot.leftIntake.setPower(0);
//                }
//            }
//            while (gamepad2.y) {
//                robot.contServoGlyphRight.setPosition(0);
//                robot.contServoGlyphLeft.setPosition(1);
//                robot.contServoGlyphLeftUp.setPosition(1);
//                robot.contServoGlyphRightUp.setPosition(0);
//                robot.servoGlyphRight.setPosition(.62);
//                robot.servoGlyphLeft.setPosition(.46);
//                robot.servoGlyphIn.setPosition(.5294);
//                while (gamepad1.dpad_up) {
//                    robot.frontLeft.setPower(movementMotorSpeed);
//                    robot.backLeft.setPower(movementMotorSpeed);
//                    robot.frontRight.setPower(movementMotorSpeed);
//                    robot.backRight.setPower(movementMotorSpeed);
//                    if (!gamepad2.y) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                    if (!gamepad1.dpad_up) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
////                if (!gamepad1.dpad_up) {
////                    robot.frontLeft.setPower(0);
////                    robot.backLeft.setPower(0);
////                    robot.frontRight.setPower(0);
////                    robot.backRight.setPower(0);
////                }
//                while (gamepad1.dpad_down) {
//                    robot.frontLeft.setPower(-movementMotorSpeed);
//                    robot.backLeft.setPower(-movementMotorSpeed);
//                    robot.frontRight.setPower(-movementMotorSpeed);
//                    robot.backRight.setPower(-movementMotorSpeed);
//                    if (!gamepad2.y) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                    if (!gamepad1.dpad_down) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
////                if (!gamepad1.dpad_down) {
////                    robot.frontLeft.setPower(0);
////                    robot.backLeft.setPower(0);
////                    robot.frontRight.setPower(0);
////                    robot.backRight.setPower(0);
////                }
//                while (gamepad1.right_bumper) {
//                    robot.frontLeft.setPower(movementMotorSpeed);
//                    robot.backLeft.setPower(movementMotorSpeed);
//                    robot.frontRight.setPower(-movementMotorSpeed);
//                    robot.backRight.setPower(-movementMotorSpeed);
//                    if (!gamepad2.y) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                    if (!gamepad1.right_bumper) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
////                if (!gamepad1.right_bumper) {
////                    robot.frontLeft.setPower(0);
////                    robot.backLeft.setPower(0);
////                    robot.frontRight.setPower(0);
////                    robot.backRight.setPower(0);
////                }
//                while (gamepad1.left_bumper) {
//                    robot.frontLeft.setPower(-movementMotorSpeed);
//                    robot.backLeft.setPower(-movementMotorSpeed);
//                    robot.frontRight.setPower(movementMotorSpeed);
//                    robot.backRight.setPower(movementMotorSpeed);
//                    if (!gamepad2.y) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                    if (!gamepad1.left_bumper) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
////                if (!gamepad1.left_bumper) {
////                    robot.frontLeft.setPower(0);
////                    robot.backLeft.setPower(0);
////                    robot.frontRight.setPower(0);
////                    robot.backRight.setPower(0);
////                }
//                while (gamepad1.dpad_right) {
//                    robot.frontLeft.setPower(movementMotorSpeed);
//                    robot.backLeft.setPower(-0.5);
//                    robot.frontRight.setPower(-movementMotorSpeed);
//                    robot.backRight.setPower(0.8);
//                    if (!gamepad2.y) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                    if (!gamepad1.dpad_right) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
////                if (!gamepad1.dpad_right) {
////                    robot.frontLeft.setPower(0);
////                    robot.backLeft.setPower(0);
////                    robot.frontRight.setPower(0);
////                    robot.backRight.setPower(0);
////                }
//                while (gamepad1.dpad_left) {
//                    robot.frontLeft.setPower(-movementMotorSpeed);
//                    robot.backLeft.setPower(0.5);
//                    robot.frontRight.setPower(movementMotorSpeed);
//                    robot.backRight.setPower(-0.8);
//                    if (!gamepad2.y) {
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                    if (!gamepad1.dpad_left) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
////                if (!gamepad1.dpad_left) {
////                    robot.frontLeft.setPower(0);
////                    robot.backLeft.setPower(0);
////                    robot.frontRight.setPower(0);
////                    robot.backRight.setPower(0);
////                }
//                if (!gamepad2.y) {
//                    robot.contServoGlyphRight.setPosition(.5);
//                    robot.contServoGlyphLeft.setPosition(.5);
//                    robot.contServoGlyphLeftUp.setPosition(.5);
//                    robot.contServoGlyphRightUp.setPosition(.5);
//                }
//            }
//            while (gamepad2.a) {
//                robot.contServoGlyphRight.setPosition(1);
//                robot.contServoGlyphLeft.setPosition(0);
//                robot.contServoGlyphLeftUp.setPosition(0);
//                robot.contServoGlyphRightUp.setPosition(1);
//
//                while (gamepad1.dpad_up) {
//                    robot.frontLeft.setPower(movementMotorSpeed);
//                    robot.backLeft.setPower(movementMotorSpeed);
//                    robot.frontRight.setPower(movementMotorSpeed);
//                    robot.backRight.setPower(movementMotorSpeed);
//                    if (!gamepad2.a) {
//                        robot.servoGlyphIn.setPosition(1);
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                    if (!gamepad1.dpad_up) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
////                if (!gamepad1.dpad_up) {
////                    robot.frontLeft.setPower(0);
////                    robot.backLeft.setPower(0);
////                    robot.frontRight.setPower(0);
////                    robot.backRight.setPower(0);
////                }
//                while (gamepad1.dpad_down) {
//                    robot.frontLeft.setPower(-movementMotorSpeed);
//                    robot.backLeft.setPower(-movementMotorSpeed);
//                    robot.frontRight.setPower(-movementMotorSpeed);
//                    robot.backRight.setPower(-movementMotorSpeed);
//                    if (!gamepad2.a) {
//                        robot.servoGlyphIn.setPosition(1);
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                    if (!gamepad1.dpad_down) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
////                if (!gamepad1.dpad_down) {
////                    robot.frontLeft.setPower(0);
////                    robot.backLeft.setPower(0);
////                    robot.frontRight.setPower(0);
////                    robot.backRight.setPower(0);
////                }
//                while (gamepad1.right_bumper) {
//                    robot.frontLeft.setPower(movementMotorSpeed);
//                    robot.backLeft.setPower(movementMotorSpeed);
//                    robot.frontRight.setPower(-movementMotorSpeed);
//                    robot.backRight.setPower(-movementMotorSpeed);
//                    if (!gamepad2.a) {
//                        robot.servoGlyphIn.setPosition(1);
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                    if (!gamepad1.right_bumper) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
////                if (!gamepad1.right_bumper) {
////                    robot.frontLeft.setPower(0);
////                    robot.backLeft.setPower(0);
////                    robot.frontRight.setPower(0);
////                    robot.backRight.setPower(0);
////                }
//                while (gamepad1.left_bumper) {
//                    robot.frontLeft.setPower(-movementMotorSpeed);
//                    robot.backLeft.setPower(-movementMotorSpeed);
//                    robot.frontRight.setPower(movementMotorSpeed);
//                    robot.backRight.setPower(movementMotorSpeed);
//                    if (!gamepad2.a) {
//                        robot.servoGlyphIn.setPosition(1);
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                    if (!gamepad1.left_bumper) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
////                if (!gamepad1.left_bumper) {
////                    robot.frontLeft.setPower(0);
////                    robot.backLeft.setPower(0);
////                    robot.frontRight.setPower(0);
////                    robot.backRight.setPower(0);
////                }
//                while (gamepad1.dpad_right) {
//                    robot.frontLeft.setPower(movementMotorSpeed);
//                    robot.backLeft.setPower(-0.5);
//                    robot.frontRight.setPower(-movementMotorSpeed);
//                    robot.backRight.setPower(0.8);
//                    if (!gamepad2.a) {
//                        robot.servoGlyphIn.setPosition(1);
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                    if (!gamepad1.dpad_right) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
////                if (!gamepad1.dpad_right) {
////                    robot.frontLeft.setPower(0);
////                    robot.backLeft.setPower(0);
////                    robot.frontRight.setPower(0);
////                    robot.backRight.setPower(0);
////                }
//                while (gamepad1.dpad_left) {
//                    robot.frontLeft.setPower(-movementMotorSpeed);
//                    robot.backLeft.setPower(0.5);
//                    robot.frontRight.setPower(movementMotorSpeed);
//                    robot.backRight.setPower(-0.8);
//                    if (!gamepad2.a) {
//                        robot.servoGlyphIn.setPosition(1);
//                        robot.contServoGlyphRight.setPosition(.5);
//                        robot.contServoGlyphLeft.setPosition(.5);
//                        robot.contServoGlyphLeftUp.setPosition(.5);
//                        robot.contServoGlyphRightUp.setPosition(.5);
//                    }
//                    if (!gamepad1.dpad_left) {
//                        robot.frontLeft.setPower(0);
//                        robot.backLeft.setPower(0);
//                        robot.frontRight.setPower(0);
//                        robot.backRight.setPower(0);
//                    }
//                }
////                if (!gamepad1.dpad_left) {
////                    robot.frontLeft.setPower(0);
////                    robot.backLeft.setPower(0);
////                    robot.frontRight.setPower(0);
////                    robot.backRight.setPower(0);
////                }
//                if (!gamepad2.a) {
//                    robot.servoGlyphIn.setPosition(1);
//                    robot.contServoGlyphRight.setPosition(.5);
//                    robot.contServoGlyphLeft.setPosition(.5);
//                    robot.contServoGlyphLeftUp.setPosition(.5);
//                    robot.contServoGlyphRightUp.setPosition(.5);
//                }
//            }
//            while (gamepad2.x) {
//                robot.servoGlyphRight.setPosition(.31);
//                robot.servoGlyphLeft.setPosition(.76);
//                robot.servoGlyphIn.setPosition(1);
//            }
//            while (gamepad2.b) {
//                robot.servoGlyphRight.setPosition(1);
//                robot.servoGlyphLeft.setPosition(.059);
//                robot.servoGlyphIn.setPosition(.11768);
//            }
//            while (gamepad2.right_bumper) {
//                robot.servoGlyphRight.setPosition(.62);
//                robot.servoGlyphLeft.setPosition(.46);
//                robot.servoGlyphIn.setPosition(.5294);
//            }
//            if (!gamepad1.dpad_up) {
//                robot.frontLeft.setPower(0);
//                robot.backLeft.setPower(0);
//                robot.frontRight.setPower(0);
//                robot.backRight.setPower(0);
//            }
//            if (!gamepad1.dpad_down) {
//                robot.frontLeft.setPower(0);
//                robot.backLeft.setPower(0);
//                robot.frontRight.setPower(0);
//                robot.backRight.setPower(0);
//            }
//            if (!gamepad1.dpad_right) {
//                robot.frontLeft.setPower(0);
//                robot.backLeft.setPower(0);
//                robot.frontRight.setPower(0);
//                robot.backRight.setPower(0);
//            }
//            if (gamepad1.x) {
//                robot.motorStop.setPower(1);
//            }
//            if (gamepad1.b) {
//                robot.motorStop.setPower(-1);
//            }
//            if (!gamepad1.x && !gamepad1.b) {
//                robot.motorStop.setPower(0);
//            }
//            if (!gamepad1.dpad_left) {
//                robot.frontLeft.setPower(0);
//                robot.backLeft.setPower(0);
//                robot.frontRight.setPower(0);
//                robot.backRight.setPower(0);
//            }
//            if (gamepad1.left_bumper) {
//                robot.frontLeft.setPower(0);
//                robot.backLeft.setPower(0);
//                robot.frontRight.setPower(0);
//                robot.backRight.setPower(0);
//            }
//            if (gamepad1.right_bumper) {
//                robot.frontLeft.setPower(0);
//                robot.backLeft.setPower(0);
//                robot.frontRight.setPower(0);
//                robot.backRight.setPower(0);
//            }
//
//            // Setup a variable for each drive whee=l to save power level for telemetry
//        }
//    }
//}
