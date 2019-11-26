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
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

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

@TeleOp(name="Ahen Main2", group="Neha")
// @Disabled
public class AhenTeleop2 extends LinearOpMode {

    final double    CLAW_SPEED      = 0.02 ;                   // sets rate to move servo

    /* Declare OpMode members. */
    HardwareAhen2 robot = new HardwareAhen2();   // Use a Redbot's hardware

    double target = 0;
    double SERVO_SPEED = .1;
    double yashIsaPanda = 0.008;
    private ElapsedTime     runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
//        robot.detector.disable();
        // Send telemetry, message to signify robot waiting;
        telemetry.addData("Remember", "\"May the force of Reema, Miki, Neha, Ivan, Aarav, Marc, Albert and Abhi be with you.\" ");
        telemetry.update();

        robot.clawLeft.setPosition(0.5);
        robot.clawRight.setPosition(0.1);
        // Which idiot wrote the documentation for this code? I have no idea what I'm doing - Ihba

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {


            // Run wheels in POV mode (note: The joystick goes negative when pushed forwards, so negate it)

            // Convert joysticks to desired motion
            Mecanum.Motion motion = Mecanum.joystickToMotion(
                    gamepad1.left_stick_x, -gamepad1.left_stick_y,
                    gamepad1.right_stick_x, -gamepad1.right_stick_y);

            // Convert desired motion to wheel powers, with power clamping
            Mecanum.Wheels wheels = Mecanum.motionToWheels(motion);
            robot.frontLeft.setPower(wheels.frontLeft);
            robot.frontRight.setPower(wheels.frontRight);
            robot.backLeft.setPower(wheels.backLeft);
            robot.backRight.setPower(wheels.backRight);

            telemetry.addData("Motor speeds", "robot.frontLeft.getPower(), robot.frontRight.getPower(), robot.backLeft.getPower(), robot.backRight.getPower()", robot.backRight.getPower(),robot.backLeft.getPower(), robot.frontRight.getPower(),robot.frontLeft.getPower());
            telemetry.update();




            if (!gamepad2.dpad_up && !gamepad2.dpad_down)
                robot.lift.setPower(0);
            else if (gamepad2.dpad_down)
                robot.lift.setPower(-1);
            else if (gamepad2.dpad_up)
                robot.lift.setPower(1);

            if (gamepad2.right_bumper) {
                robot.clawRight.setPosition(0.25);
                robot.clawLeft.setPosition(0.5);
            }

            else if (gamepad2.left_bumper) {
                robot.clawLeft.setPosition(0.8);
                robot.clawRight.setPosition(0.6);
            }

            if (gamepad2.a)
                robot.autoServo.setPosition(1);
            else if (gamepad2.b)
                robot.autoServo.setPosition(0);
            else if (gamepad2.x)
                robot.autoServo.setPosition(0.25);
            else if (gamepad2.y)
                robot.autoServo.setPosition(0.75);


//            if (gamepad1.x){
//                yashIsaPanda = yashIsaPanda+0.001;
//                telemetry.addData("count","Servo Speed" + yashIsaPanda);
//                telemetry.update();
//
//            }
//            if (gamepad1.y){
//                yashIsaPanda = yashIsaPanda-0.001;
//                telemetry.addData("count", yashIsaPanda);
//                telemetry.update();
//            }
//            if (gamepad1.a){
//                double halfTurn = 1120/2;
//                robot.tray.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//                robot.tray.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                int newTarget = robot.tray.getCurrentPosition() - (int)halfTurn;
//                robot.tray.setTargetPosition(newTarget);
//                robot.tray.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                runtime.reset();
//                robot.tray.setPower(1);
//
//                while (robot.tray.isBusy()){
//                    telemetry.addData("Status", "Running half turn");
//                    telemetry.update();
//                }
//                robot.tray.setPower(0);
//                robot.tray.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//            }
//            else if (gamepad1.b){
//                robot.tray.setPower(-0.2);
//            }
            if (!gamepad1.left_bumper && !gamepad1.right_bumper)
                robot.tray.setPower(0);
            else if (gamepad1.left_bumper)
                robot.tray.setPower(0.1);
            else if (gamepad1.right_bumper)
                robot.tray.setPower(0.1);

        }
    }
}

