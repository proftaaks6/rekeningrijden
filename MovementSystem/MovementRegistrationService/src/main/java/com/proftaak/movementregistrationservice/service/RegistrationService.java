package com.proftaak.movementregistrationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proftaak.movementregistrationservice.models.LocationPoint;
import com.proftaak.movementregistrationservice.models.Tracker;
import com.proftaak.movementregistrationservice.models.Vehicle;
import com.proftaak.movementregistrationservice.Dao.RegistrationDao;

import com.proftaak.movementregistrationservice.models.VehicleTracker;
import com.proftaak.movementregistrationservice.utils.RestCommuncationHelper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import javax.xml.stream.Location;
import java.util.Date;

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

    public boolean addVehicle(Vehicle vehicle) throws IOException {
        try {
            // Add vehicle to db
            Vehicle v = registrationDao.addVehicle(vehicle);

            // Make rest call to invoice system to add vehicle
            RestCommuncationHelper.postRequest("http://invoice_system/deploy/v1/processing/vehicle", new ObjectMapper().writeValueAsString(v));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    public boolean addTrackerToVehicle(long trackerId, long vehicleId){
        return registrationDao.addTrackerToVehicle(getTrackerById(trackerId), vehicleId);
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

    public List<LocationPoint> getLocationPointsForVehicle(long vehicleId, Date startDate, Date endDate) {
        return registrationDao.getLocationPointsForVehicle(vehicleId, startDate, endDate);
    }

    public VehicleTracker getVehicleTracker(long vehicleId, long trackerId) {
        return registrationDao.getVehicleTracker(vehicleId, trackerId);
    }

    public Tracker getTrackerById(long trackerId) {
    	return registrationDao.getTrackedById(trackerId);
	}
}
