package com.proftaak.driverapplication.models;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries( {
		@NamedQuery(
				name = "LoginAttempt.getByUserId",
				query="SELECT u FROM LoginAttempt u WHERE userId = :id"
		),
		@NamedQuery(
				name = "LoginAttempt.getById",
				query="SELECT u FROM LoginAttempt u WHERE id = :id"
		),
		@NamedQuery(
				name = "LoginAttempt.getAll",
				query="SELECT u FROM LoginAttempt u"
		)
})
@Table(name="tbl_loginAttempt")
public class LoginAttempt {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column()
	private long userId;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column
	private boolean success;

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
