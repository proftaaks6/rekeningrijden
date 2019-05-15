package com.proftaak.invoicesystem.dao;

import com.proftaak.invoicesystem.models.SquareRegion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Stateless
public class RegionDatabaseImplementation implements RegionDao{

    @Inject
    EntityManagerProvider provider;

    @Override
    public boolean saveRegion(SquareRegion region) {
        provider.getEm().persist(region);
        return true;
    }
}
