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
 * Add your docs here.
 */
public class FourBarLinkage extends Subsystem {

  private final Spark linkagePositionSpark; //Goes up and down (3 positions) 
  private final Spark cargoWheelSpark;
  private final Encoder linkageEncoder;
  private final int encoderCountsPerInch;
  
  private boolean liftToHeight;
  
  private final int minHeight;
  private final int maxHeight;
  private final int minValue;
  private final int maxValue;

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

  public boolean linkageGoToHeight(double wantedHeight)
  {
    double motorPower;
    double distanceDifference = Math.abs(linkageEncoder.get()-wantedHeight)/2;
    boolean isEncoderPositionHigher = linkageEncoder.get()+0.5f > wantedHeight;
    boolean isEncoderPositionLower = linkageEncoder.get()-0.5f < wantedHeight;
    double distanceDividend = 5; //Undetermined

    while (isEncoderPositionHigher || isEncoderPositionLower)
    {
      //Updates values
      distanceDifference = Math.abs(linkageEncoder.get()-wantedHeight)/2;
      isEncoderPositionHigher = linkageEncoder.get()+0.5f > wantedHeight;
      isEncoderPositionLower = linkageEncoder.get()-0.5f < wantedHeight;

      //
      if (isEncoderPositionHigher)
      {
        motorPower = -distanceDifference/distanceDividend;
      }
      else if (isEncoderPositionLower)
      {
        motorPower = distanceDifference/distanceDividend;
      }
      else
      {
        motorPower = 0;
        return true;
      }
      
      linkagePositionSpark.set(motorPower);
    }
    return false;
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
