package com.proftaak.movementregistrationservice.converters;

import com.proftaak.movementregistrationservice.models.Tracker;
import com.proftaak.movementregistrationservice.models.Vehicle;
import com.proftaak.movementregistrationservice.shared.FuelType;
import com.proftaak.movementregistrationservice.shared.VehicleType;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class VehicleConverter {

    @Inject
    TrackerConverter trackerConverter;

    public Vehicle toEntity(com.proftaak.movementregistrationservice.shared.Vehicle sharedVehicle){
            return new Vehicle(sharedVehicle.getId(),
                    com.proftaak.movementregistrationservice.models.VehicleType.valueOf(sharedVehicle.getVehicleType().getType()),
                            sharedVehicle.getChassisNumber(),
                            com.proftaak.movementregistrationservice.models.FuelType.valueOf(sharedVehicle.getFuelType().getType()),
                                    sharedVehicle.getEmission());
    }

    public com.proftaak.movementregistrationservice.shared.Vehicle toShared(Vehicle vehicle){
        return new com.proftaak.movementregistrationservice.shared.Vehicle((int)vehicle.getId(),
                VehicleType.valueOf(vehicle.getVehicleType().getType()), vehicle.getChassisNumber(),
                FuelType.valueOf(vehicle.getFuelType().getType()), vehicle.getEmission(), trackerConverter.toShared(vehicle.getTracker()));
    }

    public List<com.proftaak.movementregistrationservice.shared.Vehicle> toShared(List<Vehicle> vehicles){
        List<com.proftaak.movementregistrationservice.shared.Vehicle> sharedModels = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            sharedModels.add(toShared(vehicle));
        }

        return sharedModels;
    }
}
