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
    @Id
    private long id;

    @Column(unique = true)
    private String username;

    @Column
    private String passwordHash;

    public DriverUser(){}

    public DriverUser(String username, String password) {
        this.username = username;
        this.passwordHash = password;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
