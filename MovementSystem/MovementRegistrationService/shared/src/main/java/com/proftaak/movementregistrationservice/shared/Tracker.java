package com.proftaak.movementregistrationservice.shared;

import java.util.ArrayList;
import java.util.List;

public class Tracker {

    private long id;

    private boolean active;

    public Tracker() {
        this.active = true;
    }

    public Tracker(long id, boolean active) {
        this.id = id;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
