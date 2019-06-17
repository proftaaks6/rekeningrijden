package com.proftaak.invoicesystem.services;

import com.proftaak.invoicesystem.converters.RegionConverter;
import com.proftaak.invoicesystem.dao.RegionDao;
import com.proftaak.invoicesystem.dao.RegionPointDao;
import com.proftaak.invoicesystem.models.JsonRegion;
import com.proftaak.invoicesystem.models.SquareRegion;

import com.proftaak.invoicesystem.shared.Point;
import com.proftaak.invoicesystem.shared.Region;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Default
public class RegionService {

    @Inject
    private RegionDao regionDao;

    @Inject
    private RegionPointDao regionPointDao;

    @Inject
    private RegionConverter regionConverter;

    private List<SquareRegion> regions = new ArrayList<>();
    public boolean saveSquareRegion(SquareRegion region){
        if (validateSquareRegion(region)) return false;

        region.setPoints(region.getPoints().stream().map(x->regionPointDao.getOrCreateRegionPoint(x.getLongitude(), x.getLatitude())).collect(Collectors.toList()));
        return regionDao.saveRegion(region);
    }

    private boolean validateSquareRegion(SquareRegion region)
    {
        if(region.getPoints().size() != 4){
            return false;
        }
        if(region.getPoints().stream().anyMatch(i -> Collections.frequency(region.getPoints(), i) > 1 )){
            return false;
        }

        if(region.getTopLeft().getLatitude() < region.getBottomRight().getLatitude() || region.getTopLeft().getLongitude() < region.getBottomRight().getLongitude()){
            return false;
        }
        return true;
    }

    public boolean removeRegions(){
        return regionDao.removeRegions();
    }

    public void reloadRegionsInMemory(){
        regions = regionDao.getAllRegions();
    }

    public SquareRegion getRegionContainingPoint(double longitude, double latitude){
        for (SquareRegion region : regions) {
            if(region.isPointInside(longitude, latitude)){
                return region;
            }
        }
        return null;
    }

    public boolean removeRegionById(int id){
        return regionDao.removeRegionById(id);
    }

    public List<SquareRegion> getRegions(){
        return regionDao.getAllRegions();
    }

    public boolean saveNewRegions(List<JsonRegion> regions)
    {
        regionDao.removeRegions();

        List<SquareRegion> newRegions = new ArrayList<>();

        for (JsonRegion region : regions)
        {
            SquareRegion squareRegion = regionConverter.toSquareEntity(new Region((long)region.getId(), new Point(region.getTopLeftLat(), region.getTopLeftLong()), new Point(region.getBottomRightLat(), region.getBottomRightLong()), region.getTaxRate()));
            if (!validateSquareRegion(squareRegion)) return false;

            newRegions.add(squareRegion);
        }

        // new for loop so every region is first validated
        for (SquareRegion region : newRegions) {
            regionDao.saveRegion(region);
        }

        return true;
    }
}
