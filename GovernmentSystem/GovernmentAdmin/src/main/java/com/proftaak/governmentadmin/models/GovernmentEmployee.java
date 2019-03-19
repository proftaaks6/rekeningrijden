package com.proftaak.governmentadmin.models;

import java.util.List;

public class GovernmentEmployee {
    private long id;
    private String name;
    private String password;
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
