package com.proftaak.usersystem.dao.impl;

import com.proftaak.usersystem.dao.VehicleDao;
import com.proftaak.usersystem.models.Vehicle;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VehicleDaoImpl implements VehicleDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Vehicle vehicle) {
        em.persist(vehicle);
        em.flush();
    }

    @Override
    public Vehicle getByChassis(String chassis)
    {
        try {
            return em.createNamedQuery("Vehicle.getByChassis", Vehicle.class).setParameter("chassis", chassis).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
