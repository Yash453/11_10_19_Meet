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
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Gamepad;

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



        // THIS IS THE PROGRAM USED FOR LETTING OTHERS CONTROL THE ROBOT FOR OUTREACH.
        // Controller 1 is to drive, Controller 2 is for mechanisms.

        // If it's a small child, Controller 1 ONLY with finger on the DS's stop button.
        // If there are 2 people, let them work together with 2 controllers.



@TeleOp(name="Outreach Teleop", group="Neha")
@Disabled
public class RedbotOutreachTeleop extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareRedbot robot = new HardwareRedbot();   // Use a Redbot's hardware
    // could also use HardwarePushbotMatrix class.
    double SERVO_SPEED = 0.1;
    private int chosen = 0;




    @Override
    public void runOpMode() {

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */

        robot.init(hardwareMap);
        Gamepad[] controllers = new Gamepad[2]; // this is cool, it should go in the engineering notebook -- efficiency of using arrays as opposed to two if/else statements per gamepad
        controllers[0] = gamepad1;
        controllers[1] = gamepad2;
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello 2018-19 Redbots!\nHow many robots does it take to screw " +
                "in a lightbulb?\nThree - one to hold the lightbulb and two to turn the ladder!");
        telemetry.update();

        telemetry.addData("Say", "A FOR CONTROLLER 1, B FOR CONTROLLER 2");
        telemetry.update();

        while (!isStopRequested() && !isStarted())  {
            if (gamepad1.a || gamepad2.a) {
                telemetry.addData("Say", "you chose 1");
                telemetry.update();
                chosen = 0;
            }
            else if (gamepad1.b || gamepad2.b) {
                telemetry.addData("Say", "you chose 2");
                telemetry.update();
                chosen = 1;
            }
        }


        // Wait for the game to start (driver presses PLAY)
        //waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive() && !gamepad1.x && !gamepad2.x) {

            Mecanum.Motion motion = Mecanum.joystickToMotion(
                    gamepad1.left_stick_x, gamepad1.left_stick_y,
                    gamepad1.right_stick_x, gamepad1.right_stick_y);
            // Convert desired motion to wheel powers, with power clamping
            Mecanum.Wheels wheels = Mecanum.motionToWheels(motion);
            robot.frontLeft.setPower(wheels.frontLeft);
            robot.frontRight.setPower(wheels.frontRight);
            robot.backLeft.setPower(wheels.backLeft);
            robot.backRight.setPower(wheels.backRight);

            telemetry.addData("Say", "chosen =  " + chosen);
            telemetry.update();

                if (!controllers[chosen].left_bumper && !controllers[chosen].right_bumper) {
                    robot.rightIntake.setPower(0);
                    robot.leftIntake.setPower(0);
                }
                if (controllers[chosen].left_bumper) {
                    robot.rightIntake.setPower(1);
                    robot.leftIntake.setPower(-1);
                }
                if (controllers[chosen].right_bumper) {
                    robot.rightIntake.setPower(-1);
                    robot.leftIntake.setPower(1);
                }


                if (controllers[chosen].a) {
                    robot.rightUp.setPosition(0);
                    robot.leftUp.setPosition(1);
                    robot.helper.setPosition(0);
                }
                if (controllers[chosen].b) {
                    robot.rightUp.setPosition(0.5);
                    robot.leftUp.setPosition(0.5);
                    robot.helper.setPosition(0.5);
                }
                if (controllers[chosen].y) {
                    robot.rightUp.setPosition(1);
                    robot.leftUp.setPosition(0);
                    robot.helper.setPosition(1);
                }
        }
    }
}