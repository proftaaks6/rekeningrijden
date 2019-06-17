package com.proftaak.invoicesystem.models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PriceRowTest {

    private PriceRow row;

    @Before
    public void setUp() throws Exception {
        SquareRegion region = new SquareRegion();
        List<RegionPoint> regionPoints = new ArrayList<>();
        regionPoints.add(new RegionPoint(1,1));
        region.setPoints(regionPoints);
        region.setId(0);
        region.setDeleted(false);
        region.setPrice(10);
        row = new PriceRow(region);
        row.setRegion(region);
        row.setDistance(20);
        List<LocationPoint> locationPoints = new ArrayList<>();
        locationPoints.add(new LocationPoint(1, 2, 3));
        row.setLocationPoints(locationPoints);
        row.setPrice(100);
        row.setVehicleId(200);
    }

    @Test
    public void getId() {
        assertEquals(0, row.getId());
    }

    @Test
    public void setDistance() {
        row.setDistance(5);
        assertEquals(5, row.getDistance(), 0.01);
    }

    @Test
    public void setPrice() {
        row.setPrice(1000);
        assertEquals(1000, row.getPrice(), 0.01);
    }

    @Test
    public void setLocationPoints() {
        assertEquals(1, row.getLocationPoints().size());
    }

    @Test
    public void setVehicleId() {
        row.setVehicleId(90);
        assertEquals(90, row.getVehicleId(), 0.01);
    }

    @Test
    public void setRegion() {
        assertEquals(10, row.getRegion().getPrice(), 0.01);
    }
}