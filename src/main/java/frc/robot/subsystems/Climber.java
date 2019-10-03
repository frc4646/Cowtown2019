/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {

  private final Spark frontClimberSpark;
  private final Spark backClimberSpark;
  private final Encoder frontClimberEncoder;
  private final Encoder backClimberEncoder;
  private final int encoderCountsPerInch;

  public Climber()
  {
    frontClimberSpark = new Spark(RobotMap.frontClimberPort);
    backClimberSpark = new Spark(RobotMap.backClimberPort);
    frontClimberEncoder = new Encoder(RobotMap.frontClimberEncoderPort1, RobotMap.frontClimberEncoderPort2);
    backClimberEncoder = new Encoder(RobotMap.backClimberEncoderPort1, RobotMap.backClimberEncoderPort2);
    encoderCountsPerInch = -1; //Undetermined
  }

  @Override
  public void initDefaultCommand() {
  }

  public void backDrive(double speed) {
    backClimberSpark.set(speed);
  }

  public void frontDrive(double speed) {
    frontClimberSpark.set(speed);
  }

  public void resetEncoders()
  {
    frontClimberEncoder.reset();
    backClimberEncoder.reset();
  }

  public int getDriveEncodersCount(int encoder)
  {
    if (encoder == 1) return frontClimberEncoder.get();
    else if (encoder == 2) return backClimberEncoder.get();
    else return -1;
  }

  public double getDriveEncodersDistance(int encoder)
  {
    return getDriveEncodersCount(encoder) / encoderCountsPerInch;
  }
}
