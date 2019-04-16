package com.proftaak.movementregistrationservice.Dao;

import com.proftaak.movementregistrationservice.models.LocationPoint;
import com.proftaak.movementregistrationservice.models.Tracker;
import com.proftaak.movementregistrationservice.models.Vehicle;

import java.util.List;

public interface RegistrationDao {
    boolean addTracker(Tracker tracker);
    boolean editTrackerActiveStatus(boolean active, long targetTrackerId);
    boolean editTrackerLocationPoints(List<LocationPoint> points, long targetTrackerId);
    boolean editTrackerVehicles(List<Vehicle> vehicles, long targetTrackerId);
    boolean removeTracker(long targetTrackerId);
    boolean addVehicle(Vehicle vehicle);
    boolean addTrackerToVehicle(Tracker tracker, long vehicleId);
    boolean removeVehicle(long vehicleId);
    Vehicle getVehicleByChassisNumber(String chassisNumber);
    List<Vehicle> getStolenVehicles();
    List<Vehicle> getAllVehicles();
    List<Tracker> getAllTrackers();
}
