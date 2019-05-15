package com.proftaak.movementregistrationservice.shared;

public class Vehicle {
    private int id;

    private VehicleType vehicleType;

    private String chassisNumber;

    private FuelType fuelType;

    private double emission;

    private Tracker tracker;

    public Vehicle(int id, VehicleType type, String chassisNumber, FuelType fuelType, double emission, Tracker tracker) {
        this.id = id;
        this.vehicleType = type;
        this.chassisNumber = chassisNumber;
        this.fuelType = fuelType;
        this.emission = emission;
        this.tracker = tracker;
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

    public Tracker getTracker()
    {
        return tracker;
    }

    public void setTracker(Tracker tracker)
    {
        this.tracker = tracker;
    }
}
