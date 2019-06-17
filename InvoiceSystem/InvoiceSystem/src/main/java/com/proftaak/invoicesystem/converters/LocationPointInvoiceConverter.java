package com.proftaak.invoicesystem.converters;



import com.proftaak.invoicesystem.shared.PriceRow;
import com.proftaak.movementregistrationservice.shared.LocationPoint;

import java.util.List;
import java.util.stream.Collectors;

public class LocationPointInvoiceConverter {
    public static com.proftaak.invoicesystem.shared.LocationPoint fromEntity(com.proftaak.invoicesystem.models.LocationPoint entity){
        com.proftaak.invoicesystem.shared.LocationPoint point = new com.proftaak.invoicesystem.shared.LocationPoint();
        point.setDate(entity.getDate());
        point.setId(entity.getId());
        point.setLatitude(entity.getLatitude());
        point.setLongitude(entity.getLongitude());
        return point;
    }
}
