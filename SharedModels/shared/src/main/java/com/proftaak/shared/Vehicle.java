package com.proftaak.shared;

import com.proftaak.movementregistrationservice.models.FuelType;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private long id;
    private VehicleType vehicleType;
    private String chassisNumber;
    private FuelType fuelType;
    private double emission;
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
