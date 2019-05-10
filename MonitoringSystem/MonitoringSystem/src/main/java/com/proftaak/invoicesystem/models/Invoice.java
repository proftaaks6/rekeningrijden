package com.proftaak.invoicesystem.models;



import com.proftaak.movementregistrationservice.shared.Vehicle;

import javax.persistence.*;

@Entity
@Table(name="tbl_invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private Vehicle vehicle;

    @Column
    private double totalDistance;

    @Column
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
