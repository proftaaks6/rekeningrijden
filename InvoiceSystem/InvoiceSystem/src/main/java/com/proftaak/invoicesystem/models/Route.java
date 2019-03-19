package com.proftaak.invoicesystem.models;

import com.proftaak.shared.LocationPoint;
import com.proftaak.shared.Vehicle;

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

    @OneToOne
    private TaxRate taxRate;

    @OneToMany
    private List<LocationPoint> locationPoints;

    @OneToOne
    private Vehicle vehicle;

    public Route() {

    }

    public Route(double distance, double price, TaxRate taxRate, Vehicle vehicle) {
        this.distance = distance;
        this.price = price;
        this.taxRate = taxRate;
        this.vehicle = vehicle;

        this.locationPoints = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    public double getPrice() {
        return price;
    }

    public TaxRate getTaxRate() {
        return taxRate;
    }

    public List<LocationPoint> getLocationPoints() {
        return locationPoints;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
