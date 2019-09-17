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
public class Climber extends Subsystem {

  private final Spark frontClimberSpark;
  private final Spark backClimberSpark;

  public Climber()
  {
    frontClimberSpark = new Spark(RobotMap.frontClimberPort);
    backClimberSpark = new Spark(RobotMap.backClimberPort);
  }

  @Override
  public void initDefaultCommand() {
  }

  void backClimber(double speed) {
    backClimberSpark.set(speed);
  }

  void frontClimber(double speed) {
    frontClimberSpark.set(speed);
  }
}
