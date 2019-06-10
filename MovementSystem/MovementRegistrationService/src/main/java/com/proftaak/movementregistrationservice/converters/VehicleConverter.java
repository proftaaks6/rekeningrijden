package com.proftaak.movementregistrationservice.converters;

import com.proftaak.movementregistrationservice.models.Vehicle;
import com.proftaak.movementregistrationservice.service.RegistrationService;
import com.proftaak.movementregistrationservice.shared.FuelType;
import com.proftaak.movementregistrationservice.shared.VehicleType;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class VehicleConverter {

    @Inject
    VehicleTrackerConverter vehicleTrackerConverter;

    @Inject
	RegistrationService registrationService;

    public Vehicle toEntity(com.proftaak.movementregistrationservice.shared.Vehicle sharedVehicle){
    	return registrationService.getVehicleByChassisNumber(sharedVehicle.getChassisNumber());
    }

    public com.proftaak.movementregistrationservice.shared.Vehicle toShared(Vehicle vehicle){
        return new com.proftaak.movementregistrationservice.shared.Vehicle((int)vehicle.getId(),
                VehicleType.valueOf(vehicle.getVehicleType().getType()), vehicle.getChassisNumber(),
                FuelType.valueOf(vehicle.getFuelType().getType()), vehicle.getEmission(), vehicleTrackerConverter.toShared(vehicle.getTrackers(), true));
    }

    public List<com.proftaak.movementregistrationservice.shared.Vehicle> toShared(List<Vehicle> vehicles){
        List<com.proftaak.movementregistrationservice.shared.Vehicle> sharedModels = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            sharedModels.add(toShared(vehicle));
        }

        return sharedModels;
    }
}
