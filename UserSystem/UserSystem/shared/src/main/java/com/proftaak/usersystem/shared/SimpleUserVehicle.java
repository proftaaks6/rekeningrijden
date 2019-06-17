package com.proftaak.usersystem.shared;

import java.util.Date;

public class SimpleUserVehicle
{
	private String chassisNumber;

	private Date startDate;

	private Date endDate;final

	public String getChassisNumber()
	{
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber)
	{
		this.chassisNumber = chassisNumber;
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
