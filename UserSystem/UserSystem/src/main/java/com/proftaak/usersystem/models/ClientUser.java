package com.proftaak.usersystem.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries( {
        @NamedQuery(
                name = "ClientUser.getByName",
                query="SELECT u FROM ClientUser u WHERE u.name = :name"
        ),
        @NamedQuery(
                name = "ClientUser.getById",
                query="SELECT u FROM ClientUser u WHERE u.id = :id"
        )
})
@Table(name="tbl_clientUser")
public class ClientUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String residence;


    @OneToMany(mappedBy = "owner")
    private List<Vehicle> ownedCars;


    @Column(unique = true)
    private String email;

    public ClientUser() {
    }


    public ClientUser(String name, String address, String residence, List<Vehicle> ownedCars, String email) {
        this.name = name;
        this.address = address;
        this.residence = residence;
        this.ownedCars = ownedCars;
        this.email = email;
    }

    public ClientUser(long id, String name, String address, String residence, List<Vehicle> ownedCars, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.residence = residence;
        this.ownedCars = ownedCars;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Vehicle> getOwnedCars() {
        return ownedCars;
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

    public List<Integer> getOwnedCarIdList() {
        List<Integer> list = new ArrayList<>();
        for (Vehicle v : ownedCars) {
            list.add((int)v.getId());
        }

        return list;
    }
}
