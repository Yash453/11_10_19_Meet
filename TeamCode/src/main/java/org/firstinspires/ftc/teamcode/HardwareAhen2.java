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

//import com.disnodeteam.dogecv.CameraViewDisplay;
//import com.disnodeteam.dogecv.DogeCV;
//import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
//import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import android.app.Activity;
import android.view.View;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.GyroSensor;
//import org.opencv.core.Size;
//import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.qualcomm.robotcore.hardware.DigitalChannel;


/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class HardwareAhen2
{

//    static final double MARKER_RETRACTED = 0.45;
//    static final double MARKER_EXTENDED = 0.9;
//
//    public SamplingOrderDetector detector;

    /* Public OpMode members. */
    public DcMotor  frontLeft   = null;
    public DcMotor  frontRight  = null;
    public DcMotor  backLeft    = null;
    public DcMotor  backRight   = null;
    public DcMotor tray = null;
    public DcMotor lift = null;
    public Servo clawLeft = null;
    public Servo clawRight = null;
    public Servo  autoServo          = null;
//    public DcMotor  lift        = null;

//    //ModernRoboticsI2cRangeSensor range;
    IntegratingGyroscope gyro;
    ModernRoboticsI2cGyro modernRoboticsI2cGyro;
//
//    IntegratingGyroscope gyro2;
//    ModernRoboticsI2cGyro modernRoboticsI2cGyro2;
//    public DigitalChannel digitalTouch;
    ColorSensor sensorColor;
    DistanceSensor sensorDistance;
    ColorSensor sensorColor2;
    DistanceSensor sensorDistance2;

//
//
//    public Servo sampling = null;
//    public Servo teamMarker = null;

//      rjberry@rjcontrol.com




    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareAhen2(){ }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        frontLeft  = hwMap.get(DcMotor.class, "FrontLeft");
        frontRight = hwMap.get(DcMotor.class, "FrontRight");
        backLeft  = hwMap.get(DcMotor.class, "BackLeft");
        backRight = hwMap.get(DcMotor.class, "BackRight");
        tray = hwMap.get(DcMotor.class, "tray");
        lift = hwMap.get(DcMotor.class, "lift");
        clawLeft = hwMap.get(Servo.class, "clawLeft");
        clawRight = hwMap.get(Servo.class, "clawRight");
        autoServo = hwMap.get(Servo.class, "autoServo");
//        lift = hwMap.get(DcMotor.class, "Lift");
//
//
        modernRoboticsI2cGyro = hwMap.get(ModernRoboticsI2cGyro.class, "Gyro");
        gyro = (IntegratingGyroscope)modernRoboticsI2cGyro;
//        modernRoboticsI2cGyro2 = hwMap.get(ModernRoboticsI2cGyro.class, "Gyro2");
//        gyro2 = (IntegratingGyroscope)modernRoboticsI2cGyro;

        sensorColor = hwMap.get(ColorSensor.class, "sensorColorDistance");
        sensorDistance = hwMap.get(DistanceSensor.class, "sensorColorDistance");
        sensorColor2    = hwMap.get(ColorSensor.class, "sensorColorDistance2");
        sensorDistance2 = hwMap.get(DistanceSensor.class, "sensorColorDistance2");

//        digitalTouch = hwMap.get(DigitalChannel.class, "MagneticSensor"); // configure as REV Touch Sensor
//        digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        frontLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        frontRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        backLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        backRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        tray.setDirection(DcMotor.Direction.FORWARD); // Set to Forward
        lift.setDirection(DcMotor.Direction.FORWARD);
        clawLeft.setDirection((Servo.Direction.FORWARD));
        clawRight.setDirection((Servo.Direction.REVERSE));
        // Set all motors to zero power
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        tray.setPower(0);
        lift.setPower(0);
//        clawLeft.setPosition(0.7);
//        clawRight.setPosition(0.3);
//        autoServo.setPosition(0.7);
//        lift.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

//        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hwMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hwMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hwMap.appContext).findViewById(relativeLayoutId);
    }
}

/* stuff to do in order of importance

- take pictures of gyro angular velocity telemetry and gyro straight correction for notebook
- test ConceptRampMotorSpeed (just make sure it works)
- fix gyro straight/turn coefficients (make it smoother) (depending on time)
- test touch sensor (depending on time)

- phone angle for SamplingOrderDetector
- encoder unlatching
- accurate mineral alignment maybe using GoldAlignDetector?
- team marker. either a physical mechanism or robot has to actually go around?
- touching crater (using Vuforia??)

- all the above for the Crater side

- limit switch (which is programmed just like a REV touch sensor (SensorDigitalTouch))
- gyro strafe
- switch front and back maybe
- lerp / accelerate motors when starting motion

*/

