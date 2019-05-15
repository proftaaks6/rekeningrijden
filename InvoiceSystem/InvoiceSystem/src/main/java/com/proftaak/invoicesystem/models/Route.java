package com.proftaak.invoicesystem.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="tbl_route")
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private double distance;

    @Column
    private double price;


    @OneToMany
    private List<LocationPoint> locationPoints;

    @Column
    private int vehicleId;

    public Route() {

    }

    public long getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<LocationPoint> getLocationPoints() {
        return locationPoints;
    }

    public void setLocationPoints(List<LocationPoint> locationPoints) {
        this.locationPoints = locationPoints;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
}
