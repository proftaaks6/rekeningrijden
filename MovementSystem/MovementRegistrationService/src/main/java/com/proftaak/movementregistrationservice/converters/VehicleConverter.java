package com.proftaak.movementregistrationservice.converters;

import com.proftaak.movementregistrationservice.models.Tracker;
import com.proftaak.movementregistrationservice.models.Vehicle;

import javax.ejb.Stateless;


@Stateless
public class VehicleConverter {


    /**
     * TODO implement this method
     * @param sharedVehicle
     * @return
     */
    public Vehicle toEntity(com.proftaak.movementregistrationservice.shared.Vehicle sharedVehicle){
        return new Vehicle();
    }

    /**
     * TODO implement this method
     * @param vehicle
     * @return
     */
    public com.proftaak.movementregistrationservice.shared.Vehicle toShared(Vehicle vehicle){
        return new com.proftaak.movementregistrationservice.shared.Vehicle();
    }
}
