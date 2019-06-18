package com.proftaak.invoicesystem.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class InvoiceTest {

    private Invoice invoice;

    @Before
    public void setUp() throws Exception {
        invoice = new Invoice("testChassis", 10, 1);
    }

    @Test
    public void getDate() {
        assertEquals(invoice.getDate().getDay(), new Date().getDay());
    }

    @Test
    public void setDate() {
        Date date = new Date();
        invoice.setDate(date);
        assertEquals(date, invoice.getDate());
    }

    @Test
    public void getVehicleChassis() {
        assertEquals("testChassis", invoice.getVehicleChassis());
    }

    @Test
    public void setVehicleChassis() {
        assertEquals("testChassis", invoice.getVehicleChassis());
        invoice.setVehicleChassis("testChassis2");
        assertEquals("testChassis2", invoice.getVehicleChassis());
    }

    @Test
    public void setTotalDistance() {
        assertEquals(10, invoice.getTotalDistance(), 0.01);
        invoice.setTotalDistance(20);
        assertEquals(20, invoice.getTotalDistance(), 0.01);
    }

    @Test
    public void setTotalPrice() {
        assertEquals(1, invoice.getTotalPrice(), 0.01);
        invoice.setTotalPrice(2);
        assertEquals(2, invoice.getTotalPrice(), 0.01);
    }

    @Test
    public void isPaid() {
        invoice.setPaid(true);
        assertTrue(invoice.isPaid());
    }

    @Test
    public void setPaid() {
        invoice.setPaid(true);
        assertTrue(invoice.isPaid());
    }

    @Test
    public void getId() {
        assertEquals(0, invoice.getId());
    }

    @Test
    public void getTotalDistance() {
        assertEquals(10, invoice.getTotalDistance(), 0.01);
    }

    @Test
    public void getTotalPrice() {
        assertEquals(1, invoice.getTotalPrice(), 0.01);

    }
}