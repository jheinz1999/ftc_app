package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by allinson on 9/1/16.
 */
public class EvasiveV1Manual extends OpMode {

    /*
     * The left motor controller instance.
     */
    protected DcMotor leftDriveMotor;

    /*
     * The right motor controller instance.
     */
    protected DcMotor rightDriveMotor;

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
            DbgLog.msg (e.getLocalizedMessage());
        }
        // Get the right drive motor.
        try {
            this.rightDriveMotor = this.hardwareMap.dcMotor.get("rightDriveMotor");
            this.rightDriveMotor.setDirection (DcMotor.Direction.REVERSE);
        } catch (Exception e) {
            DbgLog.msg (e.getLocalizedMessage());
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
        this.leftDriveMotor.setPower(gamepad1.left_stick_y);
        this.rightDriveMotor.setPower(gamepad1.right_stick_y);
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
