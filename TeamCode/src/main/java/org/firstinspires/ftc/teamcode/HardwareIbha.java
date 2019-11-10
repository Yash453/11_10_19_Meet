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
//import com.disnodeteam.dogecv.CameraViewDisplay;
//import com.disnodeteam.dogecv.DogeCV;
//import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
//import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
//import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
//import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
//import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
//import com.qualcomm.robotcore.hardware.ColorSensor;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.hardware.GyroSensor;
//import org.opencv.core.Size;
//import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
//import com.qualcomm.robotcore.hardware.DigitalChannel;
//
//
///**
// * This is NOT an opmode.
// *
// * This class can be used to define all the specific hardware for a single robot.
// * In this case that robot is a Pushbot.
// * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
// *
// * This hardware class assumes the following device names have been configured on the robot:
// * Note:  All names are lower case and some have single spaces between words.
// *
// * Motor channel:  Left  drive motor:        "left_drive"
// * Motor channel:  Right drive motor:        "right_drive"
// * Motor channel:  Manipulator drive motor:  "left_arm"
// * Servo channel:  Servo to open left claw:  "left_hand"
// * Servo channel:  Servo to open right claw: "right_hand"
// */
//public class HardwareIbha
//{
//
////    static final double MARKER_RETRACTED = 0.45;
////    static final double MARKER_EXTENDED = 0.9;
////
//    public SamplingOrderDetector detector;
//
//    /* Public OpMode members. */
//    public DcMotor  frontLeft   = null;
//    public DcMotor  frontRight  = null;
//    public DcMotor  backLeft    = null;
//    public DcMotor  backRight   = null;
//    public DcMotor  lift        = null;
//
//    //ModernRoboticsI2cRangeSensor range;
//    IntegratingGyroscope gyro;
//    ModernRoboticsI2cGyro modernRoboticsI2cGyro;
//
//    IntegratingGyroscope gyro2;
//    ModernRoboticsI2cGyro modernRoboticsI2cGyro2;
//    public DigitalChannel digitalTouch;
//
//
//    public Servo sampling = null;
//    public Servo teamMarker = null;
//
////      rjberry@rjcontrol.com
//
//
//    public static final double MID_SERVO       =  0.5 ;
//
//    /* local OpMode members. */
//    HardwareMap hwMap           =  null;
//    private ElapsedTime period  = new ElapsedTime();
//
//    /* Constructor */
//    public HardwareIbha(){ }
//
//    /* Initialize standard Hardware interfaces */
//    public void init(HardwareMap ahwMap) {
//        // Save reference to Hardware map
//        hwMap = ahwMap;
//
//        // Define and Initialize Motors
//        frontLeft  = hwMap.get(DcMotor.class, "FrontLeft");
//        frontRight = hwMap.get(DcMotor.class, "FrontRight");
//        backLeft  = hwMap.get(DcMotor.class, "BackLeft");
//        backRight = hwMap.get(DcMotor.class, "BackRight");
//        lift = hwMap.get(DcMotor.class, "Lift");
//
//
//        modernRoboticsI2cGyro = hwMap.get(ModernRoboticsI2cGyro.class, "Gyro");
//        gyro = (IntegratingGyroscope)modernRoboticsI2cGyro;
//        modernRoboticsI2cGyro2 = hwMap.get(ModernRoboticsI2cGyro.class, "Gyro2");
//        gyro2 = (IntegratingGyroscope)modernRoboticsI2cGyro;
//
//        digitalTouch = hwMap.get(DigitalChannel.class, "MagneticSensor"); // configure as REV Touch Sensor
//        digitalTouch.setMode(DigitalChannel.Mode.INPUT);
//
//
//
//        frontLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
//        frontRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
//        backLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
//        backRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
//
//        // Set all motors to zero power
//        frontLeft.setPower(0);
//        frontRight.setPower(0);
//        backLeft.setPower(0);
//        backRight.setPower(0);
//        lift.setPower(0);
//
//        // Set all motors to run without encoders.
//        // May want to use RUN_USING_ENCODERS if encoders are installed.
//        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
////         Define and initialize ALL installed servos.
//        sampling = hwMap.get(Servo.class, "Sampling");
//        teamMarker = hwMap.get(Servo.class, "TeamMarker");
//
//        sampling.setPosition(0);    // These are the values to be tested
////        teamMarker.setPosition(MARKER_RETRACTED);
//
//
//        // Set up detector
//        detector = new SamplingOrderDetector(); // Create the detector
//        detector.init(hwMap.appContext, CameraViewDisplay.getInstance()); // Initialize detector with app context and camera
//        detector.useDefaults(); // Set detector to use default settings
//
//        detector.downscale = 0.4; // How much to downscale the input frames
//
//        // Optional tuning
//        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
//        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
//        detector.maxAreaScorer.weight = 0.001;
//
//        detector.ratioScorer.weight = 15;
//        detector.ratioScorer.perfectRatio = 1.0;
//
//        detector.enable(); // Start detector
//
//
//
//        // Set up detector
//        detector = new SamplingOrderDetector(); // Create detector
//        detector.init(hwMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera
//        detector.useDefaults(); // Set detector to use default settings
//        // Optional tuning
//        detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
//        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
//        detector.downscale = 0.4; // How much to downscale the input frames
//
//        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
//        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
//        detector.maxAreaScorer.weight = 0.005; //
//
//        detector.ratioScorer.weight = 5; //
//        detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment
//
//        detector.enable(); // Start the detector!
//    }
//}
//
// /* stuff to do in order of importance
//
// - take pictures of gyro angular velocity telemetry and gyro straight correction for notebook
// - test ConceptRampMotorSpeed (just make sure it works)
// - fix gyro straight/turn coefficients (make it smoother) (depending on time)
// - test touch sensor (depending on time)
//
// - phone angle for SamplingOrderDetector
// - encoder unlatching
// - accurate mineral alignment maybe using GoldAlignDetector?
// - team marker. either a physical mechanism or robot has to actually go around?
// - touching crater (using Vuforia??)
//
// - all the above for the Crater side
//
// - limit switch (which is programmed just like a REV touch sensor (SensorDigitalTouch))
// - gyro strafe
// - switch front and back maybe
// - lerp / accelerate motors when starting motion
//
//*/