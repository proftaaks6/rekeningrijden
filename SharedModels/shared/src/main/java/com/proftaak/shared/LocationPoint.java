package com.proftaak.shared;

import java.util.Date;

public class LocationPoint {
    private long id;
    private double longitude;
    private double latitude;
    private Date date;

    public LocationPoint() {

    }

    public LocationPoint(double longitude, double latitude, Date date) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public Date getDate() {
        return date;
    }
}
