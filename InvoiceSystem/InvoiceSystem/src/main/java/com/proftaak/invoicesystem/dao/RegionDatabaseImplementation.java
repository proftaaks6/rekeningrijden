package com.proftaak.invoicesystem.dao;

import com.proftaak.invoicesystem.models.SquareRegion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RegionDatabaseImplementation implements RegionDao{

    @Inject
    EntityManagerProvider provider;

    @Override
    public boolean saveRegion(SquareRegion region) {
        provider.getEm().persist(region);
        return true;
    }

    @Override
    public List<SquareRegion> getAllRegions() {
        return provider.getEm().createNamedQuery("SquareRegion.all").getResultList();
    }

    @Override
    public boolean removeRegions() {
        provider.getEm().remove(getAllRegions());
        return true;
    }
}
