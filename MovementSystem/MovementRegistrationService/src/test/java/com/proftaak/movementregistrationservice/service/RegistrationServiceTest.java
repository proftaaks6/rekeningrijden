package com.proftaak.movementregistrationservice.service;

import com.proftaak.movementregistrationservice.Dao.DaoImplementation.RegistrationDaoImplementation;
import com.proftaak.movementregistrationservice.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceTest {

    private RegistrationDaoImplementation dao = new RegistrationDaoImplementation();

    @BeforeEach
    void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        dao.setEm(em);

        dao.addTracker(new Tracker(true));
        dao.addTrackerLocationPiont(new LocationPoint(51.44083, 5.47778, new Date()), 1);
        dao.addVehicle(new Vehicle(VehicleType.BIG_TRUCK, "asdffhngtresthdryger",  FuelType.DIESEL, 1.2, false));
        dao.addTrackerToVehicle(dao.getTrackedById(1), 1);
    }

    @AfterEach
    void tearDown() {
        dao.getEm().getTransaction().rollback();
    }

    @Test
    void addTracker() {
        assertTrue(dao.addTracker(new Tracker(true)));
    }

    @Test
    void editTrackerActiveStatus() {
        assertTrue(dao.editTrackerActiveStatus(false, 1));
    }

    @Test
    void editTrackerLocationPoints() {
        assertTrue(dao.addTrackerLocationPiont(new LocationPoint(51.44083, 5.47778, new Date()), 1));
    }

    @Test
    void editTrackerVehicle() {
        Vehicle vehicle = dao.getAllVehicles().get(0);
        vehicle.setStolen(true);
        Tracker tracker = dao.getAllTrackers().get(0);
        assertTrue(dao.editTrackerVehicle(vehicle, tracker.getId()));
    }

    @Test
    void removeTracker() {
        assertTrue(dao.removeTracker(1));
    }

    @Test
    void addVehicle() {
        assertNotNull(dao.addVehicle(new Vehicle(VehicleType.BIG_TRUCK, "chasisnumber",  FuelType.DIESEL, 1.2, false)));
    }

    @Test
    void addTrackerToVehicle() {
        Vehicle vehicle = dao.getAllVehicles().get(0);
        assertTrue(dao.addTrackerToVehicle(new Tracker(false), vehicle.getId()));
    }

    @Test
    void removeVehicle() {
        Vehicle vehicle = dao.getAllVehicles().get(0);
        assertTrue(dao.removeVehicle(vehicle.getId()));
    }

    @Test
    void getVehicleByChassisNumber() {
        assertNotNull(dao.getVehicleByChassisNumber("asdffhngtresthdryger"));
    }

    @Test
    void getStolenVehicles() {
        assertNotNull(dao.getStolenVehicles());
    }

    @Test
    void getAllVehicles() {
        assertNotNull(dao.getAllVehicles());
    }

    @Test
    void getAllTrackers() {
        assertNotNull(dao.getAllTrackers());
    }

    @Test
    void getLocationPointsForTracker() {
        Tracker tracker = dao.getAllTrackers().get(0);
        assertNotNull(dao.getLocationPointsForTracker(tracker.getId()));
    }

    @Test
    void getLocationPointsForVehicle() {
        LocalDate localDate = LocalDate.now().minusMonths(1);
        assertNotNull(dao.getLocationPointsForVehicle("asdffhngtresthdryger", Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), new Date()));
    }

//    @Test
//    void getVehicleTracker() {
//        Vehicle vehicle = dao.getAllVehicles().get(0);
//        Tracker tracker = new Tracker(true);
//        dao.addTrackerToVehicle(tracker, vehicle.getId());
//        vehicle = dao.getAllVehicles().get(0);
//        assertNotNull(dao.getVehicleTracker(vehicle.getId(), tracker.getId()));
//    }

    @Test
    void getTrackerById() {
        assertNotNull(dao.getTrackedById(1));
    }
}