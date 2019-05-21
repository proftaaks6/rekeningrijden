package com.proftaak.movementproxy;

import com.proftaak.movementproxy.models.InvalidData;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class Debug {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void Start(){
        em.find(InvalidData.class, 1);
    }
}
