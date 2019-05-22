package com.proftaak.invoicesystem.services;

import com.proftaak.invoicesystem.dao.RegionDao;
import com.proftaak.invoicesystem.dao.RegionPointDao;
import com.proftaak.invoicesystem.dao.VehicleProcessingDao;
import com.proftaak.invoicesystem.models.SquareRegion;
import com.proftaak.invoicesystem.models.VehicleProcessingState;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Default
public class VehicleProcessingService {

    @Inject
    private VehicleProcessingDao processingDao;

    public boolean addNewVehicle(int vehicleId){
        return processingDao.addNewVehicle(vehicleId);
    }

    public List<VehicleProcessingState> getAllVehicles() {
        return processingDao.getAllVehicles();
    }
}
