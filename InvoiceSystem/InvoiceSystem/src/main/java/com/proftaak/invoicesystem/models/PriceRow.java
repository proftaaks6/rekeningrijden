package com.proftaak.invoicesystem.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="tbl_route")
@Entity
@NamedQuery(name = "getPriceRowByVehicle", query = "SELECT p FROM PriceRow p WHERE p.vehicleId = :vehicleId")
public class PriceRow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private double distance = 0;

    @Column
    private double price;

    @ManyToOne
    private SquareRegion region;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LocationPoint> locationPoints = new ArrayList<>();

    @Column
    private int vehicleId;

    public PriceRow() {

    }

    public PriceRow(SquareRegion region) {
        this.region = region;
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

    public SquareRegion getRegion() {
        return region;
    }

    public void setRegion(SquareRegion region) {
        this.region = region;
    }

    public void calculatePriceBasedOnDistance(){
        if(region == null){
            price = distance * 0.12;
            return;
        }
        price = distance * region.getPrice();
    }
}
