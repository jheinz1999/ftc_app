package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by allinson on 9/1/16.
 */
public class EvasiveV1Auto extends OpMode {
    int cycleCount; // cycle counter
    double distFront; // value for distance sensor
    double distBack;  // value for distance sensor
    /*
     * The left motor controller instance.
     */
    protected DcMotor leftDriveMotor;

    /*
     * The right motor controller instance.
     */
    protected DcMotor rightDriveMotor;

    protected UltrasonicSensor distSensorFront, distSensorBack;
    // distSensorFront - port 4; distSensorBack - Port 5

    /**
     * Perform any actions that are necessary when the OpMode is enabled.
     *
     * The system calls this member once when the OpMode is enabled.
     */
    @Override public void init () {
        // Get the left drive motor.
        try {
            this.leftDriveMotor = this.hardwareMap.dcMotor.get("leftDriveMotor");
        } catch (Exception e) {
            DbgLog.msg(e.getLocalizedMessage());
        }
        // Get the right drive motor.
        try {
            this.rightDriveMotor = this.hardwareMap.dcMotor.get("rightDriveMotor");
            this.rightDriveMotor.setDirection (DcMotor.Direction.REVERSE);
        } catch (Exception e) {
            DbgLog.msg(e.getLocalizedMessage());
        }
        // Get the back distance sensor
        try {
            this.distSensorBack = this.hardwareMap.ultrasonicSensor.get("distSensorBack");
        } catch (Exception e) {
            DbgLog.msg(e.getLocalizedMessage());
        }
        // Get the front distance sensor
        try {
            this.distSensorFront = this.hardwareMap.ultrasonicSensor.get("distSensorFront");
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
        if (cycleCount % 10 ==0)
        {
            distFront = this.distSensorFront.getUltrasonicLevel();
            distBack = this.distSensorBack.getUltrasonicLevel();
        }
        cycleCount++;

        double motorLeft = 1;
        double motorRight = 1;

        if (distFront > distBack) {

            motorLeft = 0;

        }

        else if (distBack > distFront) {

            motorRight = 0;

        }

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
