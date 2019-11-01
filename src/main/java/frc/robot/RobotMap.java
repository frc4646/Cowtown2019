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

  //Drivetrain
  public static final int frontLeftDrivePort = 2; //Done
  public static final int frontRightDrivePort = 1; //Done
  public static final int backLeftDrivePort = 3; //Done
  public static final int backRightDrivePort = 0; //Done

  //Joysticks
  public static final int leftJoyPort = 0;
  public static final int rightJoyPort = 1;
  public static final int mechJoyPort = 2;

  //Beak
  public static final int beakExtenderPort = 0; //Done
  public static final int beakGripPort1 = 0; //Done
  public static final int beakGripPort2 = 1; //Done
  public static final int compressorPort = 0; //Done
  
  //Linkage
  public static final int linkageLiftPort =  1; //Done
  public static final int cargoWheelPort = 2; //Done
  public static final int linkageEncoderPort = 0; //Done

}
