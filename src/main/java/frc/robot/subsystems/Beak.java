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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DigitalInput;


/**
 * Add your docs here.
 */
public class Beak extends Subsystem {
  
  private Spark beakArm;
  private DoubleSolenoid beakGrip;
  private final int BEAK_SPEED;
  private DigitalInput forwardStopper;

  public Beak()
  {
    beakArm = new Spark(RobotMap.beakExtenderPort);
    beakGrip = new DoubleSolenoid(RobotMap.beakGripPort1, RobotMap.beakGripPort2);
    BEAK_SPEED = -1; //Undetermined Value
  }

  @Override
  public void initDefaultCommand() {
  }

  public void extendBeak()
  {
    beakArm.set(BEAK_SPEED);
  }

  public boolean forwardStopperTouched()
  {
    return forwardStopper.get();
  }

  public void stopBeakArm()
  {
    beakArm.set(0);
  }

  public void retractBeak()
  {
    beakArm.set(-BEAK_SPEED);
    Timer.delay(2.5);
    beakArm.set(0);
  }

  public void openBeak()
  {
    beakGrip.set(Value.kForward);
  }

  public void closeBeak()
  {
    beakGrip.set(Value.kReverse);
  }
}
