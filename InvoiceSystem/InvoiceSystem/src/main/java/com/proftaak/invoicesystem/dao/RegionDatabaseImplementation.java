package com.proftaak.invoicesystem.dao;

import com.proftaak.invoicesystem.models.SquareRegion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
public class RegionDatabaseImplementation implements RegionDao{

    @Inject
    EntityManagerProvider provider;

    @Override
    public boolean saveRegion(SquareRegion region) {
        if(region.getId() > -1){
            removeRegionById(region.getId());
        }
        region.setId(0);
        provider.getEm().persist(region);
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
            return false;
        }

    }

    @Override
    public EntityManager getEm() {
        return provider.getEm();
    }
}
