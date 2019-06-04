package com.proftaak.movementregistrationservice.shared;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private int id;

    private VehicleType vehicleType;

    private String chassisNumber;

    private FuelType fuelType;

    private double emission;

    private Tracker activeTracker;

    private List<Tracker> trackers;

    public Vehicle() {
    }

    public Vehicle(int id, VehicleType type, String chassisNumber, FuelType fuelType, double emission, List<VehicleTracker> trackers) {
        this.id = id;
        this.vehicleType = type;
        this.chassisNumber = chassisNumber;
        this.fuelType = fuelType;
        this.emission = emission;
        this.trackers = new ArrayList<>();
        for (VehicleTracker vehicleTracker : trackers) {
            if (vehicleTracker.getEndDate() == null) {
                this.activeTracker = vehicleTracker.getTracker();
            }
            this.trackers.add(vehicleTracker.getTracker());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public double getEmission() {
        return emission;
    }

    public void setEmission(double emission) {
        this.emission = emission;
    }

    public Tracker getActiveTracker()
    {
        return activeTracker;
    }

    public void setActiveTracker(Tracker activeTracker)
    {
        this.activeTracker = activeTracker;
    }

    public List<Tracker> getTrackers()
    {
        return trackers;
    }

    public void setTrackers(List<Tracker> trackers)
    {
        this.trackers = trackers;
    }
}
