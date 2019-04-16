package com.proftaak.movementregistrationservice.models;

public enum FuelType {
    GASOLINE("GASOLINE"),
    DIESEL("DIESEL"),
    GAS("GAS");

    private String type;

    FuelType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
