package com.proftaak.movementregistrationservice.service;

import com.proftaak.movementregistrationservice.models.LocationPoint;
import com.proftaak.movementregistrationservice.models.Tracker;
import com.proftaak.movementregistrationservice.models.Vehicle;
import com.proftaak.movementregistrationservice.Dao.RegistrationDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import javax.xml.stream.Location;

@Stateless
public class RegistrationService {

    @Inject
    private RegistrationDao registrationDao;

    public boolean addTracker(Tracker tracker){
        return registrationDao.addTracker(tracker);
    }

    public boolean editTrackerActiveStatus(boolean active, long targetTrackerId){
        return registrationDao.editTrackerActiveStatus(active, targetTrackerId);
    }

    public boolean editTrackerLocationPoints(List<LocationPoint> points, long targetTrackerId){
        return registrationDao.editTrackerLocationPoints(points, targetTrackerId);
    }

    public boolean editTrackerVehicle(Vehicle vehicle, long targetTrackerId){
        return registrationDao.editTrackerVehicle(vehicle, targetTrackerId);
    }

    public boolean removeTracker(long targetTrackerId){
        return registrationDao.removeTracker(targetTrackerId);
    }

    public boolean addVehicle(Vehicle vehicle){
        return registrationDao.addVehicle(vehicle);
    }

    public boolean addTrackerToVehicle(Tracker tracker, long vehicleId){
        return registrationDao.addTrackerToVehicle(tracker, vehicleId);
    }

    public boolean removeVehicle(long vehicleId){
        return registrationDao.removeVehicle(vehicleId);
    }

    public Vehicle getVehicleByChassisNumber(String chassisNumber){
        return registrationDao.getVehicleByChassisNumber(chassisNumber);
    }

    public List<Vehicle> getStolenVehicles(){
        return registrationDao.getStolenVehicles();
    }

    public List<Vehicle> getAllVehicles(){
        return registrationDao.getAllVehicles();
    }
    public List<Tracker> getAllTrackers(){
        return registrationDao.getAllTrackers();
    }

    public List<LocationPoint> getLocationPointsForTracker(long trackerId) {
        return registrationDao.getLocationPointsForTracker(trackerId);
    }
}
