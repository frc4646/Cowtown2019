/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  public static final int frontLeftDrivePort = 3;
  public static final int frontRightDrivePort = 2;
  public static final int backLeftDrivePort = 1;
  public static final int backRightDrivePort = 0;

  public static final int leftJoyPort = 0;
  public static final int rightJoyPort = 1;
  public static final int mechJoyPort = 2;

  //The below motor ports are not determined yet
  public static final int analogGyroPort = 0;
  public static final int driveEncoderLeft = 0;
  public static final int driveEncoderRight = 1;

  public static final int hatchPusherPort1 = 0;
  public static final int hatchPusherPort2 = 1;
  public static final int hatchExtenderPort1 = 2;
  public static final int hatchExtenderPort2 = 3;
  
  public static final int linkageExtenderPort = 0;
  public static final int cargoWheelPort = 1;

  public static final int frontClimberPort = 0;
  public static final int backClimberPort = 1;
}
