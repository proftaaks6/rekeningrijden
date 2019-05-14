package com.proftaak.invoicesystem.models;



import com.proftaak.movementregistrationservice.shared.Vehicle;

import javax.persistence.*;

@Entity
@Table(name="tbl_invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int vehicleId;

    @Column
    private double totalDistance;

    @Column
    private double totalPrice;

    public Invoice() {

    }

    public Invoice(int vehicleId, double totalDistance, double totalPrice) {
        this.vehicleId = vehicleId;
        this.totalDistance = totalDistance;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public int getVehicle() {
        return vehicleId;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
