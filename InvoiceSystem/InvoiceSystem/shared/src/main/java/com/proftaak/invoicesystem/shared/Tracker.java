package com.proftaak.invoicesystem.shared;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_tracker")
public class Tracker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private boolean active;

    @OneToMany
    private List<LocationPoint> locationPoints;

    @ManyToMany(mappedBy = "trackers")
    private List<Vehicle> vehicles;

    public Tracker() {

    }

    public Tracker(boolean active) {
        this.active = active;
        this.vehicles = new ArrayList<>();
        this.locationPoints =  new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<LocationPoint> getLocationPoints() {
        return locationPoints;
    }
}
