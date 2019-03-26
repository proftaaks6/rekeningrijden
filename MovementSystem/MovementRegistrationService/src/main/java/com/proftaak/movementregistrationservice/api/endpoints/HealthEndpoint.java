package com.proftaak.movementregistrationservice.api.endpoints;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path(value = "/health")
@Produces(TEXT_PLAIN)
@Stateless
public class HealthEndpoint {

    @Path("check")
    @GET
    public boolean isHealthy(){
        return true;
    }
}
