package com.proftaak.usersystem.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries( {
        @NamedQuery(
                name = "Vehicle.getByChassis",
                query="SELECT v FROM Vehicle v WHERE v.chassisNumber = :chassis"
        ),
        @NamedQuery(
                name = "Vehicle.getById",
                query="SELECT v FROM Vehicle v WHERE v.id = :id"
        ),
        @NamedQuery(
                name = "Vehicle.getAll",
                query="SELECT v FROM Vehicle v"
        )
})
@Table(name="tbl_car")
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String chassisNumber;

    @OneToMany(mappedBy = "vehicle")
    private List<UserVehicle> owners;

    public Vehicle() {
        this.owners = new ArrayList<>();
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

    public List<UserVehicle> getOwners() {
        return owners;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public void setOwners(List<UserVehicle> owners) {
        this.owners = owners;
    }

    public void addOwner(UserVehicle owner) {
        this.owners.add(owner);
    }
}
