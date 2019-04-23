package com.proftaak.usersystem.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tbl_clientUser")
public class ClientUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String residence;

    @Column
    private String password;

    @ElementCollection
    private List<Integer> ownedCarIds;

    @Column(unique = true)
    private String email;

    public ClientUser() {
    }

    public ClientUser(String name, String address, String residence, String password, List<Integer> ownedCarIds, String email) {
        this.name = name;
        this.address = address;
        this.residence = residence;
        this.password = password;
        this.ownedCarIds = ownedCarIds;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<Integer> getOwnedCarIds() {
        return ownedCarIds;
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
}
