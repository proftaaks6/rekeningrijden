package com.proftaak.movementregistrationservice.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_tracker")
@NamedQueries({
        @NamedQuery(name="Tracker.getById",
                query = "SELECT t FROM Tracker t WHERE t.id = :id"),
        @NamedQuery(name="Tracker.getAll",
                query = "SELECT t FROM Tracker t"),

})
public class Tracker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private boolean active;

    @OneToMany
    private List<LocationPoint> locationPoints;

    @ManyToMany
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setLocationPoints(List<LocationPoint> locationPoints) {
        this.locationPoints = locationPoints;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
