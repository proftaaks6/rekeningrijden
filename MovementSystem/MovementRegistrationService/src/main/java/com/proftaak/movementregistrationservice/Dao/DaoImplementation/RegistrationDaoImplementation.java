package com.proftaak.movementregistrationservice.Dao.DaoImplementation;

import com.proftaak.movementregistrationservice.Dao.RegistrationDao;
import com.proftaak.movementregistrationservice.models.LocationPoint;
import com.proftaak.movementregistrationservice.models.Tracker;
import com.proftaak.movementregistrationservice.models.Vehicle;
import com.proftaak.movementregistrationservice.models.VehicleTracker;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.security.auth.login.Configuration;
import java.util.Date;
import java.util.List;

@Stateless
public class RegistrationDaoImplementation implements RegistrationDao{
    @PersistenceContext
    EntityManager em;


    @Override
    public boolean addTracker(Tracker tracker) {
        try{
            em.persist(tracker);
            em.flush();
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
            databaseTracker.addVehicleTracker(new VehicleTracker(vehicle, databaseTracker, new Date()));
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
    public Vehicle addVehicle(Vehicle vehicle) {
        try{
            if(vehicle.getFuelType() != null && vehicle.getChassisNumber() != null && vehicle.getVehicleType() != null){
                em.persist(vehicle);
                em.flush();
                return vehicle;
            }
        }catch (Exception e){
            return null;
        }
        return vehicle;

    }

    @Override
    public boolean addTrackerToVehicle(Tracker tracker, long vehicleId) {
        try{
            Vehicle databaseVehicle = em.createNamedQuery("Vehicle.getById", Vehicle.class).setParameter("id", vehicleId).getSingleResult();
            databaseVehicle.addTracker(new VehicleTracker(databaseVehicle, tracker, new Date()));
            em.merge(databaseVehicle);
            return true;
        }catch (Exception e){
            return false;
        }
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
            return em.createNamedQuery("Tracker.getById", Tracker.class).setParameter("id", trackerId).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Vehicle getVehicleByChassisNumber(String chassisNumber) {
        Vehicle vehicle = null;
        try {
            vehicle = em.createNamedQuery("Vehicle.getByChassisNumber", Vehicle.class).setParameter("chassisNumber", chassisNumber).getSingleResult();
        } catch (Exception e) {
            return vehicle;
        }
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

	@Override
	public List<LocationPoint> getLocationPointsForTracker(long trackerId)
	{
		return (List<LocationPoint>) em.createNamedQuery("Tracker.getLocationPointsForTracker").setParameter("id", trackerId).getResultList();
	}

    @Override
    public List<LocationPoint> getLocationPointsForVehicle(String chassis, Date start, Date end)
    {
     return em
                .createNamedQuery("LocationPoint.getLocationPointsForVehicleWithStartAndEndDate", LocationPoint.class)
                .setParameter("chassis", chassis)
                .setParameter("startDate", start)
                .setParameter("endDate", end)
                .getResultList();
    }

	@Override
    public VehicleTracker getVehicleTracker(long vehicleId, long trackerId) {

        return em
                .createNamedQuery("VehicleTracker.get", VehicleTracker.class)
                .setParameter("vehicleId", vehicleId)
                .setParameter("trackerId", trackerId)
                .getSingleResult();

    }

    @Override
    public VehicleTracker getActiveVehicleTracker(long vehicleId)
    {
        try
        {
            return em
                    .createNamedQuery("VehicleTracker.getActiveForVehicle", VehicleTracker.class)
                    .setParameter("vehicleId", vehicleId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public VehicleTracker editVehicleTracker(VehicleTracker vehicleTracker)
    {
        try {
            return em.merge(vehicleTracker);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public EntityManager getEm() {
        return this.em;
    }
}
