package com.proftaak.invoicesystem.models;

import javax.ejb.Local;
import javax.persistence.*;

@Entity
@Table(name="tbl_locationPoint")
public class LocationPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private double longitude;

    @Column
    private double latitude;

    public LocationPoint(int id, double longitude, double latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public LocationPoint(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public LocationPoint(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
