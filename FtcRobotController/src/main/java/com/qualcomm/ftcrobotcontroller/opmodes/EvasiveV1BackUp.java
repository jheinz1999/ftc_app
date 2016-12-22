package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by allinson on 9/1/16.
 */
public class EvasiveV1BackUp extends OpMode {
    int cycleCount; // cycle counter
    double distFront; // value for distance sensor
    double distBack;  // value for distance sensor
    int pressedbutton = 0;
    //    int whiteSensor; // value of lightSensor between 0 and 100
    /*
     * The left motor controller instance.
     */
    protected DcMotor leftDriveMotor;

    /*
     * The right motor controller instance.
     * GIT TEST 123
     */
    protected DcMotor rightDriveMotor;

    protected UltrasonicSensor distSensorFront, distSensorBack;
    // distSensorFront - port 4; distSensorBack - Port 5
    protected ColorSensor whiteLineSensor;

    protected TouchSensor touchSensor;

    /**
     * Perform any actions that are necessary when the OpMode is enabled.
     *
     * The system calls this member once when the OpMode is enabled.
     */
    @Override public void init () {
        // Get the left drive motor.

        try {
            this.leftDriveMotor = this.hardwareMap.dcMotor.get("leftDriveMotor"); //drive 1
        } catch (Exception e) {
            DbgLog.msg(e.getLocalizedMessage());
        }
        // Get the right drive motor.
        try {
            this.rightDriveMotor = this.hardwareMap.dcMotor.get("rightDriveMotor"); //drive 2
            this.rightDriveMotor.setDirection (DcMotor.Direction.REVERSE);
        } catch (Exception e) {
            DbgLog.msg(e.getLocalizedMessage());
        }
        // Get the back distance sensor
        try {
            this.distSensorBack = this.hardwareMap.ultrasonicSensor.get("distSensorBack"); //drive 4
        } catch (Exception e) {
            DbgLog.msg(e.getLocalizedMessage());
        }
        // Get the front distance sensor
        try {
            this.distSensorFront = this.hardwareMap.ultrasonicSensor.get("distSensorFront"); //drive 5
        } catch (Exception e) {
            DbgLog.msg(e.getLocalizedMessage());
        }
        // get lightSenor
        try {
            this.whiteLineSensor = this.hardwareMap.colorSensor.get("colorSensor"); //drive 5
        } catch (Exception e) {
            DbgLog.msg(e.getLocalizedMessage());
        }

        try {
            this.whiteLineSensor = this.hardwareMap.colorSensor.get("touchSensor"); //drive 5
        } catch (Exception e) {
            DbgLog.msg(e.getLocalizedMessage());
        }
    }

    /**
     * Perform any actions that are necessary when the OpMode is enabled.
     *
     * The system calls this member once when the OpMode is enabled.
     */
    @Override public void start () {
        // Code here...
    }

    /**
     * Perform any actions that are necessary while the OpMode is running.
     *
     * The system calls this member repeatedly while the OpMode is running.
     */
    @Override public void loop () {

        // Values come from distance sensors
        this.telemetry.addData("cyclecount:", cycleCount);
        if (cycleCount % 100 ==0)
        {
            distFront = this.distSensorFront.getUltrasonicLevel();
            distBack = this.distSensorBack.getUltrasonicLevel();
            this.whiteLineSensor.enableLed(true);
            this.telemetry.addData("r", whiteLineSensor.red());
            this.telemetry.addData("g", whiteLineSensor.green());
            this.telemetry.addData("b", whiteLineSensor.blue());
            //this.whiteLineSensor.enableLed(false);
        }
        cycleCount++;

        double motorLeft = 1;
        double motorRight = 1;
        this.telemetry.addData("distFront:", distFront) ;
        this.telemetry.addData("distBack:", distBack);


        //if ((distFront > 25 || distFront < 20) && (distBack > 25 || distBack < 20)) {

        if (!this.touchSensor.isPressed()) {
            if (distFront > distBack) {

                motorLeft = 0.1;

            } else if (distBack > distFront) {

                motorRight = 0.1;

            }
        }
        else if (this.touchSensor.isPressed())
        {
            pressedbutton = 1;
        }

        if (pressedbutton == 1)
        {
            motorLeft = 0;
            motorRight = 0;
        }


        //}

        this.telemetry.addData("rightDriveMotor", motorRight);
        this.telemetry.addData("leftDriveMotor", motorLeft);

//        if (whiteSensor > 150)
//        {
//            motorLeft = 0;
//            motorRight = 0;
//        }


        this.leftDriveMotor.setPower(motorLeft);
        this.rightDriveMotor.setPower(motorRight);


    }

    /**
     * Perform any actions that are necessary when the OpMode is disabled.
     *
     * The system calls this member once when the OpMode is disabled.
     */
    @Override public void stop () {
        // Code here...
    }
}
