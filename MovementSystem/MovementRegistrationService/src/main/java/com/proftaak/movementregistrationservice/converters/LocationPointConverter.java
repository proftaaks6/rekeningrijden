package com.proftaak.movementregistrationservice.converters;

import com.proftaak.movementregistrationservice.models.LocationPoint;
import com.proftaak.movementregistrationservice.models.Vehicle;

import java.util.ArrayList;
import java.util.List;
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
        return new com.proftaak.movementregistrationservice.shared.LocationPoint(locationPoint.getId(), locationPoint.getLongitude(), locationPoint.getLatitude());
    }

    public List<com.proftaak.movementregistrationservice.shared.LocationPoint> toShared(List<LocationPoint> locationPoints){
        List<com.proftaak.movementregistrationservice.shared.LocationPoint> sharedModels = new ArrayList<>();

        for (LocationPoint point : locationPoints) {
            sharedModels.add(toShared(point));
        }

        return sharedModels;
    }
}
