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
import edu.wpi.first.wpilibj.Encoder;

/**
 * Add your docs here.
 */
public class FourBarLinkage extends Subsystem {

  private final Spark linkagePositionSpark; //Goes up and down (3 positions) 
  private final Spark cargoWheelSpark;
  private final Encoder linkageEncoder;
  private final int encoderCountsPerInch;

  private final int minHeight;
  private final int maxHeight;
  private final int minValue;
  private final int maxValue;
  
  private boolean liftToHeight;

  public final double holdPower;
  public final double minPower;
  public final double maxPower;
  public final double level1Height;
  public final double level2Height;
  public final double level3Height;

  public FourBarLinkage()
  {
    linkagePositionSpark = new Spark(RobotMap.linkagePositionPort);
    cargoWheelSpark = new Spark(RobotMap.cargoWheelPort);
    linkageEncoder = new Encoder(RobotMap.linkageEncoderPort1, RobotMap.linkageEncoderPort2);

     //Undetermined values.
    encoderCountsPerInch = -1;
    minHeight = -1;
    maxHeight = -1;
    minValue = -1;
    maxValue = -1;
    holdPower = -1;
    minPower = -1;
    maxPower = -1;
    level1Height = -1;
    level2Height = -1;
    level3Height = -1;
  }

  @Override
  public void initDefaultCommand() {
  }

  public void linkageLift(double speed)
  {
    linkagePositionSpark.set(speed);
  }

  public void linkageHoldHeight()
  {
    linkagePositionSpark.set(holdPower);
  }

  public boolean getLiftToHeight()
  {
    return liftToHeight;
  }

  public void setLiftToHeight(boolean value)
  {
    liftToHeight = value;
  }

  public void resetLinkageEncoderCount()
  {
    linkageEncoder.reset();
  }

  public double getLinkageEncoderCount()
  {
    return linkageEncoder.get();
  }

  public double getLinkageHeight() {
		return getLinkageEncoderCount() / encoderCountsPerInch;
  }
  
  public void cargoWheelIntake(double speed)
  {
    cargoWheelSpark.set(speed);
  }

  public void cargoWheelOutake(double speed)
  {
    cargoWheelSpark.set(-speed);
  }
}
