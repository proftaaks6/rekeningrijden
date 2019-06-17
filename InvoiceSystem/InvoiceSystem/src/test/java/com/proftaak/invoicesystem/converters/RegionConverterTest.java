package com.proftaak.invoicesystem.converters;

import com.proftaak.invoicesystem.models.RegionPoint;
import com.proftaak.invoicesystem.models.SquareRegion;
import com.proftaak.invoicesystem.shared.Point;
import com.proftaak.invoicesystem.shared.Region;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RegionConverterTest {

    private RegionConverter converter = new RegionConverter();
    private SquareRegion squareRegion;
    private Region region;

    @Before
    public void setUp() throws Exception {
        squareRegion = new SquareRegion();
        List<RegionPoint> regionPoints = new ArrayList<>();
        regionPoints.add(new RegionPoint(1,1));
        regionPoints.add(new RegionPoint(2,2));
        regionPoints.add(new RegionPoint(3,3));
        regionPoints.add(new RegionPoint(4,4));
        squareRegion.setPoints(regionPoints);
        squareRegion.setId(0);
        squareRegion.setDeleted(false);
        squareRegion.setPrice(10);

        region = new Region(1, new Point(1, 2), new Point(3, 4), 100);
    }

    @Test
    public void fromSquareEntity() {
        Region region = converter.fromSquareEntity(squareRegion);
        assertEquals(0, region.getId());
    }

    @Test
    public void toSquareEntity() {
        SquareRegion squareRegion2 = converter.toSquareEntity(region);
        assertEquals(1, squareRegion2.getId());
    }
}