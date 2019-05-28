package com.proftaak.invoicesystem.dao;

import com.proftaak.invoicesystem.models.VehicleProcessingState;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class VehicleProcessingDaoImpl implements VehicleProcessingDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean addNewVehicle(int vehicleId) {
        try {
            em.persist(new VehicleProcessingState(vehicleId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<VehicleProcessingState> getAllVehicles() {
        try {
            return em.createNamedQuery("VehicleProcessingState.getAll", VehicleProcessingState.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public VehicleProcessingState getVehicleById(long vehicleId) {
        return em.createNamedQuery("VehicleProcessingState.getById", VehicleProcessingState.class).setParameter("vehicleId", vehicleId).getSingleResult();
    }
}
