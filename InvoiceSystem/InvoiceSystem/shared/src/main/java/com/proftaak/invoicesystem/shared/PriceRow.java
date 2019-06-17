package com.proftaak.invoicesystem.shared;

import java.util.ArrayList;
import java.util.List;

public class PriceRow {
    private long id;

    private double distance = 0;

    private double price;

    private int regionId;

    private List<LocationPoint> locationPoints = new ArrayList<>();

    public PriceRow(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public List<LocationPoint> getLocationPoints() {
        return locationPoints;
    }

    public void setLocationPoints(List<LocationPoint> locationPoints) {
        this.locationPoints = locationPoints;
    }
    
}
