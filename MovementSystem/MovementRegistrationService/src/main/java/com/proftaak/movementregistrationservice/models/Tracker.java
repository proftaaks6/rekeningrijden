package com.proftaak.movementregistrationservice.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tbl_tracker")
@NamedQueries({
        @NamedQuery(name="Tracker.getById",
                query = "SELECT t FROM Tracker t WHERE t.id = :id"),
        @NamedQuery(name="Tracker.getAll",
                query = "SELECT t FROM Tracker t"),
        @NamedQuery(name="Tracker.getLocationPointsForTracker",
                query = "SELECT t.locationPoints FROM Tracker t WHERE t.id = :id"),

})
public class Tracker {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tracker")
    private List<LocationPoint> locationPoints;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tracker")
	private List<VehicleTracker> vehicleTrackers;

    public Tracker() {
        this.locationPoints = new ArrayList<>();
        this.vehicleTrackers = new ArrayList<>();
    }

    public Tracker(boolean active) {
        this.active = active;
        this.locationPoints = new ArrayList<>();
        this.vehicleTrackers = new ArrayList<>();
    }

    public Tracker(long id, boolean active) {
        this.id = id;
        this.active = active;
        this.locationPoints = new ArrayList<>();
        this.vehicleTrackers = new ArrayList<>();
    }

    public LocationPoint getMostRecentLocationPoint() {
        long mostRecentDate = 0;
        LocationPoint mostRecentLocationPoint = null;

        if (locationPoints != null) {
            for (LocationPoint p : locationPoints) {
                if (p.getDate().getTime() > mostRecentDate) {
                    mostRecentDate = p.getDate().getTime();

                    mostRecentLocationPoint = p;
                }
            }
        }


        return mostRecentLocationPoint;
    }

    public long getId() {
        return id;
    }

    public boolean isActive() {
        return active;
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


    public void addLocationPoints(List<LocationPoint> locationPoints){
        for(int i = 0; i < locationPoints.size(); i++){
            this.locationPoints.add(locationPoints.get(i));
        }
    }

    public void addLocationPoint(LocationPoint point) {
        this.locationPoints.add(point);
        point.setTracker(this);
    }

	public List<VehicleTracker> getVehicleTrackers()
	{
		return vehicleTrackers;
	}

	public void setVehicleTrackers(List<VehicleTracker> vehicleTrackers)
	{
		this.vehicleTrackers = vehicleTrackers;
	}

	public void addVehicleTracker(VehicleTracker vehicleTracker) {
    	this.disableActiveTracker();
    	this.vehicleTrackers.add(vehicleTracker);
	}

	public void disableActiveTracker() {
    	for (int i = 0 ; i < vehicleTrackers.size(); i++) {
    		VehicleTracker vehicleTracker = vehicleTrackers.get(i);
    		if (vehicleTracker.getEndDate() == null) {
    			vehicleTracker.setEndDate(new Date());
    			vehicleTrackers.set(i, vehicleTracker);
    			break;
			}
		}
	}
}

