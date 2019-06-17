package com.proftaak.invoicesystem.dao;


import com.proftaak.invoicesystem.models.VehicleProcessingState;

import javax.persistence.EntityManager;
import java.util.List;

public interface VehicleProcessingDao {
    boolean addNewVehicle(String chassisNumber);
    List<VehicleProcessingState> getAllVehicles();
    VehicleProcessingState getVehicleByChassis(String chassis);
    void setEm(EntityManager em);
    EntityManager getEm();
}
