package com.proftaak.invoicesystem.converters;



import com.proftaak.invoicesystem.shared.PriceRow;
import com.proftaak.invoicesystem.shared.LocationPoint;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PriceRowConverter {
    public static PriceRow fromEntity(com.proftaak.invoicesystem.models.PriceRow entity){
        PriceRow row = new PriceRow();
        row.setDistance(entity.getDistance());
        row.setLocationPoints(entity.getLocationPoints().stream().map(LocationPointInvoiceConverter::fromEntity).collect(Collectors.toList()));
        row.setId(entity.getId());
        row.setPrice(entity.getPrice());
        row.setRegionId(entity.getRegion().getId());
        return row;
    }
}
