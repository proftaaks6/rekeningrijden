package com.proftaak.driverapplication.models;

import javax.persistence.*;

@Entity
public class DriverUser {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @Id
    private long userId;

    @Column
    private String passwordHash;

    @Column
    private String passwordSalt;

    public DriverUser(){}
    public DriverUser(long userId){
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public long getId() {
        return id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
}
