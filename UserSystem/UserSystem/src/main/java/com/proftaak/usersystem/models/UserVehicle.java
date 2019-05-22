package com.proftaak.usersystem.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_userVehicle",
		indexes = { @Index(name = "IDX_UserVehicle", columnList = "vehicle_id,user_id")}
)
@NamedQueries({
		@NamedQuery(name="UserVehicle.get",
				query = "SELECT ut FROM UserVehicle ut JOIN ut.vehicle v JOIN ut.user u WHERE v.id = :vehicleId AND u.id = :userId")

})
public class UserVehicle
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	private Vehicle vehicle;
	@ManyToOne
	private ClientUser user;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
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
		this(vehicle, user, startDate, new Date());
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

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
}
