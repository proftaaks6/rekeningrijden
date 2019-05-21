package com.proftaak.usersystem.debug;

import com.proftaak.usersystem.models.ClientUser;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class kickoff {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void start(){
        ClientUser user = em.find(ClientUser.class, 1);
    }
}
