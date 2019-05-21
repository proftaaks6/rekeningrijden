package com.proftaak.invoicesystem.models;

import javax.persistence.*;

@Entity
@Table(name="tbl_taxRate")
public class TaxRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private double ratio;

    public TaxRate() {

    }

    public TaxRate(double ratio) {
        this.ratio = ratio;
    }

    public long getId() {
        return id;
    }

    public double getRatio() {
        return ratio;
    }
}
