/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

//Commands
import frc.robot.commands.FindHatch;
import frc.robot.commands.FaceAngle;
import frc.robot.commands.DriveByTime;
import frc.robot.Robot;
import frc.robot.commands.BeakExtend;
import frc.robot.commands.BeakRetract;
import frc.robot.commands.OpenBeak;
import frc.robot.commands.CloseBeak;

public class HatchLineUp extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HatchLineUp() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.

    //TODO: Document code.
    addSequential(new FindHatch());
    addSequential(new FaceAngle(Robot.m_vision.GetDistAng()[1]));
    addSequential(new DriveByTime(Robot.m_vision.TimeAwayAtSpeed()[1], 
      Robot.m_vision.TimeAwayAtSpeed()[1], Robot.m_vision.TimeAwayAtSpeed()[0]));
    addSequential(new BeakExtend());
    addSequential(new OpenBeak());
  }
}
