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
import com.analog.adis16448.frc.ADIS16448_IMU.Axis;

import com.analog.adis16448.frc.ADIS16448_IMU;

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

  private final TalonSRX frontLeftDrive;
  private final TalonSRX frontRightDrive;
  private final VictorSPX backLeftDrive;
  private final VictorSPX backRightDrive;
  /*private final Encoder frontLeftEncoder;
  private final Encoder frontRightEncoder;
  private final Encoder backLeftEncoder;
  private final Encoder backRightEncoder;*/

  private ADIS16448_IMU gyro;
  private int direction;
  //private final int encoderCountsPerInch;

  public Drivetrain()
  {
    frontLeftDrive = new TalonSRX(RobotMap.frontLeftDrivePort);
    frontRightDrive = new TalonSRX(RobotMap.frontRightDrivePort);
    backLeftDrive = new VictorSPX(RobotMap.backLeftDrivePort);
    backRightDrive = new VictorSPX(RobotMap.backRightDrivePort);

    /*frontLeftEncoder = new Encoder(RobotMap.frontLeftEncoderPort1, RobotMap.frontLeftEncoderPort2);
    frontRightEncoder = new Encoder(RobotMap.frontRightEncoderPort1, RobotMap.frontRightEncoderPort2);
    backLeftEncoder = new Encoder(RobotMap.backLeftEncoderPort1, RobotMap.backLeftEncoderPort2);
    backRightEncoder = new Encoder(RobotMap.backRightEncoderPort1, RobotMap.backRightEncoderPort2);*/

    frontRightDrive.setInverted(true);
    backRightDrive.setInverted(true);
  
    gyro = new ADIS16448_IMU(Axis.kX); 

    direction = 1;
  }

  @Override
  public void initDefaultCommand() 
  {
    setDefaultCommand(new DriveTeleOp());
  }

  public void driveByPercent(double leftSpeed, double rightSpeed)
  {
    frontLeftDrive.set(ControlMode.PercentOutput, leftSpeed * direction);
    frontRightDrive.set(ControlMode.PercentOutput, rightSpeed * direction);
    backLeftDrive.set(ControlMode.PercentOutput, leftSpeed * direction);
    backRightDrive.set(ControlMode.PercentOutput, rightSpeed * direction);
  }

  public void invertDirection()
  {
    direction = -direction;
  }

  public void resetGyro()
  {
    gyro.calibrate();
  }

  public double getAngle()
  {
    return gyro.getAngle();
  }

  /*public void resetDriveEncoderCount()
  {
    frontLeftEncoder.reset();
    frontRightEncoder.reset();
    backLeftEncoder.reset();
    backRightEncoder.reset();
  }

  public double getDriveEncoderCount(int encoder)
  {
    if (encoder == 1) return frontLeftEncoder.get();
    else if (encoder == 2) return frontRightEncoder.get();
    else if (encoder == 3) return backLeftEncoder.get();
    else if (encoder == 4) return backRightEncoder.get();
    else return -1;
  }

  public double getDriveEncoderDistance(int encoder) {
		return getDriveEncoderCount(encoder) / encoderCountsPerInch;
	}*/
}
