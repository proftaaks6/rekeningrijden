package com.proftaak.driverapplication.api.endpoints;


import com.proftaak.driverapplication.converters.DriverUserConverter;
import com.proftaak.driverapplication.converters.LoginAttemptConverter;
import com.proftaak.driverapplication.models.DriverUser;
import com.proftaak.driverapplication.models.LoginAttempt;
import com.proftaak.driverapplication.service.DriverApplicationService;
import java.util.List;
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
    @Inject
    private LoginAttemptConverter loginAttemptConverter;

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

    @POST
    @Path("/loginattempt")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLoginAttempt(com.proftaak.driverapplication.shared.LoginAttempt loginAttempt) {
        try {
            driverApplicationService.addLoginAttempt(loginAttemptConverter.toEntity(loginAttempt));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
