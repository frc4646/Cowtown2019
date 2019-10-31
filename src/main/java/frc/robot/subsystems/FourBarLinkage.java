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
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * The way I hope the commands work is:
 * LinkageGoToHeight overrides LinkageTeleOp
 * When nothing happening, it run LinkageHoldHeight
 */
public class FourBarLinkage extends Subsystem {

  private final Spark liftMotor; //Goes up and down (3 positions) 
  private final Spark cargoWheelSpark;
  private final AnalogInput linkageEncoder;
  private final int encoderCountsPerInch;
  
  private boolean usingJoysticks;
  
  private final int minHeight;
  private final int maxHeight;
  private final int minValue;
  private final int maxValue;
  //private final double deltaHeight = 0.5f;
  private double height;
  private double pinVoltage;
  private double m;
  private double b;

  public final double intakeSpeed;
  public final double outakeSpeed;

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
    linkageEncoder = new AnalogInput(RobotMap.linkageEncoderPort);

     //Undetermined values.
    intakeSpeed = 0.5f;
    outakeSpeed = -0.25f;
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

  public void LiftAtSpeed(double speed){
    liftMotor.set(speed);
  }
  
  /*double LiftSystem::GetHeight(){
    pinVoltage = LiftStringPotPin.GetVoltage(); //the current voltage from the string pot
    
    //this below converts volts into inches
    m = (MinHeight - MaxHeight) / (double)(MinValue - MaxValue);
    b = MinHeight - ((MinValue)*(m));
  
    height = ((m)*(pinVoltage)) + b;
  
    return height;
  }*/
  /* 
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
  }*/
  
  public void cargoWheelIntake(double speed)
  {
    cargoWheelSpark.set(speed);
  }

  public void cargoWheelOutake(double speed)
  {
    cargoWheelSpark.set(-speed);
  }

  public double Level1Height(int height)
  {
    return level1Height;
  }
  public double Level2Height(int height)
  {
    return level2Height;
  }
  public double Level3Height(int height)
  {
    return level3Height;
  }
}
