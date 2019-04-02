package com.proftaak.movementregistrationservice.api.endpoints;

import com.proftaak.movementregistrationservice.models.Tracker;
import com.proftaak.movementregistrationservice.service.RegistrationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ThreadLocalRandom;

@Path(value = "/test")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class ConcurrencyTest {

    private int random;

    public ConcurrencyTest(){
        random = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
    }

    @Inject
    RegistrationService service;

    @GET
    @Path("/create")
    public boolean create(){
        Tracker tracker = new Tracker(true);
        return service.addTracker(tracker);
    }

    @GET
    @Path("/amount")
    public String amount(){
        return service.getAllTrackers().size() + " : " +random ;
    }
}
