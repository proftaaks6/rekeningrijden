package com.proftaak.invoicesystem.dao;

import com.proftaak.invoicesystem.models.RegionPoint;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RegionPointDatabaseImplementation implements RegionPointDao {

    @Inject
    EntityManagerProvider provider;

    @Override
    public RegionPoint getOrCreateRegionPoint(double longitude, double latitude) {
        List<RegionPoint> regionPoint = provider.getEm().createNamedQuery("RegionPoint.getByLongitudeLatitude").setParameter("longitude", longitude).setParameter("latitude", latitude).setMaxResults(1).getResultList();
        if(regionPoint == null || regionPoint.size() == 0){
            RegionPoint point = new RegionPoint(longitude, latitude);
            provider.getEm().persist(point);
            return point;
        }
        return regionPoint.get(0);
    }
}
