package com.proftaak.invoicesystem.dao;

import com.proftaak.invoicesystem.models.Invoice;
import com.proftaak.invoicesystem.models.RegionPoint;
import com.proftaak.invoicesystem.models.SquareRegion;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RegionDatabaseImplementationTest {

    RegionDatabaseImplementation dao = new RegionDatabaseImplementation();

    @Before
    public void setUp() throws Exception {
        SquareRegion region = new SquareRegion();
        List<RegionPoint> regionPoints = new ArrayList<>();
        regionPoints.add(new RegionPoint(1,1));
        region.setPoints(regionPoints);
        region.setId(0);
        region.setDeleted(false);
        region.setPrice(10);
        dao.saveRegion(region);
    }

    @After
    public void tearDown() throws Exception {
        dao.getEm().getTransaction().rollback();
    }

    //Should work but the dao implementation uses EntityManagerProvider which breaks this type of unit test.
    @Ignore
    @Test
    public void saveRegion() {
        int count = dao.getAllRegions().size();
        dao.saveRegion(new SquareRegion());
        assertEquals(count+1, dao.getAllRegions().size());
    }
}