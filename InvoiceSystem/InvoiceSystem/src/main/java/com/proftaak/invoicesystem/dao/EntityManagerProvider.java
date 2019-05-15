package com.proftaak.invoicesystem.dao;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class EntityManagerProvider {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }
}
