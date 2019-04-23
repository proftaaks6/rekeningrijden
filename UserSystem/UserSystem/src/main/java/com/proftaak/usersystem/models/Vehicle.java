package com.proftaak.usersystem.models;

import javax.persistence.*;

@Entity
@Table(name="tbl_car")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String chassisNumber;

    @ManyToOne
    private ClientUser owner;
}
