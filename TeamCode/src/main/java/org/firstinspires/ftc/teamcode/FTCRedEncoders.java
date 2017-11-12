package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

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
        //Encoder Values
        check = 0;
        uEncoderMax = 2000.00;
        uEncoderMin = -200.0;
        lEncoderMax = -1000;
        lEncoderMin = 20;
        robot.init(hardwareMap);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Encoders reset, BRAAAAAAH");
        robot.lowerLiftMotor.resetDeviceConfigurationForOpMode();
        robot.upperLiftMotor.resetDeviceConfigurationForOpMode();
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


        //Chassis Drivetraine
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
        robot.frontLeftMotor.setPower((ch3 + ch1 - ch4));
        robot.frontRightMotor.setPower((ch3 - ch1 - ch4));
        robot.rearLeftMotor.setPower(ch3 + ch1 + ch4);
        robot.rearRightMotor.setPower(ch3 - ch1 + ch4);
        /* <<<<LIFT MOTORS>>>> */
        if ((ly <= 0 && lEncoderVal >= lEncoderMin) || (ly >= 0 && lEncoderVal < lEncoderMax)){
            robot.lowerLiftMotor.setPower(ly);
        } else if ((ry <= 0 && lEncoderVal >= lEncoderMin) || (ry >= 0 && lEncoderVal < lEncoderMax)) {
            robot.upperLiftMotor.setPower(ry);
        } else {
            robot.lowerLiftMotor.setPower(0);
            robot.upperLiftMotor.setPower(0);
        }

//        if(lEncoderVal>lEncoderMin || lEncoderVal<lEncoderMax || uEncoderVal>uEncoderMin || uEncoderVal<uEncoderMax){
//
//            if(ly<=0 && lEncoderVal>lEncoderMin) {
//                robot.lowerLiftMotor.setPower(ly);
//            } else if(ly>=0 && lEncoderVal<lEncoderMax) {
//                robot.lowerLiftMotor.setPower(ly);
//            } else if(ry<=0 && lEncoderVal>lEncoderMin) {
//                robot.upperLiftMotor.setPower(ry);
//            } else if(ry>=0 && lEncoderVal<lEncoderMax) {
//                robot.upperLiftMotor.setPower(ry);
//            } else {
//
//            }
//        } else{
//            robot.lowerLiftMotor.setPower(ly);
//            robot.lowerLiftMotor.setPower(ry);
//        }
        /* <<<<SERVOS>>>> */
        if (gamepad2.right_bumper) {
            robot.rightGlyph.setPosition(.6);
            robot.leftGlyph.setPosition(0);

        } else if (gamepad2.left_bumper) {
            robot.rightGlyph.setPosition(0);
            robot.leftGlyph.setPosition(.6);
        }
        // if (!"ly".equals(0))
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


    /*Private instance fields*/
    private double ch1;
    private double ch2;
    private double ch3;
    private double ch4;
    private double lEncoderMax;
    private double lEncoderMin;
    private double uEncoderMin;
    private double lEncoderVal;
    private double uEncoderMax;
    private double uEncoderVal;
    private double ly;
    private double ry;
    private double lift;
    private double zero;
    private double check;
}