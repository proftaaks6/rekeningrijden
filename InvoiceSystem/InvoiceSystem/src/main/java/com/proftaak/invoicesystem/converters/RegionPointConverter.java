package com.proftaak.invoicesystem.converters;

import com.proftaak.invoicesystem.models.RegionPoint;
import com.proftaak.invoicesystem.shared.Point;

public class RegionPointConverter {
    Point fromEntity(RegionPoint entity){
        Point point = new Point();
        point.setLongitude(entity.getLongitude());
        point.setLatitude(entity.getLatitude());
        return point;
    }
}
