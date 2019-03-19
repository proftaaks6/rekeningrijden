package com.proftaak.policeadministration.models;

public class PoliceEmployee {
    private long id;
    private String name;
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
