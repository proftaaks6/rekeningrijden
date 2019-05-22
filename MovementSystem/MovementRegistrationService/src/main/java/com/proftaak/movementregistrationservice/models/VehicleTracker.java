package com.proftaak.movementregistrationservice.models;


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
import org.hibernate.annotations.ManyToAny;

@Entity
@Table(
		name = "tbl_vehicleTracker",
		indexes = { @Index(name = "IDX_VehicleTracker", columnList = "vehicle_id,tracker_id", unique = true)}
		)
@NamedQueries({
		@NamedQuery(name="VehicleTracker.get",
				query = "SELECT vt FROM VehicleTracker vt JOIN vt.vehicle v JOIN vt.tracker t WHERE v.id = :vehicleId AND t.id = :trackerId")

})
public class VehicleTracker
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	private Vehicle vehicle;
	@ManyToOne
	private Tracker tracker;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	public VehicleTracker() {

	}

	public VehicleTracker(Vehicle vehicle, Tracker tracker, Date startDate, Date endDate)
	{
		this.vehicle = vehicle;
		this.tracker = tracker;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public VehicleTracker(Vehicle vehicle, Tracker tracker, Date startDate)
	{
		this.vehicle = vehicle;
		this.tracker = tracker;
		this.startDate = startDate;
		this.endDate = null;
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

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
}
