package com.proftaak.policeadministration.models;

import javax.persistence.*;

@Entity
@Table(name="tbl_stolenCar")
public class StolenCar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private long trackerId;

    @Column
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
