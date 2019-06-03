package com.proftaak.invoicesystem.shared;

public class Region {
    private long id;
    private Point topLeft;
    private Point bottomRight;
    private double price;

    public Region(Point topLeft, Point bottomRight, double price) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.price = price;
    }

    public Region(long id, Point topLeft, Point bottomRight, double price) {
        this.id = id;
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.price = price;
    }

    public Region() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }
}
