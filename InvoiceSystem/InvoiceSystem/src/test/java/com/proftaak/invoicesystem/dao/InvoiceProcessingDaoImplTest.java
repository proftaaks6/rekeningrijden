package com.proftaak.invoicesystem.dao;

import com.proftaak.invoicesystem.models.Invoice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InvoiceProcessingDaoImplTest {

    private InvoiceProcessingDaoImpl dao = new InvoiceProcessingDaoImpl();

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        dao.setEm(em);

        Invoice invoice = new Invoice();
        invoice.setTotalPrice(100);
        invoice.setTotalDistance(200);
        dao.addInvoice(invoice);
    }

    @After
    public void tearDown() throws Exception {
        dao.getEm().getTransaction().rollback();
    }

    @Test
    public void markAsPaid() {
        Invoice invoice = dao.getInvoiceById(1);
        dao.markAsPaid(1);
        invoice.setPaid(true);
        assertTrue(dao.getInvoiceById(1).isPaid());
    }

    @Test
    public void addInvoice() {
        dao.addInvoice(new Invoice());
        assertEquals(2, dao.getInvoiceById(2).getId());
    }

    @Test
    public void getInvoiceById() {
        String[] string = new String[20];
        assertEquals(0, dao.getInvoicesForUser(1, string).size());
        assertEquals(100, dao.getInvoiceById(1).getTotalPrice(), 0.01);
    }
}