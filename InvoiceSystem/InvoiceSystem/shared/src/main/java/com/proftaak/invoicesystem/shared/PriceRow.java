package com.proftaak.invoicesystem.shared;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class PriceRow {
    private long id;

    private double distance = 0;

    private double price;

    private SquareRegion region;

    private List<LocationPoint> locationPoints = new ArrayList<>();

    private int vehicleId;

    public PriceRow(long id, double distance, double price, SquareRegion region, List<LocationPoint> locationPoints, int vehicleId) {
        this.id = id;
        this.distance = distance;
        this.price = price;
        this.region = region;
        this.locationPoints = locationPoints;
        this.vehicleId = vehicleId;
    }

    public void calculatePriceBasedOnDistance(){
        if(region == null){
            price = 0;
            return;
        }
        price = distance * region.getPrice();
    }
}
