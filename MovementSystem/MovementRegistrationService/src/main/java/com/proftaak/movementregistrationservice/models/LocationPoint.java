package com.proftaak.movementregistrationservice.models;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="tbl_locationPoint")
public class LocationPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private double longitude;

    @Column
    private double latitude;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public LocationPoint() {

    }

    /**
     * A null date means it has been converted from shared/LocationPoint
     * Author: Rick Jeurissen
     */
    public LocationPoint(long id, double longitude, double latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public long getId() {
        return id;
    }
}
