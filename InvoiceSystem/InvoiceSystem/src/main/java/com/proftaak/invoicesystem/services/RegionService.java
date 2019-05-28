package com.proftaak.invoicesystem.services;

import com.proftaak.invoicesystem.dao.RegionDao;
import com.proftaak.invoicesystem.dao.RegionPointDao;
import com.proftaak.invoicesystem.models.SquareRegion;

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

    private List<SquareRegion> regions = new ArrayList<>();
    public boolean saveSquareRegion(SquareRegion region){
        if(region.getPoints().size() != 4){
            return false;
        }
        if(region.getPoints().stream().anyMatch(i -> Collections.frequency(region.getPoints(), i) > 1 )){
            return false;
        }

        if(region.getTopLeft().getLatitude() < region.getBottomRight().getLatitude() || region.getTopLeft().getLongitude() > region.getBottomRight().getLongitude()){
            return false;
        }

        region.setPoints(region.getPoints().stream().map(x->regionPointDao.getOrCreateRegionPoint(x.getLongitude(), x.getLatitude())).collect(Collectors.toList()));
        return regionDao.saveRegion(region);
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

    public List<SquareRegion> getRegions(){
        return regionDao.getAllRegions();
    }
}
