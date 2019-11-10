/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are camel case and some have single spaces between words.
 *
 * Motor channel:  Front Left drive motor:     "frontLeft"
 * Motor channel:  Front Right drive motor:    "frontRight"
 * Motor channel:  Back Left  drive motor:     "backLeft"
 * Motor channel:  Back Right drive motor:     "backRight"
 * Motor channel:  Manipulator drive motor:    "leftArm"
 * Motor channel:  Manipulator drive motor:    "linearSlides"
 * Servo channel:  Servo to open left claw:    "leftClaw"
 * Servo channel:  Servo to open right claw:   "rightClaw"
 */
public class HardwareRedbot
{
    static final int    BLUE_LED    = 0;     // Blue LED channel on DIM
    static final int    RED_LED     = 1;     // Red LED Channel on DIM

    /* Public OpMode members. */
    public DcMotor  frontLeft       = null;     // Drive
    public DcMotor  frontRight      = null;     // Drive
    public DcMotor  backLeft        = null;     // Drive
    public DcMotor  backRight       = null;     // Drive

//    public Servo claimingArm = null;    // arm for placing team marker
//    public Servo claw1 = null;    // claw 1?
//    public Servo claw2 = null;    // claw 2?
//    public Servo samplingArm = null;    // arm for sampling




    public DcMotor  leftIntake      = null;     // Left Intake
    public DcMotor  rightIntake     = null;     // Right Intake
    public DcMotor  dropper         = null;     // Dropper

    public Servo    helper          = null;     // servo to help lift plate
    public Servo    leftUp          = null;     // Left tray servo
    public Servo    rightUp         = null;     // Right tray servo

    public Servo    outLeft         = null;   // Should this be Servo or CRServo?
    public Servo    outRight        = null;   // Lifting mechanism servos

    public DeviceInterfaceModule dim = null;

    public ModernRoboticsI2cRangeSensor range = null;
    public ModernRoboticsI2cGyro gyro    = null;
    public ModernRoboticsI2cColorSensor color = null;

    public static final double MID_SERVO            =  0.5 ;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareRedbot(){ }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
//        frontLeft   = hwMap.get(DcMotor.class, "frontLeft");
//        frontRight  = hwMap.get(DcMotor.class, "frontRight");
//        backLeft    = hwMap.get(DcMotor.class, "backLeft");
//        lift    = hwMap.get(DcMotor.class, "lift");
//        backRight   = hwMap.get(DcMotor.class, "backRight");

        leftIntake  = hwMap.get(DcMotor.class, "leftIntake");
        rightIntake = hwMap.get(DcMotor.class, "rightIntake");
        dropper     = hwMap.get(DcMotor.class, "dropper");


        //range = hwMap.get(ModernRoboticsI2cRangeSensor.class, "range");
        gyro = (ModernRoboticsI2cGyro)hwMap.gyroSensor.get("gyro");
        //color = hwMap.get(ModernRoboticsI2cColorSensor.class, "color");

        frontLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        frontRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        backLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        backRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        leftIntake.setPower(0);
        rightIntake.setPower(0);
        dropper.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        dropper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.

//        claimingArm = hwMap.get(Servo.class, "claimingArm");
//        claw1 = hwMap.get(Servo.class, "claw1");
//        claw2 = hwMap.get(Servo.class, "claw2");
//        samplingArm = hwMap.get(Servo.class, "samplingArm");

        helper = hwMap.get(Servo.class, "helper");
        leftUp  = hwMap.get(Servo.class, "leftUp");
        rightUp = hwMap.get(Servo.class, "rightUp");
        outLeft = hwMap.get(Servo.class, "outLeft");
        outRight = hwMap.get(Servo.class, "outRight");

//        claimingArm.setPosition(0);      // find out what servo they are using
//        claw1.setPosition(0);
//        claw2.setPosition(0);
//        samplingArm.setPosition(0);

        helper.setPosition(MID_SERVO);
        leftUp.setPosition(MID_SERVO);
        rightUp.setPosition(MID_SERVO);
        outLeft.setPosition(0.5);
        outRight.setPosition(0.5);

        dim = hwMap.get(DeviceInterfaceModule.class, "Device Interface Module 1");

    }

//    public void rangeAdjust(double inches, double power){
//        if (inches > range.getDistance(DistanceUnit.INCH)) { // move closer to wall/object
//            frontLeft.setPower(power);
//            frontRight.setPower(power);
//            backLeft.setPower(power);
//            backRight.setPower(power);
//        }
//        else if (inches < range.getDistance(DistanceUnit.INCH))  { // move further from wall/object
//            frontLeft.setPower(-power);
//            frontRight.setPower(-power);
//            backLeft.setPower(-power);
//            backRight.setPower(-power);
//        }
//        else if (inches == range.getDistance(DistanceUnit.INCH)){
//            //turn on light
//        }
//    }
}



 /*
 Stuff to Learn:
 -Tele-Op Tank Drive - Done
 -Tele-Op Mecanum Drive - Done
 -Auto Drive by Time - Done
 -Auto Drive using Encoders - Done, mostly
 -Auto Drive using Gyro - Done, mostly
 -Auto Color Sensor - Ehh
 -Auto Range - Done
 -Auto Touch - Done
 -Auto Programming Challenge - Ideally done but needs testing
 -Tele-Op Gyro Strafe - Ideally done but needs testing (and a working gyro sensor)
 -Servo position finder - Done
 -Vuforia - No
 -Wall Following - No
 -LED Light thing - No
 -Color Number for color sensor - Ehh

 -After init of auto, use a range sensor between robot and wall to see if it is placed correctly
    -Method called rangeAdjust in this program


 Auto Programming Challenge:
 -Initialize 4 motors, 1 servo, 1 gyro, 1 range, and 1 color
 -Using encoders, go straight 20 inches
 -turn 90 degrees using gyro
 -turn on range sensor, detect how far away robot is from the wall
 -make the robot go straight until the distance is within 10 centimeters
 -strafe right until senses a black line
 -stop


 Encoder Calculations:
 -Let’s say we want the robot to go forward 26 inches.
 -The diameter of the wheels is 4 inches
 -1120 ticks / 4 inches diameter = 280 ticks per inch
 -280 ticks per inch * 26 inches = 7280 ticks
 -Therefore, we need to program the robot to move 7280 ticks to move 26 inches.

  */