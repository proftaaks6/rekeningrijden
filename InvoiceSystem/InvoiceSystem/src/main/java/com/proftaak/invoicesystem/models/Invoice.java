package com.proftaak.invoicesystem.models;




import javax.persistence.*;
import java.util.Date;

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

    @Column
    private boolean isPaid;

    @Column
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

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
