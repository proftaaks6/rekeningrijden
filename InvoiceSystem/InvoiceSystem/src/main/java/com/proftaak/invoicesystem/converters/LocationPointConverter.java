package com.proftaak.invoicesystem.converters;

import com.proftaak.invoicesystem.models.LocationPoint;

public class LocationPointConverter {
    public LocationPoint toEntity(com.proftaak.movementregistrationservice.shared.LocationPoint movementLocationPoint){
        return new LocationPoint(movementLocationPoint.getLongitude(), movementLocationPoint.getLatitude(), movementLocationPoint.getDate(), movementLocationPoint.getId());
    }
}
