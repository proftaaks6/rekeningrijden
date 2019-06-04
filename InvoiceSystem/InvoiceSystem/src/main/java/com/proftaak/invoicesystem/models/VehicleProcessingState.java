package com.proftaak.invoicesystem.models;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;

@Entity
@Table(name="tbl_vehicleproccessing")
@NamedQueries({
        @NamedQuery(name="VehicleProcessingState.get",
                query = "SELECT vpt FROM VehicleProcessingState vpt WHERE vpt.lastProcessed < :lastProcessed",
                lockMode = PESSIMISTIC_WRITE),
        @NamedQuery(
                name = "VehicleProcessingState.getAll",
                query = "SELECT p FROM VehicleProcessingState p"
        ),
        @NamedQuery(
                name = "VehicleProcessingState.getByChassis",
                query = "SELECT p FROM VehicleProcessingState p where p.vehicleChassis = :chassis"
        )

})
public class VehicleProcessingState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String vehicleChassis;

    @Column
    private Date lastProcessed;

    public VehicleProcessingState() { }

    public VehicleProcessingState(String vehicleChassis) {
        this.vehicleChassis = vehicleChassis;
        this.lastProcessed = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleChassis() {
        return vehicleChassis;
    }

    public void setVehicleChassis(String vehicleChassis) {
        this.vehicleChassis = vehicleChassis;
    }

    public Date getLastProcessed() {
        return lastProcessed;
    }

    public void setLastProcessed(Date lastProcessed) {
        this.lastProcessed = lastProcessed;
    }
}
