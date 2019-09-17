/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;

/**
 * Add your docs here.
 */
public class FourBarLinkage extends Subsystem {

  private final Spark linkageExtenderSpark;
  private final Spark cargoWheelSpark;

  public FourBarLinkage()
  {
    linkageExtenderSpark = new Spark(RobotMap.linkageExtenderPort);
    cargoWheelSpark = new Spark(RobotMap.cargoWheelPort);
  }

  @Override
  public void initDefaultCommand() {
  }

  void linkageExtend(double speed)
  {
    linkageExtenderSpark.set(speed);
  }
  
  void linkageRetract(double speed)
  {
    linkageExtenderSpark.set(-speed);
  }

  void cargoWheelIntake(double speed)
  {
    cargoWheelSpark.set(speed);
  }

  void cargoWheelOutake(double speed)
  {
    cargoWheelSpark.set(-speed);
  }
}
