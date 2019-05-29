package com.proftaak.movementregistrationservice.api.endpoints;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Some resources are more important than others.
 * Take for example a health check; imagine not being
 * able to respond if a doctor asks you if you're
 * alive. That would suck.
 *
 * This same applies to rest services, especially in an
 * containerised environment. So please, leave this file
 * intact. Don't modify it or do something stupid.
 * Because if our automated "doctor" asks if this service is alive.
 * It expects a "True".
 *
 * Best wishes :)
 */

@Path(value = "/health")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class HealthCheck {

    /**
     * Return true if this service is up and running and everything
     * is rainbows and unicorns.
     * Return false if it should be killed.
     * @return
     */
    @GET
    @Path(value = "/check")
    public boolean alive(){
        return true;
    }

    @GET
    @Path(value = "/whoami")
    public String WhoAmI(){
        return "MovementRegistrationService";
    }
}
