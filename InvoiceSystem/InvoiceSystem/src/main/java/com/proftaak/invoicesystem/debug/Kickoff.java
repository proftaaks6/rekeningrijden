package com.proftaak.invoicesystem.debug;

import com.proftaak.invoicesystem.models.Invoice;
import com.proftaak.invoicesystem.models.Route;
import com.proftaak.invoicesystem.models.TaxRate;

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
        Invoice invoice = em.find(Invoice.class, 1);
        Route route = em.find(Route.class, 1);
        TaxRate rate = em.find(TaxRate.class, 1);
    }
}