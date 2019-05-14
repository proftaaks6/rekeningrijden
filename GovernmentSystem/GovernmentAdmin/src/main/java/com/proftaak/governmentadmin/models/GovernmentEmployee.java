package com.proftaak.governmentadmin.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="tbl_governmentEmployee")
@NamedQueries({
        @NamedQuery(
                name = "GovernmentEmployee.findByUsername",
                query = "SELECT u FROM GovernmentEmployee u WHERE u.username = :username"),
        @NamedQuery(
                name = "GovernmentEmployee.getAll",
                query = "SELECT u FROM GovernmentEmployee u"),
        @NamedQuery(
                name = "GovernmentEmployee.validateUser",
                query = "SELECT u FROM GovernmentEmployee u WHERE u.username = :username AND u.password = :password"
        )
})
public class GovernmentEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String username;

    @Column
    private String password;

    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name="tbl_governmentEmployee_role")
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    public GovernmentEmployee() {

    }

    public GovernmentEmployee(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
