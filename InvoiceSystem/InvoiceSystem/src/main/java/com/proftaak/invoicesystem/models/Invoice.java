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
                name = "Invoice.GetByVehicleChassis",
                query = "SELECT a FROM Invoice a WHERE a.vehicleChassis = :chassis"
        )
}
)
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String vehicleChassis;

    @Column
    private double totalDistance;

    @Column
    private double totalPrice;

    @Column
    private boolean isPaid;

    @Column
    private Date date;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PriceRow> priceRowList;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVehicleChassis() {
        return vehicleChassis;
    }

    public void setVehicleChassis(String vehicleChassis) {
        this.vehicleChassis = vehicleChassis;
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

    public Invoice(String vehicleChassis, double totalDistance, double totalPrice) {
        this.vehicleChassis = vehicleChassis;
        this.totalDistance = totalDistance;
        this.totalPrice = totalPrice;
        this.date = new Date();
        this.isPaid = false;
    }

    public long getId() {
        return id;
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
