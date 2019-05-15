package com.proftaak.movementregistrationservice.shared;

import java.util.ArrayList;
import java.util.List;

public class Tracker {

    private long id;

    private boolean active;

    private List<LocationPoint> locationPoints;

    public Tracker() {

    }

    public Tracker(long id, boolean active, List<LocationPoint> locationPoints) {
        this.id = id;
        this.active = active;
        this.locationPoints = locationPoints;
    }

    public Tracker(long id, boolean active) {
        this(id, active, new ArrayList<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<LocationPoint> getLocationPoints()
    {
        return locationPoints;
    }

    public void setLocationPoints(List<LocationPoint> locationPoints)
    {
        this.locationPoints = locationPoints;
    }
}
