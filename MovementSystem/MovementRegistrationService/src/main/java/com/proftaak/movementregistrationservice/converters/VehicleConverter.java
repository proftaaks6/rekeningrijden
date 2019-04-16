package com.proftaak.movementregistrationservice.converters;

import com.proftaak.movementregistrationservice.models.Tracker;
import com.proftaak.movementregistrationservice.models.Vehicle;
import com.proftaak.movementregistrationservice.shared.FuelType;
import com.proftaak.movementregistrationservice.shared.VehicleType;

import javax.ejb.Stateless;


@Stateless
public class VehicleConverter {


    /**
     * TODO implement this method
     * @param sharedVehicle
     * @return
     */
    public Vehicle toEntity(com.proftaak.movementregistrationservice.shared.Vehicle sharedVehicle){
            return new Vehicle(sharedVehicle.getId(),
                    com.proftaak.movementregistrationservice.models.VehicleType.valueOf(sharedVehicle.getVehicleType().getType()),
                            sharedVehicle.getChassisNumber(),
                            com.proftaak.movementregistrationservice.models.FuelType.valueOf(sharedVehicle.getFuelType().getType()),
                                    sharedVehicle.getEmission());
    }

    /**
     * TODO implement this method
     * @param vehicle
     * @return
     */
    public com.proftaak.movementregistrationservice.shared.Vehicle toShared(Vehicle vehicle){
        return new com.proftaak.movementregistrationservice.shared.Vehicle((int)vehicle.getId(), VehicleType.valueOf(vehicle.getVehicleType().getType()), vehicle.getChassisNumber(), FuelType.valueOf(vehicle.getFuelType().getType()), vehicle.getEmission());
    }
}
