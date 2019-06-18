package com.proftaak.driverapplication.debug;

import com.proftaak.driverapplication.models.DriverUser;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class Kickoff {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void start(){
        em.find(DriverUser.class, 1);
    }
}