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
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * Add your docs here.
 */
public class Beak extends Subsystem {
  
  private DoubleSolenoid beakArm;
  private DoubleSolenoid beakGrip;

  public Beak()
  {
    beakArm = new DoubleSolenoid(RobotMap.hatchExtenderPort1, RobotMap.hatchExtenderPort2);
    beakGrip = new DoubleSolenoid(RobotMap.beakGripPort1, RobotMap.beakGripPort2);
  }

  @Override
  public void initDefaultCommand() {
  }

  public void extendHatcher()
  {
    beakArm.set(Value.kForward);
  }

  public void retractHatcher()
  {
    beakArm.set(Value.kReverse);
  }

  public void openHatcher()
  {
    beakGrip.set(Value.kForward);
  }

  public void closeHatcher()
  {
    beakGrip.set(Value.kReverse);
  }
}
