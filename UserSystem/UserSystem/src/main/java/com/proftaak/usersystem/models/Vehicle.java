package com.proftaak.usersystem.models;

import javax.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cascade;

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

    @ManyToOne
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
