package com.proftaak.driverapplication.api.endpoints;


import com.proftaak.driverapplication.converters.DriverUserConverter;
import com.proftaak.driverapplication.converters.LoginAttemptConverter;
import com.proftaak.driverapplication.models.DriverUser;
import com.proftaak.driverapplication.models.LoginAttempt;
import com.proftaak.driverapplication.service.DriverApplicationService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
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
    @Inject
    private LoginAttemptConverter loginAttemptConverter;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/createUser")
    public Response addNewUser(@FormParam("username") String username, @FormParam("password") String password) {
        try {
            DriverUser user = driverApplicationService.saveNewUser(username, password);
            return Response.ok().entity(driverUserConverter.toShared(user)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getUserStatistics(@PathParam("id") long id) {
        try {
            List<LoginAttempt> loginAttempts = driverApplicationService.getUserStatistics(id);
            return Response.ok().entity(loginAttemptConverter.toShared(loginAttempts)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
