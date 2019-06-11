package com.proftaak.usersystem.shared;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

public class Vehicle implements Serializable {

    private long id;

    private String chassisNumber;

    @Transient
    private List<UserVehicle> owners;

    public Vehicle() {

    }

    public Vehicle(String chassisNumber, List<UserVehicle> owners) {
        this.chassisNumber = chassisNumber;
        this.owners = owners;
    }

    public long getId() {
        return id;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public List<UserVehicle>  getOwner() {
        return owners;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public void setOwner(List<UserVehicle>  owners) {
        this.owners = owners;
    }
}
