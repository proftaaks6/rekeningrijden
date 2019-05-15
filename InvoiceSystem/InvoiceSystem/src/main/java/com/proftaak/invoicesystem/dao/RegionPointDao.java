package com.proftaak.invoicesystem.dao;

import com.proftaak.invoicesystem.models.RegionPoint;

public interface RegionPointDao {
    RegionPoint getOrCreateRegionPoint(double longitude, double latitude);
}

