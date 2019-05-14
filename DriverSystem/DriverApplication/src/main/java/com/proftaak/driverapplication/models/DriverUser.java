package com.proftaak.driverapplication.models;

import javax.persistence.*;

@Entity
@NamedQueries( {
        @NamedQuery(
                name = "DriverUser.getById",
                query="SELECT u FROM DriverUser u WHERE u.id = :id"
        ),
        @NamedQuery(
                name = "DriverUser.getAll",
                query="SELECT u FROM DriverUser u"
        )
})
@Table(name="tbl_driverUser")
public class DriverUser {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @Id
    private long userId;

    @Column
    private String passwordHash;

    public DriverUser(){}
    public DriverUser(long userId){
        this.userId = userId;
    }
    public DriverUser(long userId, String password){
        this.userId = userId;
        this.passwordHash = password;
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
}
