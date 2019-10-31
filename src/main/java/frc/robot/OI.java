/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.BeakExtend;
import frc.robot.commands.BeakRetract;
import frc.robot.commands.CargoIntake;
import frc.robot.commands.CargoOutake;
import frc.robot.commands.CloseBeak;
import frc.robot.commands.InvertDriveDirection;
import frc.robot.commands.OpenBeak;
import frc.robot.commands.LinkageGoToHeight;

public class OI 
{
  public OI()
  {
    // Put button mapped commands here.
    // Example: Robot.m_io.mechButton1.whenPressed(new exampleCommand1());


    //Button map to command that is not created v
    //Robot.m_io.rightButten3.whenPressed(new HatchLineUp());

    // Mech Buttons for Cargo Intake/Outake
    Robot.m_io.mechButton3.whenPressed(new CargoIntake());
    Robot.m_io.mechButton5.whenPressed(new CargoOutake());

    // Mech Button for Beak
    Robot.m_io.mechButton9.whenPressed(new BeakExtend());
    Robot.m_io.mechButton11.whenPressed(new BeakRetract());
    Robot.m_io.mechButton4.whenPressed(new OpenBeak());
    Robot.m_io.mechButton6.whenPressed(new CloseBeak());

    // Right Joy Button for Invert Drive Direction
    Robot.m_io.leftButton2.whenPressed(new InvertDriveDirection());

    // Mech Buttons for Linkage Heights
    Robot.m_io.mechButton12.whenPressed(new LinkageGoToHeight(Robot.m_fourBarLinkage.LEVEL1_HEIGHT));
    Robot.m_io.mechButton10.whenPressed(new LinkageGoToHeight(Robot.m_fourBarLinkage.LEVEL2_HEIGHT));
    Robot.m_io.mechButton8.whenPressed(new LinkageGoToHeight(Robot.m_fourBarLinkage.LEVEL3_HEIGHT));

  }
}
