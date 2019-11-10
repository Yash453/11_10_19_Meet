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
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
///**
// * This OpMode ramps a single motor speed up and down repeatedly until Stop is pressed.
// * The code is structured as a LinearOpMode
// *
// * This code assumes a DC motor configured with the name "left_drive" as is found on a pushbot.
// *
// * INCREMENT sets how much to increase/decrease the power each cycle
// * CYCLE_MS sets the update period.
// *
// * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
// * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
// */
//@Autonomous(name = "Edited: Concept: Ramp Motor Speed", group = "Concept")
//// @Disabled
//public class ConceptRampMotorSpeed extends LinearOpMode {
//
//    HardwareIhba robot = new HardwareIhba();
//
//    static final double INCREMENT   = 0.01;     // amount to ramp motor each CYCLE_MS cycle
//    static final int    CYCLE_MS    =   50;     // period of each cycle
//
//    // Define class members
//    double  power   = 0;
//    double ramp = 1;
//    // 0 means no acceleration, 1 means acceleration, -1 means deceleration
//
//    public void accelerate(double maxPower) {
//        while (opModeIsActive() && power < maxPower){
//            power += INCREMENT;
//            powerMotors();
//        }
//        power = maxPower;
//    }
//
//    public void decelerate(double minPower) {
//        while(opModeIsActive() && power > minPower) {
//            power -= INCREMENT;
//            powerMotors();
//        }
//        power = minPower;
//    }
//
//    public void powerMotors() {
//        robot.frontRight.setPower(power);
//        robot.frontLeft.setPower(power);
//        robot.backRight.setPower(power);
//        robot.backLeft.setPower(power);
//        sleep(CYCLE_MS);
//        idle();
//    }
//
//    public void turnMotorsOn(double powerL, double powerR) {
//        robot.frontLeft.setPower(powerL);
//        robot.backLeft.setPower(powerL);
//        robot.frontRight.setPower(powerR);
//        robot.backRight.setPower(powerR);
//    }
//
//    public void move(double powerL, double powerR, long milliseconds) {
//        long distance = milliseconds * 5; // valueInInches = 5
//        turnMotorsOn(powerL, powerR);
//        sleep(distance);
//        turnMotorsOn(0,0);
//        sleep(1000);
//    }
//
//    @Override
//    public void runOpMode() {
//
//        robot.init(hardwareMap);
//
//        // Wait for the start button
//        telemetry.addData(">", "Press Start to run Motors." );
//        telemetry.update();
//        waitForStart();
//
//
//        while(opModeIsActive()) {
//            telemetry.addData(">", "Starting");
//            telemetry.update();
//
//            // accelerates to full speed
//            accelerate(1);
//            telemetry.addData(">", "Accelerating");
//            telemetry.update();
//
//            // goes forward for 1 second
//            move(power, power, 1000);
//            telemetry.addData(">", "Forward");
//            telemetry.update();
//
//            // decelerates to a stop
//            decelerate(0);
//            telemetry.addData(">", "Decelerating");
//            telemetry.update();
//
//            // Turn off motors
//            turnMotorsOn(0, 0);
//            telemetry.addData(">", "Stopped");
//            telemetry.update();
//
//
//            // start going backwards
//            // accelerates to full speed
//            decelerate(-1);
//            telemetry.addData(">", "Accelerating (decelerating) backwards");
//            telemetry.update();
//
//            // goes forward for 1 second
//            move(-power, -power, 1000);
//            telemetry.addData(">", "Backwards");
//            telemetry.update();
//
//            // decelerates to a stop
//            accelerate(0);
//            telemetry.addData(">", "Decelerating (accelerating) backwards");
//            telemetry.update();
//
//            // Turn off motors
//            turnMotorsOn(0, 0);
//            telemetry.addData(">", "Stopped");
//            telemetry.update();
//        }
//
//        telemetry.addData(">", "Done");
//        telemetry.update();
//    }
//}
