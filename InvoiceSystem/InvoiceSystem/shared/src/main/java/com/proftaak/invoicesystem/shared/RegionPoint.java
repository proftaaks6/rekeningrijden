package com.proftaak.invoicesystem.shared;

import java.io.Serializable;
import java.util.Objects;

public class RegionPoint implements Serializable {

    private int id;

    private double longitude;

    private double latitude;



    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public RegionPoint(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public RegionPoint(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionPoint that = (RegionPoint) o;
        return Double.compare(that.longitude, longitude) == 0 &&
                Double.compare(that.latitude, latitude) == 0;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, latitude, longitude);
    }

    public int getId() {
        return id;
    }
}
