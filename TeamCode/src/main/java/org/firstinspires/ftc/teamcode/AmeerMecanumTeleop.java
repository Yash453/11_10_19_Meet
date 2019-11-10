//
//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.util.Range;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//
///**
// * Created by Ansheth18 on 3/7/2018.
// */
//
//@TeleOp(name="Old Mecanum TeleOp", group="Neha")
//@Disabled
//public class AmeerMecanumTeleop extends LinearOpMode
//{
//    HardwareRedbot ameer = new HardwareRedbot();
//    private ElapsedTime runtime = new ElapsedTime();
//
//    @Override
//    public void runOpMode() throws InterruptedException
//    {
//        ameer.init(hardwareMap);
//
//        waitForStart();
//        while (opModeIsActive())
//        {
//            // Setup a variable for each drive wheel to save power level for telemetry
//            double frontLeftPower;
//            double frontRightPower;
//            double backLeftPower;
//            double backRightPower;
//            double yDrive = gamepad1.left_stick_y;
//            double turn  =  gamepad1.right_stick_x;
//            double xDrive = gamepad1.left_stick_x;
//
//            if (Math.abs(xDrive) < Math.abs((2/5)*yDrive))
//                xDrive = 0;
//            else if (Math.abs(yDrive) < Math.abs((2/5)*xDrive))
//                yDrive = 0;
//
//
//            frontLeftPower  = Range.clip(yDrive + xDrive + turn, -0.7, 0.7);
//            backLeftPower   = Range.clip(yDrive - xDrive + turn, -0.7, 0.7);
//            frontRightPower = Range.clip(yDrive - xDrive - turn, -0.7, 0.7);
//            backRightPower  = Range.clip(yDrive + xDrive - turn, -0.7, 0.7);
//
//            // Send calculated power to wheels
//            ameer.frontLeft.setPower(frontLeftPower);
//            ameer.frontRight.setPower(frontRightPower);
//            ameer.backLeft.setPower(backLeftPower);
//            ameer.backRight.setPower(backRightPower);
//
//            telemetry.addData("xDrive: ", +xDrive);
//            telemetry.addData("yDrive: ", +yDrive);
//            telemetry.addData("turn: ", +turn);
//        }
//    }
//    public void stopDriving ()
//    {
//        ameer.frontLeft.setPower(0);
//        ameer.frontRight.setPower(0);
//        ameer.backLeft.setPower(0);
//        ameer.backRight.setPower(0);
//    }
//}
