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

  NetworkTable hatchTargetTable;

  NetworkTableEntry isTrackingEntry;
  NetworkTableEntry targetXEntry;
  NetworkTableEntry targetYEntry;
  NetworkTableEntry targetArea;

  public Vision()
  {
    inst = NetworkTableInstance.getDefault();

    hatchTargetTable = inst.getTable("Microsoft® LifeCam HD-3000");

    isTrackingEntry = hatchTargetTable.getEntry("is_valid");
    targetXEntry = hatchTargetTable.getEntry("yaw");
    targetYEntry = hatchTargetTable.getEntry("pitch");
    targetArea = hatchTargetTable.getEntry("distance");

    inst.startClientTeam(4646);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

    // This does not need a default command.
  }

  public void updatesCameraValues()
  {
    //add an entry listener for changed values of "X", the lambda ("->" operator)
      //defines the code that should run when "X" changes

    hatchTargetTable.addEntryListener("is_valid", (table, key, entry, value, flags) -> {
      System.out.println("is_valid changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    hatchTargetTable.addEntryListener("yaw", (table, key, entry, value, flags) -> {
      System.out.println("yaw changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    hatchTargetTable.addEntryListener("pitch", (table, key, entry, value, flags) -> {
      System.out.println("pitch changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    hatchTargetTable.addEntryListener("distance", (table, key, entry, value, flags) -> {
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

  public boolean IsTracking()
  {
    return isTrackingEntry.getBoolean(false);
  }

  public double TargetXEntry()
  {
    return targetXEntry.getDouble(-1);
  }

  public double TargetYEntry()
  {
    return targetYEntry.getDouble(-1);
  }

  //Returns the sum of the area of both the vision tape targets,
  //only if both tapes are visible.
  public double TargetArea()
  {
    return targetArea.getDouble(-1);
  }

  public double[] GetDistAng()
  {
    if (IsTracking())
    {
      double distance = TargetArea(); //TODO: Get the actual distance in feet, not the area. Max area should be arround 20K.
      double angle = -Math.atan(distance / TargetXEntry());
      double[] distAng = new double[]{distance, angle};
      return distAng;
    }
    else
    {
      return new double[]{-1};
    }
  }

  public double[] TimeAwayAtSpeed() //In seconds and percentage speed.
  {
    if (IsTracking()) 
    {
      //Lets assume that it takes one second for the robot to 
        //drive one foot when running at 50 percent drivetrain power.
      double speed = .5;
      double timeToDistScale = 1;
      double time = GetDistAng()[0] * timeToDistScale;
      double[] timeSped = new double[]{time, speed};
      return timeSped;
    }
    else
    {
      return new double[]{-1};
    }
  }
}
