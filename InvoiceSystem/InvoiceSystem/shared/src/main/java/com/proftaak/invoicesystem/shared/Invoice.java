package com.proftaak.invoicesystem.shared;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class Invoice {
    private long id;

    private long vehicleId;

    private double totalDistance;

    private double totalPrice;

    private boolean isPaid;

    private Date date;

    private List<PriceRow> priceRowList;


    public Invoice() {

    }

    public Invoice(int vehicleId, double totalDistance, double totalPrice) {
        this.vehicleId = vehicleId;
        this.totalDistance = totalDistance;
        this.totalPrice = totalPrice;
        this.date = new Date();
        this.isPaid = false;
    }

}
