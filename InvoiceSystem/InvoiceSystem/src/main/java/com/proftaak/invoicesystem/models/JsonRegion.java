package com.proftaak.invoicesystem.models;

public class JsonRegion {
    private double id;
    private double topLeftLat;
    private double topLeftLong;
    private double bottomRightLat;
    private double bottomRightLong;
    private double taxRate;

    public JsonRegion() {
    }

    public JsonRegion(double topLeftLong, double topLeftLat, double bottomRightLong,  double bottomRightLat, double taxRate) {
        this.topLeftLat = topLeftLat;
        this.topLeftLong = topLeftLong;
        this.bottomRightLat = bottomRightLat;
        this.bottomRightLong = bottomRightLong;
        this.taxRate = taxRate;
    }

    public JsonRegion(double id, double topLeftLat, double topLeftLong, double bottomRightLat, double bottomRightLong, double taxRate) {
        this.id = id;
        this.topLeftLat = topLeftLat;
        this.topLeftLong = topLeftLong;
        this.bottomRightLat = bottomRightLat;
        this.bottomRightLong = bottomRightLong;
        this.taxRate = taxRate;
    }

    public double getTopLeftLat() {
        return topLeftLat;
    }

    public void setTopLeftLat(double topLeftLat) {
        this.topLeftLat = topLeftLat;
    }

    public double getTopLeftLong() {
        return topLeftLong;
    }

    public void setTopLeftLong(double topLeftLong) {
        this.topLeftLong = topLeftLong;
    }

    public double getBottomRightLat() {
        return bottomRightLat;
    }

    public void setBottomRightLat(double bottomRightLat) {
        this.bottomRightLat = bottomRightLat;
    }

    public double getBottomRightLong() {
        return bottomRightLong;
    }

    public void setBottomRightLong(double bottomRightLong) {
        this.bottomRightLong = bottomRightLong;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }
}
