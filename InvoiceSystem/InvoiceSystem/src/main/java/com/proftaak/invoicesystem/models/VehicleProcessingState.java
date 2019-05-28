package com.proftaak.invoicesystem.models;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;

@Entity
@NamedQueries({
        @NamedQuery(name="VehicleProcessingState.get",
                query = "SELECT vpt FROM VehicleProcessingState vpt WHERE vpt.lastProcessed < :lastProcessed",
                lockMode = PESSIMISTIC_WRITE),
        @NamedQuery(
                name = "VehicleProcessingState.getAll",
                query = "SELECT p FROM VehicleProcessingState p"
        ),
        @NamedQuery(
                name = "VehicleProcessingState.getById",
                query = "SELECT p FROM VehicleProcessingState p where p.vehicleId = :vehicleId"
        )

})
public class VehicleProcessingState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private int vehicleId;

    @Column
    private Date lastProcessed;

    public VehicleProcessingState() { }

    public VehicleProcessingState(int vehicleId) {
        this.vehicleId = vehicleId;
        this.lastProcessed = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getLastProcessed() {
        return lastProcessed;
    }

    public void setLastProcessed(Date lastProcessed) {
        this.lastProcessed = lastProcessed;
    }
}
