package com.proftaak.usersystem.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
@NamedQueries( {
        @NamedQuery(
                name = "ClientUser.getByName",
                query="SELECT u FROM ClientUser u WHERE u.name = :name"
        ),
        @NamedQuery(
                name = "ClientUser.getById",
                query="SELECT u FROM ClientUser u WHERE u.id = :id"
        ),
        @NamedQuery(
                name = "ClientUser.getAll",
                query="SELECT u FROM ClientUser u"
        )
})
@Table(name="tbl_clientUser")
public class ClientUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String residence;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserVehicle> ownedVehicles;

    @Column(unique = true)
    private String email;

    public ClientUser() {
        this.ownedVehicles = new ArrayList<>();
    }


    public ClientUser(String name, String address, String residence, List<UserVehicle> ownedVehicles, String email) {
        this.name = name;
        this.address = address;
        this.residence = residence;
        this.ownedVehicles = ownedVehicles;
        this.email = email;
    }

    public ClientUser(long id, String name, String address, String residence, List<UserVehicle> ownedVehicles, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.residence = residence;
        this.ownedVehicles = ownedVehicles;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<UserVehicle> getOwnedVehicles() {
        return ownedVehicles;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getResidence() {
        return residence;
    }

    public List<String> getAllOwnedVehicleChassisNumbers() {
        List<String> list = new ArrayList<>();
        for (UserVehicle v : ownedVehicles) {
            list.add(v.getVehicle().getChassisNumber());
        }

        return list;
    }

    public List<String> getOwnedVehicleChassis()
    {
        List<String> list = new ArrayList<>();
        for (UserVehicle v : ownedVehicles)
        {
            list.add(v.getVehicle().getChassisNumber());
        }

        return list;
    }

    public void addOwnedVehicle(UserVehicle userVehicle)
    {
        this.ownedVehicles.add(userVehicle);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }
}
