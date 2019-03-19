package com.proftaak.governmentadmin.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tbl_governmentEmployee")
public class GovernmentEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String password;

    @OneToMany
    private List<Role> roles;

    public GovernmentEmployee() {

    }

    public GovernmentEmployee(String name, String password, List<Role> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
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

    public List<Role> getRoles() {
        return roles;
    }
}
