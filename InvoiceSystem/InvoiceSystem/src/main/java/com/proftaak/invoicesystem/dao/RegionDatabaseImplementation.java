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
        provider.getEm().merge(region);
        return true;
    }

    @Override
    public List<SquareRegion> getAllRegions() {
        return provider.getEm().createNamedQuery("SquareRegion.allNonDeleted").getResultList();
    }

    @Override
    public boolean removeRegions() {
        provider.getEm().remove(getAllRegions());
        return true;
    }

    @Override
    public boolean removeRegionById(int id) {
        try{
            SquareRegion region = provider.getEm().createNamedQuery("SquareRegion.getById", SquareRegion.class).setParameter("id", id).getSingleResult();
            region.setDeleted(true);
            provider.getEm().merge(region);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
