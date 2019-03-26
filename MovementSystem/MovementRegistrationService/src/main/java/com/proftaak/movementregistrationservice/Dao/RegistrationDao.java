package com.proftaak.movementregistrationservice.Dao;

import com.proftaak.invoicesystem.shared.LocationPoint;
import com.proftaak.invoicesystem.shared.Tracker;
import com.proftaak.invoicesystem.shared.Vehicle;

import java.util.List;

public interface RegistrationDao {
    boolean addTracker(Tracker tracker);
    boolean editTrackerActiveStatus(boolean active, int targetTrackerId);
    boolean editTrackerLocationPoints(List<LocationPoint> points, int targetTrackerId);
    boolean editTrackerVehicles(List<Vehicle> vehicles, int targetTrackerId);
    boolean removeTracker(int targetTrackerId);
    boolean addVehicle(Vehicle vehicle);
    boolean addTrackerToVehicle(Tracker tracker, int vehicleId);
}
