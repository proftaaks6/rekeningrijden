package com.proftaak.shared;

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
