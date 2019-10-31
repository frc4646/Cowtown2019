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
import frc.robot.commands.LinkageTeleOp;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class FourBarLinkage extends Subsystem {

  private final Spark liftMotor; //The lift motor.
  private final AnalogInput liftEncoderPin; //The encoder for the lift motor.
  private final Spark cargoWheelSpark; //The intake motors.

  public final double INTAKE_SPEED, OUTAKE_SPEED;  //The intake  and outake speeds.

  private boolean liftToHeight; //True if running LinkageGoToHeight()

  private double height, pinVoltage, m, b; //The values needed to convert the voltage of the encoder to inches.

  public final double MIN_HEIGHT, MAX_HEIGHT; //The mininum  and maximum height in inches.
  private final double MIN_VALUE, MAX_VALUE; //Voltage at mininum and maximum height.

  public final double HOLD_POWER, MIN_POWER, MAX_POWER; //The hold, minimum, and maximum power of the lift motor.
  public final double DOWN_POWER, UP_POWER; //The power when moving to heights downward and upwards.
  public final double LEVEL1_HEIGHT, LEVEL2_HEIGHT, LEVEL3_HEIGHT; //Level 1, 2, and 3's height in inches.

  public FourBarLinkage()
  {
    SmartDashboard.putNumber("Lift height in inches", GetHeight());

    liftMotor = new Spark(RobotMap.linkagePositionPort);
    cargoWheelSpark = new Spark(RobotMap.cargoWheelPort);
    liftEncoderPin = new AnalogInput(RobotMap.linkageEncoderPort);

    liftToHeight = false;

    //Undetermined values below.
    INTAKE_SPEED = 0.5f;
    OUTAKE_SPEED = -0.25f;
    MIN_HEIGHT = 10.5;
    MAX_HEIGHT = 81;
    MIN_VALUE = 1.355;
    MAX_VALUE = 3.173;
    HOLD_POWER = 0.2f;
    MIN_POWER = -0.5;
    MAX_POWER = 0.8;    
    DOWN_POWER = -0.3f;
    UP_POWER = 0.5f;
    LEVEL1_HEIGHT = 19;
    LEVEL2_HEIGHT = 47;
    LEVEL3_HEIGHT = 75;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LinkageTeleOp());
  }

  //Intake methods
  public void cargoWheelIntake()
  {
    cargoWheelSpark.set(INTAKE_SPEED);
  }

  public void cargoWheelOutake()
  {
    cargoWheelSpark.set(OUTAKE_SPEED);
  }

  //LIFT METHODS
  public void LiftAtSpeed(double speed){
    liftMotor.set(speed);
  }

  public double GetHeight() //Converts voltage of analog encoder to inches.
  {
    pinVoltage = liftEncoderPin.getVoltage();
    m = (MIN_HEIGHT - MAX_HEIGHT) / (double)(MIN_VALUE - MAX_VALUE);
    b = MIN_HEIGHT - ((MIN_VALUE)*(m));
    height = ((m)*(pinVoltage)) + b;
    return height;
  }

  public void HoldHeight() //Holds the lift mechanism in place.
  {
    liftMotor.set(HOLD_POWER);
  }



  public boolean GetLiftToHeight()
  {
    return liftToHeight;
  }

  public void SetLiftToHeight(boolean value)
  {
    liftToHeight = value;
  }
}
