package com.proftaak.usersystem.shared;

import java.io.Serializable;

public class Vehicle implements Serializable {

    private long id;

    private String chassisNumber;

    private ClientUser owner;

    public Vehicle() {

    }

    public Vehicle(String chassisNumber, ClientUser owner) {
        this.chassisNumber = chassisNumber;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public ClientUser getOwner() {
        return owner;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public void setOwner(ClientUser owner) {
        this.owner = owner;
    }
}
