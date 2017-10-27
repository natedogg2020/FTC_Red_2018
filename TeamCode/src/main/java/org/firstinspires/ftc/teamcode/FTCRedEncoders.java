package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by nmckelvey on 10/23/17.
 */
@TeleOp(name="FTC Red Encoder?", group="FTCRed")
public class FTCRedEncoders extends OpMode {

    /* Declare OpMode members. */
    FTCRedHardware2 robot = new FTCRedHardware2(); // use the class created to define a robot's hardware
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello FTC Red Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*

     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double ch1;
        double ch2;
        double ch3;
        double ch4;
        double lEncoderMax;
        double lEncoderMin;
        double uEncoderMin;
        double lEncoderVal;
        double uEncoderMax;
        double uEncoderVal;
        double ly;
        double ry;
        double lift;
        //Encoder Values
        uEncoderMax = -4000.00;
        uEncoderMin = 5000.0;
        lEncoderMax = -4000.00;
        lEncoderMin = 5000.0;

        //Chassis Drivetrain
        ch1 = gamepad1.right_stick_x;
        ch2 = gamepad1.right_stick_y;
        ch3 = -gamepad1.left_stick_y;
        ch4 = -gamepad1.left_stick_x;
        //Linear Lift Motors
        ly = gamepad2.left_stick_y;
        ry = gamepad2.right_stick_y;
        //Lift Motor Encoders
        lEncoderVal = robot.lowerLiftMotor.getCurrentPosition();
        telemetry.addData("Lower Lift Encoder", lEncoderVal);
        uEncoderVal = robot.upperLiftMotor.getCurrentPosition();
        telemetry.addData("Upper Lift Encoder", uEncoderVal);
        /* <<<<DRIVETRAIN>>>> */
        robot.frontLeftMotor.setPower((ch3 + ch1 - ch4)/2);
        robot.frontRightMotor.setPower((ch3 - ch1 - ch4)/2);
        robot.rearLeftMotor.setPower(ch3 + ch1 + ch4);
        robot.rearRightMotor.setPower(ch3 - ch1 + ch4);
        /* <<<<LIFT MOTORS>>>> */
        if (ly>0 && ly>= lEncoderMax ){
            robot.lowerLiftMotor.setPower(ly);
        } else{
        robot.upperLiftMotor.setPower(0);
        }
        if (ly<0 && ly<= lEncoderMin){
            robot.lowerLiftMotor.setPower(ly);
        } else{
            robot.lowerLiftMotor.setPower(0);
        } if (ry>0 && ry>= uEncoderMax ){
            robot.upperLiftMotor.setPower(ry);
        } else{
        robot.upperLiftMotor.setPower(0);
        }
        if (ry<0 && ry<= uEncoderMin){
            robot.upperLiftMotor.setPower(ry);
        } else{
            robot.upperLiftMotor.setPower(0);
        }
        /* <<<<SERVOS>>>> */
        if (gamepad2.right_bumper) {
            robot.rightGlyph.setPosition(.6);
            robot.leftGlyph.setPosition(0);

        } else if (gamepad2.left_bumper) {
            robot.rightGlyph.setPosition(0);
            robot.leftGlyph.setPosition(.6);
        }

        /**
         *linear lift code
         if(encoderVal >= encoderLim && gamepad2.right_trigger > 0){
         robot.liftMotor.setPower(-gamepad2.right_trigger);
         } else if (gamepad2.left_trigger > 0) {
         robot.liftMotor.setPower(gamepad2.left_trigger);
         } else {
         robot.liftMotor.setPower(0);
         }
     /*
     * Code to run ONCE after the driver hits STOP
     */
    }
}