/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.RobotMap;
import frc.robot.commands.DriveTeleOp;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogGyro;

/**
 * Docs: The drivetrain is powered by two VictorSPXs and two TalonSRXs.
 * The drivetrain is currently driven by running percentages, but we look
 * forward to get PID Loop movement as an option soon.
 * 
 * You can drive by percent with the function:
 * driveByPercent(double leftSpeed, double rightSpeed);
 * 
 * The drivetrain also has a gyro. You can reset the gyro's rotation with this:
 * resetGyro();
 * 
 * To get angle of the gyro, use:
 * getAngle();
 * 
 * The drivetrain also has an encoder to measure how far the motor moves.
 * 
 * To reset the encoder, use:
 * resetDriveEncoderCount();
 * 
 * To measure the encoder distance, use:
 * getDriveEncoderDistance();
 * 
 * That's all for now. This documentation is a WIP.
 */

public class Drivetrain extends Subsystem {

  private final VictorSPX frontLeftDrive;
  private final TalonSRX frontRightDrive;
  private final TalonSRX backLeftDrive;
  private final VictorSPX backRightDrive;
  private final AnalogGyro gyro;
  private final Encoder driveEncoder;
  private final int encoderCountsPerInch;

  public Drivetrain()
  {
    frontLeftDrive = new VictorSPX(RobotMap.frontLeftDrivePort);
    frontRightDrive = new TalonSRX(RobotMap.frontRightDrivePort);
    backLeftDrive = new TalonSRX(RobotMap.backLeftDrivePort);
    backRightDrive = new VictorSPX(RobotMap.backRightDrivePort);

    frontRightDrive.setInverted(true);
    backRightDrive.setInverted(true);
  
    gyro = new AnalogGyro(RobotMap.analogGyroPort);

    driveEncoder = new Encoder(RobotMap.driveEncoderLeft, RobotMap.driveEncoderRight);
    encoderCountsPerInch = 0; //Idk
  }

  @Override
  public void initDefaultCommand() 
  {
    setDefaultCommand(new DriveTeleOp());
  }

  public void driveByPercent(double leftSpeed, double rightSpeed)
  {
    frontLeftDrive.set(ControlMode.PercentOutput, leftSpeed);
    frontRightDrive.set(ControlMode.PercentOutput, rightSpeed);
    backLeftDrive.set(ControlMode.PercentOutput, leftSpeed);
    backRightDrive.set(ControlMode.PercentOutput, rightSpeed);
  }

  public void resetGyro()
  {
    gyro.calibrate();
  }

  public double getAngle()
  {
    return gyro.getAngle();
  }

  public void resetDriveEncoderCount()
  {
    driveEncoder.reset();
  }

  public double getDriveEncoderCount()
  {
    return driveEncoder.get();
  }

  public double getDriveEncoderDistance() {
		return getDriveEncoderCount() / encoderCountsPerInch;
	}
}
