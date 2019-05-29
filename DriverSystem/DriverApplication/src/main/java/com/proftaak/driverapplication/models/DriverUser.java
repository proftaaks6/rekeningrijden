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
        ),
        @NamedQuery(
                name = "DriverUser.validateUser",
                query = "SELECT u FROM DriverUser u WHERE u.username = :username AND u.password = :password"
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
    private String password;

    public DriverUser(){}

    public DriverUser(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
