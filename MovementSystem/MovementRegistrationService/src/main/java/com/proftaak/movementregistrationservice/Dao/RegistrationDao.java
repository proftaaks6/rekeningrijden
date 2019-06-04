package com.proftaak.movementregistrationservice.Dao;

import com.proftaak.movementregistrationservice.models.LocationPoint;
import com.proftaak.movementregistrationservice.models.Tracker;
import com.proftaak.movementregistrationservice.models.Vehicle;

import com.proftaak.movementregistrationservice.models.VehicleTracker;
import java.util.Date;
import java.util.List;

public interface RegistrationDao {
    boolean addTracker(Tracker tracker);
    boolean editTrackerActiveStatus(boolean active, long targetTrackerId);
    boolean editTrackerLocationPoints(List<LocationPoint> points, long targetTrackerId);
    boolean addTrackerLocationPiont(LocationPoint point, long trackerId);
    boolean editTrackerVehicle(Vehicle vehicles, long targetTrackerId);
    boolean removeTracker(long targetTrackerId);
    Vehicle addVehicle(Vehicle vehicle);
    boolean addTrackerToVehicle(Tracker tracker, long vehicleId);
    boolean removeVehicle(long vehicleId);
    Tracker getTrackedById(long trackerId);
    Vehicle getVehicleByChassisNumber(String chassisNumber);
    List<Vehicle> getStolenVehicles();
    List<Vehicle> getAllVehicles();
    List<Tracker> getAllTrackers();
    List<LocationPoint> getLocationPointsForTracker(long trackerId);
    List<LocationPoint> getLocationPointsForVehicle(String chassis, Date start, Date end);
    VehicleTracker getVehicleTracker(long vehicleId, long trackerId);
}
