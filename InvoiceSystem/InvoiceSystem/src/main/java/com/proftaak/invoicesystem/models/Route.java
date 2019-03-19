package com.proftaak.invoicesystem.models;

import com.proftaak.shared.LocationPoint;
import com.proftaak.shared.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private long id;
    private double distance;
    private double price;
    private TaxRate taxRate;
    private List<LocationPoint> locationPoints;

    private Vehicle vehicle;

    public Route() {

    }

    public Route(double distance, double price, TaxRate taxRate, Vehicle vehicle) {
        this.distance = distance;
        this.price = price;
        this.taxRate = taxRate;
        this.vehicle = vehicle;

        this.locationPoints = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    public double getPrice() {
        return price;
    }

    public TaxRate getTaxRate() {
        return taxRate;
    }

    public List<LocationPoint> getLocationPoints() {
        return locationPoints;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
