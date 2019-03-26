package com.proftaak.invoicesystem.shared;

import com.proftaak.movementregistrationservice.models.FuelType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private VehicleType vehicleType;

    @Column
    private String chassisNumber;

    @OneToOne
    private FuelType fuelType;

    @Column
    private double emission;

    @ManyToMany(mappedBy = "vehicles")
    private List<Tracker> trackers;

    public Vehicle() {

    }

    public Vehicle(VehicleType vehicleType, String chassisNumber, FuelType fuelType, double emission) {
        this.vehicleType = vehicleType;
        this.chassisNumber = chassisNumber;
        this.fuelType = fuelType;
        this.emission = emission;

        trackers = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public double getEmission() {
        return emission;
    }

    public List<Tracker> getTrackers() {
        return trackers;
    }
}
