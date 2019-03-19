package com.proftaak.invoicesystem.models;

import com.proftaak.shared.Vehicle;

public class Invoice {
    private int id;
    private Vehicle vehicle;
    private double totalDistance;
    private double totalPrice;

    public Invoice() {

    }

    public Invoice(Vehicle vehicle, double totalDistance, double totalPrice) {
        this.vehicle = vehicle;
        this.totalDistance = totalDistance;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
