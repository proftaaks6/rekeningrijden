package com.proftaak.invoicesystem.dao;


import com.proftaak.invoicesystem.models.SquareRegion;

import com.proftaak.invoicesystem.shared.Region;
import javax.persistence.EntityManager;
import java.util.List;

public interface RegionDao {
    SquareRegion saveRegion(SquareRegion region);
    List<SquareRegion> getAllRegions();
    boolean removeRegions();
    boolean removeRegionById(int id);
    EntityManager getEm();
}
