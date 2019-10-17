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
import frc.robot.commands.LinkageHoldHeight;

/**
 * The way I hope the commands work is:
 * LinkageGoToHeight overrides LinkageTeleOp
 * When nothing happening, it run LinkageHoldHeight
 */
public class FourBarLinkage extends Subsystem {

  private final Spark linkagePositionSpark; //Goes up and down (3 positions) 
  private final Spark cargoWheelSpark;
  private final Encoder linkageEncoder;
  private final int encoderCountsPerInch;
  
  private boolean usingJoysticks;
  
  private final int minHeight;
  private final int maxHeight;
  private final int minValue;
  private final int maxValue;
  private final double deltaHeight = 0.5f;

  public final int intakeSpeed;
  public final int outakeSpeed;

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
    intakeSpeed = -1;
    outakeSpeed = -1;
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
    setDefaultCommand(new LinkageHoldHeight());
  }

  public void linkageLift(double speed)
  {
    linkagePositionSpark.set(speed);
  }

  public void linkageGoToHeight(double wantedHeight)
  {
    double motorPower;
    //double distanceDifference = Math.abs(getLinkageHeight()-wantedHeight);
    boolean isEncoderPositionHigher = getLinkageHeight()+deltaHeight > wantedHeight;
    boolean isEncoderPositionLower = getLinkageHeight()-deltaHeight < wantedHeight;

    if (isEncoderPositionHigher)
    {
      motorPower = holdPower - 0.10;
    }
    else if (isEncoderPositionLower)
    {
      motorPower = holdPower + 0.15;
    }
    else
    {
      motorPower = holdPower;
    }
    
    linkagePositionSpark.set(motorPower);
  }
  

  public boolean isAtHeight(double wantedHeight)
  {
    boolean isEncoderPositionHigher = getLinkageHeight()+deltaHeight > wantedHeight;
    boolean isEncoderPositionLower = getLinkageHeight()-deltaHeight < wantedHeight;

    if (!isEncoderPositionHigher && !isEncoderPositionLower)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  public void linkageHoldHeight()
  {
    linkagePositionSpark.set(holdPower);
  }

  public boolean getUsingJoysticks()
  {
    return usingJoysticks;
  }

  public void setUsingJoysticks(boolean value)
  {
    usingJoysticks = value;
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
