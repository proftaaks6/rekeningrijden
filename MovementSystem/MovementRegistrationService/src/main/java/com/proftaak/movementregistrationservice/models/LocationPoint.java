package com.proftaak.movementregistrationservice.models;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="tbl_locationMessage")
@NamedQuery(name="LocationPoint.getLocationPointsForVehicleWithStartAndEndDate",
        query = "SELECT lp FROM LocationPoint lp " +
                "   JOIN lp.tracker t " +
                "   JOIN t.vehicleTrackers vts " +
                "   JOIN vts.vehicle v "+
                "WHERE v.chassisNumber = :chassis AND lp.date >= :startDate AND lp.date < :endDate")

public class LocationPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private double longitude;

    @Column
    private double latitude;

    @ManyToOne
    private Tracker tracker;

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

    public Tracker getTracker()
    {
        return tracker;
    }

    public void setTracker(Tracker tracker)
    {
        this.tracker = tracker;
    }
}
