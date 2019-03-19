package com.proftaak.policeadministration.models;

public class StolenCar {
    private long id;
    private long trackerId;
    private String chassisNumber;

    public StolenCar(long trackerId, String chassisNumber) {
        this.trackerId = trackerId;
        this.chassisNumber = chassisNumber;
    }

    public StolenCar() {

    }

    public long getId() {
        return id;
    }

    public long getTrackerId() {
        return trackerId;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }
}
