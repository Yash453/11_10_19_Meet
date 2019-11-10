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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a POV Game style Teleop for a PushBot
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Single Controller", group="Neha")
@Disabled
public class RedbotTeleopOne extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareRedbot robot = new HardwareRedbot();   // Use a Redbot's hardware
    // could also use HardwarePushbotMatrix class.
    double target = 0;
    double SERVO_SPEED = .1;

    @Override
    public void runOpMode() {

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello 2018-19 Redbots!\nHow many robots does it take to screw " +
                "in a lightbulb?\nThree - one to hold the lightbulb and two to turn the ladder!");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in POV mode (note: The joystick goes negative when pushed forwards, so negate it)

            // Convert joysticks to desired motion
            Mecanum.Motion motion = Mecanum.joystickToMotion(
                    gamepad1.left_stick_x, gamepad1.left_stick_y,
                    gamepad1.right_stick_x, gamepad1.right_stick_y);
            // Convert desired motion to wheel powers, with power clamping
            Mecanum.Wheels wheels = Mecanum.motionToWheels(motion);
            robot.frontLeft.setPower(wheels.frontLeft);
            robot.frontRight.setPower(wheels.frontRight);
            robot.backLeft.setPower(wheels.backLeft);
            robot.backRight.setPower(wheels.backRight);

            if (!gamepad1.b){
                robot.dropper.setPower(0);
            }
            if (gamepad1.b) {
                robot.dropper.setPower(-1);
            }


            if (!gamepad1.left_bumper && !gamepad1.right_bumper) {
                robot.rightIntake.setPower(0);
                robot.leftIntake.setPower(0);
            }
            if (gamepad1.left_bumper) {
                robot.rightIntake.setPower(1);
                robot.leftIntake.setPower(-1);
            }
            if (gamepad1.right_bumper) {
                robot.rightIntake.setPower(-1);
                robot.leftIntake.setPower(1);
            }


            if (gamepad1.dpad_up && !gamepad1.dpad_down)
            {
                while(opModeIsActive() && gamepad1.dpad_up){
                    robot.outLeft.setPosition(1);
                }
            }



            while (opModeIsActive() && gamepad1.dpad_up && gamepad1.y){
                robot.outLeft.setPosition(1);
                robot.outRight.setPosition(1);
            }
            while (opModeIsActive() && gamepad1.dpad_down && gamepad1.a){
                robot.outRight.setPosition(0);
                robot.outLeft.setPosition(0);
            }


            while (opModeIsActive() && gamepad1.dpad_up && !gamepad1.y){
                robot.outLeft.setPosition(1);
            }
            while(opModeIsActive() && gamepad1.dpad_down && !gamepad1.a){
                robot.outLeft.setPosition(0);
            }
            if (!gamepad1.dpad_down && !gamepad1.dpad_up){
                robot.outLeft.setPosition(.5);
            }


            while (opModeIsActive() && gamepad1.y && !gamepad1.dpad_up){
                robot.outRight.setPosition(1);
            }
            while(opModeIsActive() && gamepad1.a && !gamepad1.dpad_down){
                robot.outRight.setPosition(0);
            }
            if (!gamepad1.y && !gamepad1.a){
                robot.outRight.setPosition(.5);
            }


            if (gamepad1.dpad_left) { //down
                robot.rightUp.setPosition(0);
                robot.leftUp.setPosition(1);
                robot.helper.setPosition(1);
            }
            if (gamepad1.dpad_right) {
                robot.rightUp.setPosition(0.5);
                robot.leftUp.setPosition(0.5);
                robot.helper.setPosition(0.5);
            }
            if (gamepad1.x) { //up
                robot.rightUp.setPosition(1);
                robot.leftUp.setPosition(0);
                robot.helper.setPosition(0);
            }
        }
    }
}