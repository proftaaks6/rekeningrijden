package com.proftaak.invoicesystem.models;




import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tbl_invoice")
@NamedQueries({
        @NamedQuery(
                name = "Invoice.GetById",
                query = "SELECT a FROM Invoice a WHERE a.id = :invoiceId"
        ),
        @NamedQuery(
                name = "Invoice.GetByVehicleId",
                query = "SELECT a FROM Invoice a WHERE a.vehicleId = :vehicleId"
        )
}
)
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private long vehicleId;

    @Column
    private double totalDistance;

    @Column
    private double totalPrice;

    @Column
    private boolean isPaid;

    @Column
    private Date date;

    @OneToMany
    private List<PriceRow> priceRowList;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
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
        this.date = new Date();
        this.isPaid = false;
    }

    public long getId() {
        return id;
    }

    public long getVehicle() {
        return vehicleId;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<PriceRow> getPriceRowList() {
        return priceRowList;
    }

    public void setPriceRowList(List<PriceRow> priceRowList) {
        this.priceRowList = priceRowList;
    }
}
