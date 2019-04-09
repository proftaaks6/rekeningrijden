package com.proftaak.movementregistrationservice.shared;

public enum VehicleType {
    BIG_TRUCK("BIG_TRUCK"),
    SMALL_TRUCK("SMALL_TRUCK"),
    CAR("CAR"),
    BUS("BUS"),
    MOTOR("MOTOR");

    private String type;

    VehicleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
