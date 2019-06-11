package com.proftaak.movementregistrationservice.service;

import com.proftaak.movementregistrationservice.Dao.RegistrationDao;
import com.proftaak.movementregistrationservice.converters.VehicleConverter;
import com.proftaak.movementregistrationservice.models.LocationPoint;
import com.proftaak.movementregistrationservice.models.Tracker;
import com.proftaak.movementregistrationservice.models.Vehicle;
import com.proftaak.movementregistrationservice.models.VehicleTracker;
import com.proftaak.movementregistrationservice.utils.RestCommuncationHelper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Stateless
public class RegistrationService {

    @Inject
    private VehicleConverter vehicleConverter;

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

            //Todo: Fix this "Unknown host exception" error.
            // Make rest call to invoice system to add vehicle
            if(System.getenv("environment") != null && System.getenv("environment").equals("production")) {
                RestCommuncationHelper.postRequest("http://invoicesystem:8080/deploy/v1/vehicleprocessing/vehicle/" + v.getId());
            } else {
                RestCommuncationHelper.postRequest("http://localhost:8080/InvoiceSystem/v1/vehicleprocessing/vehicle/" + v.getChassisNumber());
            }
        } catch (Exception e) {

            return false;
        }


        return true;
    }

    public boolean addTrackerToVehicle(long trackerId, long vehicleId){
        VehicleTracker activeLink = registrationDao.getActiveVehicleTracker(vehicleId);
        if (activeLink != null) {
            activeLink.setEndDate(new Date());
            registrationDao.editVehicleTracker(activeLink);
        }
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

    public List<LocationPoint> getLocationPointsForVehicle(String chassis, Date startDate, Date endDate) {
        return registrationDao.getLocationPointsForVehicle(chassis, startDate, endDate);
    }

    public VehicleTracker getVehicleTracker(long vehicleId, long trackerId) {
        return registrationDao.getVehicleTracker(vehicleId, trackerId);
    }

    public Tracker getTrackerById(long trackerId) {
    	return registrationDao.getTrackedById(trackerId);
	}
}
