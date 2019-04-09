package com.proftaak.movementregistrationservice.converters;

import com.proftaak.movementregistrationservice.models.LocationPoint;
import com.proftaak.movementregistrationservice.models.Vehicle;

import javax.ejb.Stateless;


@Stateless
public class LocationPointConverter {


    /**
     * TODO implement this method
     * @param sharedPoint
     * @return
     */
    public LocationPoint toEntity(com.proftaak.movementregistrationservice.shared.LocationPoint sharedPoint){

        return new LocationPoint(sharedPoint.getId(), sharedPoint.getLongitude(), sharedPoint.getLatitude());
    }

    /**
     * TODO implement this method
     * @param locationPoint
     * @return
     */
    public com.proftaak.movementregistrationservice.shared.LocationPoint toShared(LocationPoint locationPoint){
        return new com.proftaak.movementregistrationservice.shared.LocationPoint((int)locationPoint.getId(), locationPoint.getLongitude(), locationPoint.getLatitude());
    }
}
