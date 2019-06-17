package com.proftaak.invoicesystem.converters;

import com.proftaak.movementregistrationservice.shared.LocationPoint;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class LocationPointConverterTest {

    private LocationPointConverter converter = new LocationPointConverter();
    private LocationPoint locationPoint;

    @Before
    public void setUp() throws Exception {
        locationPoint = new LocationPoint(0, 2, 3);
    }

    @Test
    public void toEntity() {
        com.proftaak.invoicesystem.models.LocationPoint newPoint = converter.toEntity(locationPoint);
        assertEquals(3, newPoint.getLatitude(), 0.01);
        assertEquals(2, newPoint.getLongitude(), 0.01);
    }
}