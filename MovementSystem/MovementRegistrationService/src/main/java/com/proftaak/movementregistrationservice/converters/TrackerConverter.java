package com.proftaak.movementregistrationservice.converters;

import com.proftaak.movementregistrationservice.models.Tracker;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class TrackerConverter {

    public Tracker toEntity(com.proftaak.movementregistrationservice.shared.Tracker sharedTracker){
        return new Tracker(sharedTracker.getId(), sharedTracker.isActive());
    }

    public com.proftaak.movementregistrationservice.shared.Tracker toShared(Tracker tracker){
        if (tracker == null) {
            return null;
        }
        return new com.proftaak.movementregistrationservice.shared.Tracker(tracker.getId(), tracker.isActive());
    }

    public List<com.proftaak.movementregistrationservice.shared.Tracker> toShared(List<Tracker> trackers){
    	List<com.proftaak.movementregistrationservice.shared.Tracker> shared = new ArrayList<>();

    	for (Tracker tracker : trackers) {
    		shared.add(toShared(tracker));
		}

		return shared;
	}
}
