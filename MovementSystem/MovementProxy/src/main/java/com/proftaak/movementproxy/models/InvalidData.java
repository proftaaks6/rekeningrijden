package com.proftaak.movementproxy.models;

import javax.inject.Named;
import javax.persistence.*;

@Entity
@Table(name="tbl_invalidData")
@NamedQueries({
        @NamedQuery(name="InvalidData.getInvalidData",
                query="SELECT d FROM InvalidData d WHERE d.id = :id"),
        @NamedQuery(name="InvalidData.getAll",
                query="SELECT d FROM InvalidData d")
})
public class InvalidData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String data;

    public InvalidData() {
    }

    public InvalidData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }
}
