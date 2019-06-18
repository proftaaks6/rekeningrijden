package com.proftaak.invoicesystem.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class LocationPointTest {

    private LocationPoint point;

    @Before
    public void setUp() throws Exception {
        point = new LocationPoint(0, 1, new Date(), 2);
    }

    @Test
    public void setId() {
        point.setId(3);
        assertEquals(3, point.getId());
    }

    @Test
    public void setLongitude() {
        point.setLongitude(1);
        assertEquals(1, point.getLongitude(), 0.01);
    }

    @Test
    public void setLatitude() {
        point.setLatitude(2);
        assertEquals(2, point.getLatitude(), 0.01);
    }

    @Test
    public void setDate() {
        Date date = new Date();
        point.setDate(date);
        assertEquals(date, point.getDate());
    }
}