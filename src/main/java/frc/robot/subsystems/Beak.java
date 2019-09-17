/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * Add your docs here.
 */
public class Beak extends Subsystem {
  
  private DoubleSolenoid hatchPusher;
  private DoubleSolenoid hatchExtender;

  public Beak()
  {
    hatchPusher = new DoubleSolenoid(RobotMap.hatchPusherPort1, RobotMap.hatchPusherPort2);
    hatchExtender = new DoubleSolenoid(RobotMap.hatchExtenderPort1, RobotMap.hatchExtenderPort2);
    
  }

  @Override
  public void initDefaultCommand() {
  }

  void extendHatcher()
  {
    hatchPusher.set(Value.kForward);
  }

  void retractHatcher()
  {
    hatchPusher.set(Value.kReverse);
  }

  void pushHatcher()
  {
    hatchPusher.set(Value.kForward);
  }

  void pullHatcher()
  {
    hatchPusher.set(Value.kReverse);
  }
}
