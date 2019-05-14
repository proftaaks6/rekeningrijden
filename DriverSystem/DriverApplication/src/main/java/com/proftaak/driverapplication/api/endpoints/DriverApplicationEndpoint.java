package com.proftaak.driverapplication.api.endpoints;


import com.proftaak.driverapplication.converters.DriverUserConverter;
import com.proftaak.driverapplication.models.DriverUser;
import com.proftaak.driverapplication.service.DriverApplicationService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(value = "/driverapplication")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class DriverApplicationEndpoint
{

    @Inject
    private DriverApplicationService driverApplicationService;
    @Inject
    private DriverUserConverter driverUserConverter;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/{id}")
    public Response addNewUser(@PathParam("id") long id) {
        try {
            DriverUser user = driverApplicationService.saveNewUser(id);
            return Response.ok().entity(driverUserConverter.toShared(user)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
