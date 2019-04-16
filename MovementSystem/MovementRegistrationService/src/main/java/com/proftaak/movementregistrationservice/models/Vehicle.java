package com.proftaak.movementregistrationservice.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_vehicle")
@NamedQueries({
        @NamedQuery(name="Vehicle.getById",
                query = "SELECT v FROM Vehicle v WHERE v.id = :id"),
        @NamedQuery(name = "Vehicle.getByChassisNumber", query = "SELECT v FROM Vehicle v where v.chassisNumber = :chassisNumber"),
        @NamedQuery(name = "Vehicle.getStolenVehicles", query = "SELECT v FROM Vehicle v WHERE v.isStolen = true "),
        @NamedQuery(name = "Vehicle.getAll", query = "SELECT v FROM Vehicle v")

})
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Column
    private String chassisNumber;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column
    private double emission;

    @ManyToMany(mappedBy = "vehicles")
    private List<Tracker> trackers;

    @Column
    private boolean isStolen;

    public Vehicle() {

    }

    /**
     * Null isStole nvalue means it has been converted using the VehicleConverter!
     * Author: Rick Jeurissen
     */
    public Vehicle(long id, VehicleType vehicleType, String chassisNumber, FuelType fuelType, double emission, boolean isStolen) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.chassisNumber = chassisNumber;
        this.fuelType = fuelType;
        this.emission = emission;
        this.isStolen = isStolen;

        trackers = new ArrayList<>();
    }

    public Vehicle(long id, VehicleType vehicleType, String chassisNumber, FuelType fuelType, double emission) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.chassisNumber = chassisNumber;
        this.fuelType = fuelType;
        this.emission = emission;

        trackers = new ArrayList<>();
    }

    public Vehicle(VehicleType vehicleType, String chassisNumber, FuelType fuelType, double emission, boolean isStolen) {
        this.vehicleType = vehicleType;
        this.chassisNumber = chassisNumber;
        this.fuelType = fuelType;
        this.emission = emission;
        this.isStolen = isStolen;

        trackers = new ArrayList<>();
    }

    public void addTracker(Tracker tracker){
        trackers.add(tracker);
    }

    public long getId() {
        return id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public double getEmission() {
        return emission;
    }

    public List<Tracker> getTrackers() {
        return trackers;
    }

    public boolean isStolen() {
        return isStolen;
    }

    public void setStolen(boolean stolen) {
        isStolen = stolen;
    }
}
