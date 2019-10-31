/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LinkageTeleOp extends Command {
  private final double minValue = 0.1; //any joystick reading between -minPower and minPower will not affect lift power (lift will hold)
  double liftPower;
  double slope;
  double power;

  public LinkageTeleOp() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_fourBarLinkage);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_fourBarLinkage.HoldHeight();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (!Robot.m_fourBarLinkage.GetLiftToHeight())
    {
      if (Robot.m_io.getMechJoyY() > minValue)
      {
        slope = (Robot.m_fourBarLinkage.MAX_POWER - Robot.m_fourBarLinkage.HOLD_POWER)/(1.0 - minValue);
        power = slope * (Robot.m_io.getMechJoyY() - 1.0) + Robot.m_fourBarLinkage.MAX_POWER;
      }
      else if (Robot.m_io.getMechJoyY() < -minValue)
      {
        slope = (Robot.m_fourBarLinkage.MIN_POWER - Robot.m_fourBarLinkage.HOLD_POWER)/(minValue - 1.0);
        power = slope * (Robot.m_io.getMechJoyY() + 1.0) + Robot.m_fourBarLinkage.MIN_POWER;
      }
      else
      {
        power = Robot.m_fourBarLinkage.HOLD_POWER;
        System.out.printf("Lift Tele Op is holding!");
      }
      
      Robot.m_fourBarLinkage.LiftAtSpeed(power);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_fourBarLinkage.HoldHeight();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
