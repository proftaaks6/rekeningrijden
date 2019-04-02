package com.proftaak.movementregistrationservice.converters;

import com.proftaak.movementregistrationservice.models.Tracker;
import javax.ejb.Stateless;


@Stateless
public class TrackerConverter {


    /**
     * TODO implement this method
     * @param sharedTracker
     * @return
     */
    public Tracker toEntity(com.proftaak.movementregistrationservice.shared.Tracker sharedTracker){
        return new Tracker();
    }

    /**
     * TODO implement this method
     * @param tracker
     * @return
     */
    public com.proftaak.movementregistrationservice.shared.Tracker toShared(Tracker tracker){
        return new com.proftaak.movementregistrationservice.shared.Tracker();
    }
}
