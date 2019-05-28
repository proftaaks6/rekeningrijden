package com.proftaak.usersystem.shared;

import java.util.Date;

public class UserVehicle
{
	private Vehicle vehicle;

	private ClientUser user;

	private Date startDate;

	private Date endDate;

	public UserVehicle() {

	}

	public UserVehicle(Vehicle vehicle, ClientUser user, Date startDate, Date endDate)
	{
		this.vehicle = vehicle;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public UserVehicle(Vehicle vehicle, ClientUser user, Date startDate)
	{
		this(vehicle, user, startDate, null);
	}

	public Vehicle getVehicle()
	{
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle)
	{
		this.vehicle = vehicle;
	}

	public ClientUser getUser()
	{
		return user;
	}

	public void setUser(ClientUser user)
	{
		this.user = user;
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
