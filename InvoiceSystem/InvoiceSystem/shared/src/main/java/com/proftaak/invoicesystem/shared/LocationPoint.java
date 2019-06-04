package com.proftaak.invoicesystem.shared;

import java.util.Date;

public class LocationPoint {

    private int id;

    private Date date;

    private double longitude;

    private double latitude;

    private long originalId;

    public LocationPoint(int id, Date date, double longitude, double latitude, long originalId) {
        this.id = id;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
        this.originalId = originalId;
    }

    public LocationPoint(){}

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public long getOriginalId() {
        return originalId;
    }
}
