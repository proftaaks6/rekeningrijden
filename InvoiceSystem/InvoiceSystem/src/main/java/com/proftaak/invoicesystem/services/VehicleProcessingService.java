package com.proftaak.invoicesystem.services;

import com.proftaak.invoicesystem.dao.VehicleProcessingDao;
import com.proftaak.invoicesystem.models.VehicleProcessingState;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Default
public class VehicleProcessingService {

    @Inject
    private VehicleProcessingDao processingDao;

    public boolean addNewVehicle(String vehicleChassis){
        return processingDao.addNewVehicle(vehicleChassis);
    }

    public List<VehicleProcessingState> getAllVehicles() {
        return processingDao.getAllVehicles();
    }

    public VehicleProcessingState getVehicleByChassis(String chassis){return processingDao.getVehicleByChassis(chassis);}
}
