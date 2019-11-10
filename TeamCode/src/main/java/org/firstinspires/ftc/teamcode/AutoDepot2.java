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
//import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.util.Range;
//
//
//
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
//
///**
// * This file illustrates the concept of driving a path based on Gyro heading and encoder counts.
// * It uses the common Pushbot hardware class to define the drive on the robot.
// * The code is structured as a LinearOpMode
// *
// * The code REQUIRES that you DO have encoders on the wheels,
// *   otherwise you would use: PushbotAutoDriveByTime;
// *
// *  This code ALSO requires that you have a Modern Robotics I2C gyro with the name "gyro"
// *   otherwise you would use: PushbotAutoDriveByEncoder;
// *
// *  This code requires that the drive Motors have been configured such that a positive
// *  power command moves them forward, and causes the encoders to count UP.
// *
// *  This code uses the RUN_TO_POSITION mode to enable the Motor controllers to generate the run profile
// *
// *  In order to calibrate the Gyro correctly, the robot must remain stationary during calibration.
// *  This is performed when the INIT button is pressed on the Driver Station.
// *  This code assumes that the robot is stationary when the INIT button is pressed.
// *  If this is not the case, then the INIT should be performed again.
// *
// *  Note: in this example, all angles are referenced to the initial coordinate frame set during the
// *  the Gyro Calibration process, or whenever the program issues a resetZAxisIntegrator() call on the Gyro.
// *
// *  The angle of movement/rotation is assumed to be a standardized rotation around the robot Z axis,
// *  which means that a Positive rotation is Counter Clock Wise, looking down on the field.
// *  This is consistent with the FTC field coordinate conventions set out in the document:
// *  ftc_app\doc\tutorial\FTC_FieldCoordinateSystemDefinition.pdf
// *
// * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
// * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
// */
//
//@Autonomous(name="Ideally the Better Depot Program", group="Neha")
//// @Disabled
//public class AutoDepot2 extends LinearOpMode {
//
//    /* Declare OpMode members. */
//    HardwareAhen robot = new HardwareAhen();   // Use a Pushbot's hardware
//    private ElapsedTime     runtime = new ElapsedTime();
//
//    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: TETRIX Motor Encoder
//    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
//    static final double WHEEL_DIAMETER_INCHES = 4.0;         // For figuring circumference
//    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
//            (WHEEL_DIAMETER_INCHES * 3.1415);
//
//    // These constants define the desired driving/control characteristics
//    // The can/should be tweaked to suite the specific robot drive train.
//    static final double     DRIVE_SPEED             = 0.4;
//    static final double     TURN_SPEED              = 0.3;
//    static final double     SLOW_STRAFE_SPEED       = 0.3;
//    static final double     STRAFE_SPEED            = 0.5;
//
//
//    static final double HEADING_THRESHOLD = 1;      // As tight as we can make it with an integer gyro
//    static final double P_TURN_COEFF = 0.1;     // Larger is more responsive, but also less stable
//    static final double P_DRIVE_COEFF = 0.05;     // Larger is more responsive, but also less stable
//
//    static final double MARKER_RETRACTED = 0.45;
//    static final double MARKER_EXTENDED = 0.9;
//
//    double color_num = 3; // 3 = blue, 10 = red
//    double current_distance_from_wall = -1;
//    double currentAngle = 0;
//
//    String goldlocation = "UNKNOWN";
//
//    @Override
//    public void runOpMode() {
//
//        /*
//         * Initialize the standard drive system variables.
//         * The init() method of the hardware class does most of the work here
//         */
//        robot.init(hardwareMap);
//        //robot.detector.enable();
//
//        // Ensure the robot is stationary, then reset the encoders and calibrate the gyro.
//        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        robot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        // Send telemetry message to alert driver that we are calibrating;
//        telemetry.addData(">", "Calibrating Gyro");    //
//        telemetry.update();
//
//        robot.modernRoboticsI2cGyro.calibrate();
//        robot.modernRoboticsI2cGyro2.calibrate();
//
//        // make sure the gyro is calibrated before continuing
//        while (!isStopRequested() && robot.modernRoboticsI2cGyro.isCalibrating()) {
//            sleep(50);
//            idle();
//        }
//        while (!isStopRequested() && robot.modernRoboticsI2cGyro2.isCalibrating()) {
//            sleep(50);
//            idle();
//        }
//
//        telemetry.addData(">", "Robot Ready.");
//        telemetry.update();
//
//        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        // Wait for the game to start (Display Gyro value), and reset gyro before we move.
//        while (!isStarted() && !isStopRequested()) {
//            telemetry.addData(">", "Robot Heading = %d", robot.modernRoboticsI2cGyro.getIntegratedZValue());
//            telemetry.update();
//            telemetry.addData(">", "Robot Heading 2 = %d", robot.modernRoboticsI2cGyro2.getIntegratedZValue());
//            telemetry.update();
//            //goldlocation = robot.detector.getCurrentOrder().toString();    // scan minerals
//            goldlocation = robot.detector.getLastOrder().toString();
//            telemetry.addData("Mineral is on the robot's ", goldlocation);
//            telemetry.update();
//
//        }
//
//        robot.modernRoboticsI2cGyro.resetZAxisIntegrator();
//        robot.modernRoboticsI2cGyro2.resetZAxisIntegrator();
//
//
//        // Step through each leg of the path,
//        // Note: Reverse movement is obtained by setting a negative distance (not speed)
//        // Put a hold after each turn
//
//
//        // LEFT MEANS THE ROBOT'S LEFT
//
//        unlatch();
//
//        robot.detector.disable();
//
//        if (goldlocation.equals("LEFT")){
//            telemetry.addData("Going: ", "LEFT");
//            telemetry.update();
//            leftMineral();
//        }
//        else if (goldlocation.equals("CENTER")){
//            telemetry.addData("Going: ", "CENTER");
//            telemetry.update();
//            centerMineral();
//        }
//        else {//(goldlocation.equals("RIGHT")){
//            telemetry.addData("Going: ", "RIGHT or UNKNOWN");
//            telemetry.update();
//            rightMineral();
//        }
//
//
//        telemetry.addData("Path", "Complete");
//        telemetry.update();
//    }
//
//    public void unlatch(){
//
//        double revolutions = 8.5;   // rotations that the lift should move down
//        // timeoutS means that it will def stop in that amount of time, even if the encoders stop working
//        double timeoutS = 3.6;  // amount of seconds that it takes to lower lift
//        int newLiftTarget = robot.lift.getCurrentPosition() + (int)(revolutions * COUNTS_PER_MOTOR_REV);
//
//        robot.lift.setTargetPosition(newLiftTarget);
//        robot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        robot.lift.setPower(1);
//        runtime.reset();
//        while (opModeIsActive() && (runtime.seconds() < timeoutS) && robot.lift.isBusy()) {
//            // Display it for the driver.
//            telemetry.addData("Path1",  "Running to %7d", newLiftTarget);
//            telemetry.addData("Path2",  "Running at %7d", robot.lift.getCurrentPosition());
//            telemetry.update();
//        }
//
//        robot.lift.setPower(0); // stop motor
//        robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        gyroTurn(0.3, 10);
//        gyroHold(0.3,10,0.5);
//        currentAngle = 10;
//
//        robot.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        robot.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        robot.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        robot.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        robot.frontLeft.setPower(STRAFE_SPEED);
//        robot.frontRight.setPower(-STRAFE_SPEED);
//        robot.backLeft.setPower(-STRAFE_SPEED);
//        robot.backRight.setPower(STRAFE_SPEED);
//
//        runtime.reset();
//        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
//            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
//            telemetry.update();
//        }
//        robot.frontLeft.setPower(0);
//        robot.frontRight.setPower(0);
//        robot.backLeft.setPower(0);
//        robot.backRight.setPower(0);
//
//        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        sleep(250);
//
//
//        gyroDrive(DRIVE_SPEED,7, currentAngle);
//    }
//
//    public void leftMineral(){
//
//        // align with mineral
//        encoderStrafeLeft(STRAFE_SPEED, 2.5, 4);
//        sleep(100);
//
//        //knock off mineral
//        gyroDrive(DRIVE_SPEED,35, currentAngle);
//
//        //turn -45 degrees
//        currentAngle = -45;
//        gyroTurn(TURN_SPEED,currentAngle);
//        gyroHold(TURN_SPEED,currentAngle, 0.5);
//
//        // move forward
//        gyroDrive(DRIVE_SPEED, 18, currentAngle);
//
//        // turn 180 degrees
//        currentAngle = 45;
//        gyroTurn(TURN_SPEED, currentAngle);
//        gyroHold(TURN_SPEED, currentAngle, 0.5);
//
//        //remove marker
//        removeMarker();
//
//        // turn, facing crater
//        currentAngle = 135;
//        gyroTurn(TURN_SPEED, currentAngle);
//        gyroHold(TURN_SPEED, currentAngle, 0.5);
//
//        // move to crater
//        gyroDrive(1,96, currentAngle - 5);
//
//    }
//
//    public void centerMineral(){
//
//        // align with mineral (might also need to strafe?) (or, maybe make the angle towards the left (more positive))
//        encoderStrafeLeft(STRAFE_SPEED,1.2, 2);
//        sleep(100);
//
//        // knock off mineral
//        gyroDrive(DRIVE_SPEED, 35, currentAngle);
//
//        // turn 45 degrees
//        currentAngle = 67;
//        gyroTurn(TURN_SPEED, currentAngle);
//        gyroHold(TURN_SPEED, currentAngle, 0.5);
//
//        sleep(250);
//
//        // remove marker
//        removeMarker();
//
//        // turn 45 degrees, facing crater
//        currentAngle = 135;
//        gyroTurn(TURN_SPEED, currentAngle);
//        gyroHold(TURN_SPEED, currentAngle, 0.5);
//        sleep(250);
//
//        // move to crater
//        gyroDrive(1,96, currentAngle - 5);
//
//    }
//
//    public void rightMineral(){
//
//        // align with mineral
//        currentAngle = -20;
//        gyroTurn(TURN_SPEED, currentAngle);
//        gyroHold(TURN_SPEED, currentAngle,0.5);
//        sleep(100);
//
//        // knock off mineral
//        gyroDrive(DRIVE_SPEED, 27, currentAngle);
//
//        // turn left
//        currentAngle = 45;
//        gyroTurn(TURN_SPEED,currentAngle);
//        gyroHold(TURN_SPEED,currentAngle,0.5);
//
//        // move to depot
//        gyroDrive(DRIVE_SPEED, 30, currentAngle);
//
//        // turn left 45 degrees
//        currentAngle = 90;
//        gyroTurn(TURN_SPEED, currentAngle);
//        gyroHold(TURN_SPEED,currentAngle,0.5);
//
//        // team marker
//        removeMarker();
//
//        // turn 45 degrees, now facing crater
//        currentAngle = 135;
//        gyroTurn(TURN_SPEED, currentAngle);
//        gyroHold(TURN_SPEED,currentAngle,0.5);
//        sleep(250);
//
//
//        // is this necessary??
//        robot.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        robot.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        robot.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        robot.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        // move to crater
//        gyroDrive(1,96, currentAngle - 5);
//
//    }
//
//
//
//    public void encoderStrafeLeft(double speed, double revolutions, double timeoutS){
//
//        int newFrontLeftTarget = robot.frontLeft.getCurrentPosition() - (int)(revolutions * COUNTS_PER_MOTOR_REV);
//        int newBackLeftTarget = robot.backLeft.getCurrentPosition() + (int)(revolutions * COUNTS_PER_MOTOR_REV);
//
//        robot.frontLeft.setTargetPosition(newFrontLeftTarget);
//        robot.frontRight.setTargetPosition(newBackLeftTarget);
//        robot.backLeft.setTargetPosition(newBackLeftTarget);
//        robot.backRight.setTargetPosition(newFrontLeftTarget);
//
//        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        robot.frontLeft.setPower(-speed);
//        robot.frontRight.setPower(speed);
//        robot.backLeft.setPower(speed);
//        robot.backRight.setPower(-speed);
//
//        runtime.reset();
//        while (opModeIsActive() && (runtime.seconds() < timeoutS) && robot.frontLeft.isBusy() && robot.frontRight.isBusy()
//                && robot.backLeft.isBusy() && robot.backRight.isBusy()) {
//            // Display it for the driver.
//            telemetry.addData("Path1",  "Running to %7d", newFrontLeftTarget);
//            telemetry.addData("Path2",  "Running at %7d", robot.frontLeft.getCurrentPosition());
//            telemetry.update();
//        }
//
//        robot.frontLeft.setPower(0);
//        robot.frontRight.setPower(0);
//        robot.backLeft.setPower(0);
//        robot.backRight.setPower(0);
//
//        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//    }
//
//    public void removeMarker(){
//        for(int i = 0; i < 3; i++) {
//            robot.teamMarker.setPosition(MARKER_EXTENDED);
//            sleep(250);
//            robot.teamMarker.setPosition(MARKER_RETRACTED);
//            sleep(250);
//        }
//    }
//
//    /**
//     * Method to drive on a fixed compass bearing (angle), based on encoder counts.
//     * Move will stop if either of these conditions occur:
//     * 1) Move gets to the desired position
//     * 2) Driver stops the opmode running.
//     *
//     * @param speed    Target speed for forward motion.  Should allow for _/- variance for adjusting heading
//     * @param distance Distance (in inches) to move from current position.  Negative distance means move backwards.
//     * @param angle    Absolute Angle (in Degrees) relative to last gyro reset.
//     *                 0 = fwd. +ve is CCW from fwd. -ve is CW from forward.
//     *                 If a relative angle is required, add/subtract from current heading.
//     */
//    public void gyroDrive(double speed,
//                          double distance,
//                          double angle) {
//
//        int newLeftTarget;
//        int newRightTarget;
//        int moveCounts;
//        double max;
//        double error;
//        double steer;
//        double leftSpeed;
//        double rightSpeed;
//
//        // Ensure that the opmode is still active
//        if (opModeIsActive()) {
//
//            // Determine new target position, and pass to motor controller
//            moveCounts = (int) (distance * COUNTS_PER_INCH);
//            newLeftTarget = robot.frontLeft.getCurrentPosition() + moveCounts;
//            newRightTarget = robot.frontRight.getCurrentPosition() + moveCounts;
//
//            // Set Target and Turn On RUN_TO_POSITION
//            robot.frontLeft.setTargetPosition(newLeftTarget);
//            robot.frontRight.setTargetPosition(newRightTarget);
//            robot.backLeft.setTargetPosition(newLeftTarget);
//            robot.backRight.setTargetPosition(newRightTarget);
//
//
//            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            // start motion.
//            speed = Range.clip(Math.abs(speed), 0.0, 1.0);
//            robot.frontLeft.setPower(speed);
//            robot.frontRight.setPower(speed);
//            robot.backLeft.setPower(speed);
//            robot.backRight.setPower(speed);
//
//            // keep looping while we are still active, and BOTH motors are running.
//            while (opModeIsActive() &&
//                    (robot.frontLeft.isBusy() && robot.frontRight.isBusy() && robot.backLeft.isBusy() && robot.backRight.isBusy())) {
//
//                // adjust relative speed based on heading error.
//                error = getError(angle);
//                steer = getSteer(error, P_DRIVE_COEFF);
//
//                // if driving in reverse, the motor correction also needs to be reversed
//                if (distance < 0)
//                    steer *= -1.0;
//
//                leftSpeed = speed - steer;
//                rightSpeed = speed + steer;
//
//                // Normalize speeds if either one exceeds +/- 1.0;
//                max = Math.max(Math.abs(leftSpeed), Math.abs(rightSpeed));
//                if (max > 1.0) {
//                    leftSpeed /= max;
//                    rightSpeed /= max;
//                }
//
//                robot.frontLeft.setPower(leftSpeed);
//                robot.frontRight.setPower(rightSpeed);
//                robot.backLeft.setPower(leftSpeed);
//                robot.backRight.setPower(rightSpeed);
//
//                // Display drive status for the driver.
//                telemetry.addData("Err/St", "%5.1f/%5.1f", error, steer);
//                telemetry.addData("Target", "%7d:%7d", newLeftTarget, newRightTarget);
//                telemetry.addData("Actual", "%7d:%7d", robot.frontLeft.getCurrentPosition(),
//                        robot.frontRight.getCurrentPosition());
//                telemetry.addData("Speed", "%5.2f:%5.2f", leftSpeed, rightSpeed);
//                telemetry.update();
//            }
//
//            // Stop all motion;
//            robot.frontLeft.setPower(0);
//            robot.frontRight.setPower(0);
//            robot.backLeft.setPower(0);
//            robot.backRight.setPower(0);
//
//            // Turn off RUN_TO_POSITION
//            robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//
//        }
//    }
//
//    /**
//     * Method to spin on central axis to point in a new direction.
//     * Move will stop if either of these conditions occur:
//     * 1) Move gets to the heading (angle)
//     * 2) Driver stops the opmode running.
//     *
//     * @param speed Desired speed of turn.
//     * @param angle Absolute Angle (in Degrees) relative to last gyro reset.
//     *              0 = fwd. +ve is CCW from fwd. -ve is CW from forward.
//     *              If a relative angle is required, add/subtract from current heading.
//     */
//    public void gyroTurn(double speed, double angle) {
//
//        // keep looping while we are still active, and not on heading.
//        while (opModeIsActive() && !onHeading(speed, angle, P_TURN_COEFF)) {
//            // Update telemetry & Allow time for other processes to run.
//            telemetry.update();
//        }
//    }
//
//    /**
//     * Method to obtain & hold a heading for a finite amount of time
//     * Move will stop once the requested time has elapsed
//     *
//     * @param speed    Desired speed of turn.
//     * @param angle    Absolute Angle (in Degrees) relative to last gyro reset.
//     *                 0 = fwd. +ve is CCW from fwd. -ve is CW from forward.
//     *                 If a relative angle is required, add/subtract from current heading.
//     * @param holdTime Length of time (in seconds) to hold the specified heading.
//     */
//    public void gyroHold(double speed, double angle, double holdTime) {
//
//        ElapsedTime holdTimer = new ElapsedTime();
//
//        // keep looping while we have time remaining.
//        holdTimer.reset();
//        while (opModeIsActive() && (holdTimer.time() < holdTime)) {
//            // Update telemetry & Allow time for other processes to run.
//            onHeading(speed, angle, P_TURN_COEFF);
//            telemetry.update();
//        }
//
//        // Stop all motion;
//        robot.frontLeft.setPower(0);
//        robot.frontRight.setPower(0);
//        robot.backLeft.setPower(0);
//        robot.backRight.setPower(0);
//    }
//
//    /**
//     * Perform one cycle of closed loop heading control.
//     *
//     * @param speed  Desired speed of turn.
//     * @param angle  Absolute Angle (in Degrees) relative to last gyro reset.
//     *               0 = fwd. +ve is CCW from fwd. -ve is CW from forward.
//     *               If a relative angle is required, add/subtract from current heading.
//     * @param PCoeff Proportional Gain coefficient
//     * @return
//     */
//    boolean onHeading(double speed, double angle, double PCoeff) {
//        double error;
//        double steer;
//        boolean onTarget = false;
//        double leftSpeed;
//        double rightSpeed;
//
//        // determine turn power based on +/- error
//        error = getError(angle);
//
//        if (Math.abs(error) <= HEADING_THRESHOLD) {
//            steer = 0.0;
//            leftSpeed = 0.0;
//            rightSpeed = 0.0;
//            onTarget = true;
//        } else {
//            steer = getSteer(error, PCoeff);
//            rightSpeed = speed * steer;
//            leftSpeed = -rightSpeed;
//        }
//
//        // Send desired speeds to motors.
//        robot.frontLeft.setPower(leftSpeed);
//        robot.frontRight.setPower(rightSpeed);
//        robot.backLeft.setPower(leftSpeed);
//        robot.backRight.setPower(rightSpeed);
//
//        // Display it for the driver.
//        telemetry.addData("Target", "%5.2f", angle);
//        telemetry.addData("Err/St", "%5.2f/%5.2f", error, steer);
//        telemetry.addData("Speed.", "%5.2f:%5.2f", leftSpeed, rightSpeed);
//
//        return onTarget;
//    }
//
//    /**
//     * getError determines the error between the target angle and the robot's current heading
//     *
//     * @param targetAngle Desired angle (relative to global reference established at last Gyro Reset).
//     * @return error angle: Degrees in the range +/- 180. Centered on the robot's frame of reference
//     * +ve error means the robot should turn LEFT (CCW) to reduce error.
//     */
//    public double getError(double targetAngle) {
//
//        double robotError;
//
//        AngularVelocity rates = robot.gyro.getAngularVelocity(AngleUnit.DEGREES);
//
//        if (!(formatRate(rates.xRotationRate).equals("0.000") && formatRate(rates.yRotationRate).equals("0.000") && formatRate(rates.zRotationRate).equals("0.000"))){
//            robotError = targetAngle - robot.modernRoboticsI2cGyro.getIntegratedZValue();
//            telemetry.addData(">","first gyro");
//            telemetry.update();
//        }
//        else{
//            robotError = targetAngle - robot.modernRoboticsI2cGyro2.getIntegratedZValue();
//            telemetry.addData(">","backup");
//            telemetry.update();
//        }
//        while (robotError > 180) robotError -= 360;
//        while (robotError <= -180) robotError += 360;
//        return robotError;
//    }
//
//    /**
//     * returns desired steering force.  +/- 1 range.  +ve = steer left
//     *
//     * @param error  Error angle in robot relative degrees
//     * @param PCoeff Proportional Gain Coefficient
//     * @return
//     */
//    public double getSteer(double error, double PCoeff) {
//        return Range.clip(error * PCoeff, -1, 1);
//    }
//
//    String formatRate(float rate) {
//        return String.format("%.3f", rate);
//    }
//}
