package com.proftaak.invoicesystem.dao;


import com.proftaak.invoicesystem.models.VehicleProcessingState;

import java.util.List;

public interface VehicleProcessingDao {
    boolean addNewVehicle(String chassisNumber);
    List<VehicleProcessingState> getAllVehicles();
    VehicleProcessingState getVehicleByChassis(String chassis);
}
