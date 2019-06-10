package com.proftaak.invoicesystem.dao;

import com.proftaak.invoicesystem.models.VehicleProcessingState;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class VehicleProcessingDaoImpl implements VehicleProcessingDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean addNewVehicle(String chassisNumber) {
        try {
            em.persist(new VehicleProcessingState(chassisNumber));
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    @Override
    public List<VehicleProcessingState> getAllVehicles() {
        try {
            return em.createNamedQuery("VehicleProcessingState.getAll", VehicleProcessingState.class).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public VehicleProcessingState getVehicleByChassis(String chassis) {
        return em.createNamedQuery("VehicleProcessingState.getByChassis", VehicleProcessingState.class).setParameter("chassis", chassis).getSingleResult();
    }
}
