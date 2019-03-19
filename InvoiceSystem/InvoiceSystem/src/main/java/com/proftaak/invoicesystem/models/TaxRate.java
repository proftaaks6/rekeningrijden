package com.proftaak.invoicesystem.models;

public class TaxRate {
    private long id;
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
