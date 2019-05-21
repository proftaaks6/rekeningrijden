package com.proftaak.policeadministration.models;

import javax.persistence.*;

@Entity
@Table(name="tbl_policeEmployee")
public class PoliceEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String password;

    public PoliceEmployee() {

    }

    public PoliceEmployee(String name, String password) {
        this.name = name;
        this.password = password;
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
}
