package com.proftaak.invoicesystem.dao;

import com.proftaak.invoicesystem.models.Invoice;
import com.proftaak.movementregistrationservice.shared.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class VehicleProcessingDaoImplTest {

    VehicleProcessingDaoImpl dao = new VehicleProcessingDaoImpl();

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        dao.setEm(em);

        List<VehicleTracker> trackerList = new ArrayList<>();
        trackerList.add(new VehicleTracker(new Vehicle(), new Tracker(), new Date(), new Date()));
        Vehicle vehicle = new Vehicle(0, VehicleType.CAR, "testChassis", FuelType.DIESEL, 10, trackerList);
        dao.addNewVehicle("testVehicle");
    }

    @After
    public void tearDown() throws Exception {
        dao.getEm().getTransaction().rollback();
    }

    @Test
    public void addNewVehicle() {
        assertEquals(1, dao.getAllVehicles().size());
        dao.addNewVehicle("testVehicle2");
        assertEquals(2, dao.getAllVehicles().size());
    }

    @Test
    public void getAllVehicles() {
        assertEquals(1, dao.getAllVehicles().size());
    }

    @Test
    public void getVehicleByChassis() {
        assertEquals(1, dao.getVehicleByChassis("testVehicle").getId());
    }
}