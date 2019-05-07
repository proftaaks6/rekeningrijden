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
            //Todo: This is very slow code. You'd need to either push the entire list of points onto the tracker or get them from the database beforehand. Changed this to appender for better performance.
            Tracker databaseTracker = em.createNamedQuery("Tracker.getById", Tracker.class).setParameter("id", targetTrackerId).getSingleResult();
            databaseTracker.addLocationPoints(points);
            em.merge(databaseTracker);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean addTrackerLocationPiont(LocationPoint point, long trackerId) {
        Tracker tracker = em.createNamedQuery("Tracker.getById", Tracker.class).setParameter("id", trackerId).getSingleResult();
        tracker.addLocationPoint(point);

        em.merge(tracker);
        return true;
    }

    @Override
    public boolean editTrackerVehicle(Vehicle vehicle, long targetTrackerId) {
        try{
            Tracker databaseTracker = em.createNamedQuery("Tracker.getById", Tracker.class).setParameter("id", targetTrackerId).getSingleResult();
            databaseTracker.setVehicle(vehicle);
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
            databaseVehicle.setTracker(tracker);
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
    public Tracker getTrackedById(long trackerId) {
        try {
            return em.createNamedQuery("Tracker.getById", Tracker.class).setParameter("trackerId", trackerId).getSingleResult();
        } catch (Exception e) {
            return null;
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

    @Override
    public List<Tracker> getAllTrackers() {
        List<Tracker> trackers = em.createNamedQuery("Tracker.getAll", Tracker.class).getResultList();
        return trackers;
    }
}
