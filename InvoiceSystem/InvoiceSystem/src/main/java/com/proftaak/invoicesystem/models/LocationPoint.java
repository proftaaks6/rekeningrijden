package com.proftaak.invoicesystem.models;

import javax.ejb.Local;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tbl_locationPoint")
public class LocationPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int originalId;

    @Column
    private int vehicleId;

    @Column
    private Date date;

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

    public int getOriginalId() {
        return originalId;
    }

    public void setOriginalId(int originalId) {
        this.originalId = originalId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
