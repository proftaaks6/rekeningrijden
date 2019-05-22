package com.proftaak.invoicesystem.dao;


import com.proftaak.invoicesystem.models.VehicleProcessingState;

import java.util.List;

public interface VehicleProcessingDao {
    boolean addNewVehicle(int vehicleId);
    List<VehicleProcessingState> getAllVehicles();
}
