package com.proftaak.invoicesystem.shared;

import java.util.Date;
import java.util.List;

public class Invoice {
    private long id;

    private String vehicleChassis;

    private long userId;

    private double totalDistance;

    private double totalPrice;

    private boolean isPaid;

    private Date date;

    private List<PriceRow> priceRowList;


    public Invoice() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVehicleChassis() {
        return vehicleChassis;
    }

    public void setVehicleChassis(String vehicleChassis) {
        this.vehicleChassis = vehicleChassis;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public double getTotalPrice() {
        return totalPrice;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<PriceRow> getPriceRowList() {
        return priceRowList;
    }

    public void setPriceRowList(List<PriceRow> priceRowList) {
        this.priceRowList = priceRowList;
    }
}
