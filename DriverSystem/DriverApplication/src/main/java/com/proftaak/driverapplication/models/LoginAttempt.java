package com.proftaak.driverapplication.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(
		name = "LoginAttempt.getByUserId",
		query="SELECT u FROM LoginAttempt u WHERE u.userId = :id"
)
@NamedQuery(
		name = "LoginAttempt.getById",
		query="SELECT u FROM LoginAttempt u WHERE u.id = :id"
)
@NamedQuery(
		name = "LoginAttempt.getAll",
		query="SELECT u FROM LoginAttempt u"
)
@Table(name="tbl_loginAttempt")
public class LoginAttempt {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;

	@Column
	private long userId;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column
	private boolean success;

	public LoginAttempt(Date date, boolean success) {
		this.date = date;
		this.success = success;
	}

	public LoginAttempt() {
	}

	public LoginAttempt(long userId, Date date, boolean success) {
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

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
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
