package com.proftaak.invoicesystem.services;

import com.proftaak.invoicesystem.dao.RegionDao;
import com.proftaak.invoicesystem.dao.RegionPointDao;
import com.proftaak.invoicesystem.models.SquareRegion;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.Collections;
import java.util.stream.Collectors;

@Stateless
@Default
public class RegionService {

    @Inject
    private RegionDao regionDao;

    @Inject
    private RegionPointDao regionPointDao;

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
}
