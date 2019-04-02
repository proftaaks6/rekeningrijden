package com.proftaak.movementregistrationservice.Dao.DaoImplementation;

import com.proftaak.movementregistrationservice.models.LocationPoint;
import com.proftaak.movementregistrationservice.models.Tracker;
import com.proftaak.movementregistrationservice.models.Vehicle;
import com.proftaak.movementregistrationservice.Dao.RegistrationDao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RegistrationDaoImplementation implements RegistrationDao{
    @PersistenceContext
    EntityManager em;


    @Override
    public boolean addTracker(Tracker tracker) {
        try{
            em.persist(tracker);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean editTrackerActiveStatus(boolean active, long targetTrackerId) {
        try{
            Tracker databaseTracker = em.createNamedQuery("Tracker.getById", Tracker.class).setParameter("id", targetTrackerId).getSingleResult();
            databaseTracker.setActive(active);
            em.merge(databaseTracker);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean editTrackerLocationPoints(List<LocationPoint> points, long targetTrackerId) {
        try{
            Tracker databaseTracker = em.createNamedQuery("Tracker.getById", Tracker.class).setParameter("id", targetTrackerId).getSingleResult();
            databaseTracker.setLocationPoints(points);
            em.merge(databaseTracker);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean editTrackerVehicles(List<Vehicle> vehicles, long targetTrackerId) {
        try{
            Tracker databaseTracker = em.createNamedQuery("Tracker.getById", Tracker.class).setParameter("id", targetTrackerId).getSingleResult();
            databaseTracker.setVehicles(vehicles);
            em.merge(databaseTracker);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean removeTracker(long targetTrackerId) {
        try{
            em.remove(em.createNamedQuery("Tracker.getById", Tracker.class).setParameter("id", targetTrackerId).getSingleResult());
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        try{
            if(vehicle.getFuelType() != null && vehicle.getChassisNumber() != null && vehicle.getVehicleType() != null){
                em.persist(vehicle);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean addTrackerToVehicle(Tracker tracker, long vehicleId) {
        try{
            Vehicle databaseVehicle = em.createNamedQuery("Vehicle.getById", Vehicle.class).setParameter("id", vehicleId).getSingleResult();
            //Todo: Am not sure whether or not this tracker already exists in database, if it does at this point then it will be duplicated unless the tracker object has an id. Subject to change if need be.
            databaseVehicle.addTracker(tracker);
            em.merge(databaseVehicle);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean removeVehicle(long vehicleId) {
        Vehicle vehicle = em.createNamedQuery("Vehicle.getById", Vehicle.class).setParameter("id", vehicleId).getSingleResult();

        if(vehicle != null){
            em.remove(vehicle);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Vehicle getVehicleByChassisNumber(String chassisNumber) {
        Vehicle vehicle = em.createNamedQuery("Vehicle.getByChassisNumber", Vehicle.class).setParameter("chassisNumber", chassisNumber).getSingleResult();

        return vehicle;
    }

    @Override
    public List<Vehicle> getStolenVehicles() {
        List<Vehicle> vehicles = em.createNamedQuery("Vehicle.getStolenVehicles", Vehicle.class).getResultList();
        return vehicles;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = em.createNamedQuery("Vehicle.getAll", Vehicle.class).getResultList();
        return vehicles;
    }
}
