package com.proftaak.invoicesystem.models;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;

@Entity
@Table(name="tbl_userprocessing")
@NamedQueries({
        @NamedQuery(name="UserProcessingState.get",
                query = "SELECT ups FROM UserProcessingState ups WHERE ups.lastProcessed < :lastProcessed",
                lockMode = PESSIMISTIC_WRITE),
        @NamedQuery(
                name = "UserProcessingState.getAll",
                query = "SELECT p FROM UserProcessingState p"
        ),
        @NamedQuery(
                name = "UserProcessingState.getByUserId",
                query = "SELECT p FROM UserProcessingState p where p.userId = :userId"
        )

})
public class UserProcessingState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private long userId;

    @Column
    private Date lastProcessed;

    public UserProcessingState() { }

    public UserProcessingState(long userId) {
        this.userId = userId;
        this.lastProcessed = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getLastProcessed() {
        return lastProcessed;
    }

    public void setLastProcessed(Date lastProcessed) {
        this.lastProcessed = lastProcessed;
    }
}
