package com.proftaak.driverapplication.api.endpoints;

import com.proftaak.driverapplication.converters.DriverUserConverter;
import com.proftaak.driverapplication.converters.LoginAttemptConverter;
import com.proftaak.driverapplication.models.DriverUser;
import com.proftaak.driverapplication.models.LoginAttempt;
import com.proftaak.driverapplication.security.Secured;
import com.proftaak.driverapplication.service.DriverApplicationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path(value = "/me")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class MeEndpoint
{
    @GET
    @Secured
    @Path("")
    public Response getMe() throws IOException {
        return Response.ok().build();
    }

    @GET
    @Secured
    @Path("/vehicles")
    public Response getVehicles() throws IOException {
        return Response.ok().build();
    }

    @GET
    @Secured
    @Path("/invoices")
    public Response getInvoices() throws IOException {
        return Response.ok().build();
    }

    @GET
    @Secured
    @Path("/invoices/{id}")
    public Response getInvoiceInfo(@PathParam("id") long id) throws IOException {
        return Response.ok().build();
    }


    @POST
    @Secured
    @Path("/invoices/{id}/pay")
    public Response setPay(@PathParam("id") long id) {
        return Response.ok().build();
    }
}
