package com.proftaak.invoicesystem.dao;

import com.proftaak.invoicesystem.models.VehicleProcessingState;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public VehicleProcessingState getVehicleByChassis(String chassis) {
        return em.createNamedQuery("VehicleProcessingState.getByChassis", VehicleProcessingState.class).setParameter("chassis", chassis).getSingleResult();
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
