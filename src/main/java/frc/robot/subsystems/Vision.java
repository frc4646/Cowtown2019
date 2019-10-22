/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

//pipeline, drive_mode, timestamp

/**
 * Add your docs here.
 */
public class Vision extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  NetworkTableInstance inst;

  NetworkTable hatchTable;
  NetworkTable cargoTable;

  NetworkTableEntry isTrackingEntry;
  NetworkTableEntry hatchXEntry;
  NetworkTableEntry hatchYEntry;
  NetworkTableEntry hatchDistance;

  public Vision()
  {
    inst = NetworkTableInstance.getDefault();

    hatchTable = inst.getTable("Microsoft® LifeCam HD-3000");
    cargoTable = inst.getTable("cargoTable");

    isTrackingEntry = hatchTable.getEntry("is_valid");
    hatchXEntry = hatchTable.getEntry("yaw");
    hatchYEntry = hatchTable.getEntry("pitch");
    hatchDistance = hatchTable.getEntry("distance");

    inst.startClientTeam(4646);
  }

  public void updatesCameraValues()
  {
    //add an entry listener for changed values of "X", the lambda ("->" operator)
      //defines the code that should run when "X" changes

    hatchTable.addEntryListener("is_valid", (table, key, entry, value, flags) -> {
      System.out.println("is_valid changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    hatchTable.addEntryListener("yaw", (table, key, entry, value, flags) -> {
      System.out.println("yaw changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    hatchTable.addEntryListener("pitch", (table, key, entry, value, flags) -> {
      System.out.println("pitch changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    hatchTable.addEntryListener("distance", (table, key, entry, value, flags) -> {
      System.out.println("distance changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    try {
      Thread.sleep(10000);
   } catch (InterruptedException ex) {
      System.out.println("Interrupted");
      Thread.currentThread().interrupt();
      return;
   }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
