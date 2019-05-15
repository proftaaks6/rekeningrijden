package com.proftaak.movementregistrationservice.shared;

import java.util.Date;

public class VehicleTracker
{
	private Vehicle vehicle;

	private Tracker tracker;

	private Date startDate;

	private Date endDate;

	public VehicleTracker(Vehicle vehicle, Tracker tracker, Date startDate)
	{
		this.vehicle = vehicle;
		this.tracker = tracker;
		this.startDate = startDate;
		this.endDate = null;
	}

	public VehicleTracker(Vehicle vehicle, Tracker tracker, Date startDate, Date endDate)
	{
		this.vehicle = vehicle;
		this.tracker = tracker;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Vehicle getVehicle()
	{
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle)
	{
		this.vehicle = vehicle;
	}

	public Tracker getTracker()
	{
		return tracker;
	}

	public void setTracker(Tracker tracker)
	{
		this.tracker = tracker;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}
}
