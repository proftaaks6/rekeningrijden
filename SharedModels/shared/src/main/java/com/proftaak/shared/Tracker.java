package com.proftaak.shared;


import java.util.ArrayList;
import java.util.List;

public class Tracker {
    private long id;
    private boolean active;

    private List<LocationPoint> locationPoints;
    private List<Vehicle> vehicles;

    public Tracker() {

    }

    public Tracker(boolean active) {
        this.active = active;
        this.vehicles = new ArrayList<>();
        this.locationPoints =  new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<LocationPoint> getLocationPoints() {
        return locationPoints;
    }
}
