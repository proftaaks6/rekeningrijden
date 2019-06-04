package com.proftaak.invoicesystem.converters;

import com.proftaak.invoicesystem.models.SquareRegion;
import com.proftaak.invoicesystem.shared.Region;

public class RegionConverter {
    private RegionPointConverter pointConverter = new RegionPointConverter();

    public Region fromSquareEntity(SquareRegion region){
        Region region1 = new Region();

        region1.setTopLeft(pointConverter.fromEntity(region.getTopLeft()));
        region1.setBottomRight(pointConverter.fromEntity(region.getBottomRight()));
        region1.setId(region.getId());
        return region1;
    }
    public com.proftaak.invoicesystem.models.SquareRegion toSquareEntity(Region region){
        SquareRegion squareRegion = new SquareRegion();
        squareRegion.setPoints(region.getTopLeft().getLongitude(), region.getTopLeft().getLatitude(), region.getBottomRight().getLongitude(), region.getBottomRight().getLatitude());
        return squareRegion;
    }
}
