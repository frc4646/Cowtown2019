/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LinkageGoToHeight extends Command {
  private double wantedHeight;
  private double tolerance = 0.5;

  public LinkageGoToHeight(double height) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_fourBarLinkage);
    wantedHeight = height;
    System.out.printf("Constructor %f\n", wantedHeight);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_fourBarLinkage.SetLiftToHeight(true);
    System.out.printf("Intialize%f\n", wantedHeight);

    if (Robot.m_fourBarLinkage.GetHeight() > wantedHeight)
    {
      Robot.m_fourBarLinkage.LiftAtSpeed(Robot.m_fourBarLinkage.DOWN_POWER);
    }
    else
    {
      Robot.m_fourBarLinkage.LiftAtSpeed(Robot.m_fourBarLinkage.UP_POWER);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    System.out.printf("Is finished %f \n", Robot.m_fourBarLinkage.GetHeight());
    return ((wantedHeight + tolerance) >= Robot.m_fourBarLinkage.GetHeight() &&
            (wantedHeight - tolerance) <= Robot.m_fourBarLinkage.GetHeight());
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_fourBarLinkage.HoldHeight();
    Robot.m_fourBarLinkage.SetLiftToHeight(false);
    System.out.printf("This the end of the function%f\n", wantedHeight);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
