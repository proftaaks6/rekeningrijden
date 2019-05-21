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

    @OneToOne
    private TaxRate taxRate;

    @OneToMany
    private List<LocationPoint> locationPoints;

    @Column
    private int vehicleId;

    public Route() {

    }

    public Route(double distance, double price, TaxRate taxRate, int vehicle) {
        this.distance = distance;
        this.price = price;
        this.taxRate = taxRate;
        this.vehicleId = vehicle;

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

    public int getVehicleId() {
        return vehicleId;
    }
}
