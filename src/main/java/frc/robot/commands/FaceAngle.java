/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FaceAngle extends Command {

  private int wantedAngle;
  private boolean isWantedAngle;

  public FaceAngle(double angle) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_drivetrain.resetGyro();
    isWantedAngle = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double gyroAngle = Robot.m_drivetrain.getAngle();

    if (gyroAngle - 2.5f < wantedAngle) //If gyro angle is less than the wanted angle:
    {
      Robot.m_drivetrain.driveByPercent(0.5f, -0.5f); //Turn right
    }
    else if (gyroAngle + 2.5f > wantedAngle) //If gyro angle is greater than the wanted angle:
    {
      Robot.m_drivetrain.driveByPercent(-0.5f, 0.5f); //Turn left
    }
    else
    {
      isWantedAngle = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isWantedAngle;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
