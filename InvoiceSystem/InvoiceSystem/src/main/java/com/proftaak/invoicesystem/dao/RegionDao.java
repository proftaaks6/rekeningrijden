package com.proftaak.invoicesystem.dao;


import com.proftaak.invoicesystem.models.SquareRegion;

import java.util.List;

public interface RegionDao {
    boolean saveRegion(SquareRegion region);
    List<SquareRegion> getAllRegions();
    boolean removeRegions();
    boolean removeRegionById(int id);
}
