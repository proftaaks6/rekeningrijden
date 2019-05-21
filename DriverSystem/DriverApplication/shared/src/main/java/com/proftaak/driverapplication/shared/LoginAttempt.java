package com.proftaak.driverapplication.shared;

public class LoginAttempt
{
	private long id;
	private long userId;
	private String date;
	private boolean success;

	public LoginAttempt(long id, long userId, String date, boolean success) {
		this.id = id;
		this.userId = userId;
		this.date = date;
		this.success = success;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}
}
