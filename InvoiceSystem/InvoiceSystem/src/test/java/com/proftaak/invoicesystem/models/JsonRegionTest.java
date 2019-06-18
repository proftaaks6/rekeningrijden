package com.proftaak.invoicesystem.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonRegionTest {

    private JsonRegion region;

    @Before
    public void setUp() throws Exception {
        region = new JsonRegion(1, 2, 3, 4, 5);
    }
    @Test
    public void setTopLeftLat() {
        assertEquals(2, region.getTopLeftLat(), 0.01);
        region.setTopLeftLat(3);
        assertEquals(3, region.getTopLeftLat(), 0.01);

    }

    @Test
    public void setTopLeftLong() {
        assertEquals(1, region.getTopLeftLong(), 0.01);
        region.setTopLeftLong(2);
        assertEquals(2, region.getTopLeftLong(), 0.01);
    }

    @Test
    public void setBottomRightLat() {
        assertEquals(4, region.getBottomRightLat(), 0.01);
        region.setBottomRightLat(5);
        assertEquals(5, region.getBottomRightLat(), 0.01);
    }

    @Test
    public void setBottomRightLong() {
        assertEquals(4, region.getBottomRightLat(), 0.01);
        region.setBottomRightLat(5);
        assertEquals(5, region.getBottomRightLat(), 0.01);
    }

    @Test
    public void setTaxRate() {
        assertEquals(5, region.getTaxRate(), 0.01);
        region.setTaxRate(6);
        assertEquals(6, region.getTaxRate(), 0.01);
    }

    @Test
    public void setId() {
        region.setId(5d);
        assertEquals(5, region.getId(), 0.01);
    }
}