package com.proftaak.driverapplication.api.endpoints;

import com.proftaak.driverapplication.security.Secured;
import com.proftaak.driverapplication.service.DriverApplicationService;
import com.proftaak.usersystem.shared.ClientUser;
import java.io.IOException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path(value = "/me")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class MeEndpoint
{
    @Inject
    DriverApplicationService driverApplicationService;

    @GET
    @Secured
    @Path("")
    public Response getMe(@Context SecurityContext context) throws IOException {
        ClientUser user = driverApplicationService.getClientUserByName(context.getUserPrincipal().getName());
        return Response.ok().entity(user).build();
    }

    @GET
    @Secured
    @Path("/vehicles")
    public Response getVehicles(@Context SecurityContext context) throws IOException {
        return Response.ok().entity(driverApplicationService.getUserVehicles(context.getUserPrincipal().getName())).build();
    }

    @GET
    @Secured
    @Path("/invoices")
    public Response getInvoices(@Context SecurityContext context) throws IOException {
        return Response.ok().entity(driverApplicationService.getUserInvoices(context.getUserPrincipal().getName())).build();
    }

    @GET
    @Secured
    @Path("/invoices/{id}")
    public Response getInvoiceInfo(@Context SecurityContext context, @PathParam("id") long id) throws IOException {
        return Response.ok().entity(driverApplicationService.getInvoiceById(id, context.getUserPrincipal().getName())).build();
    }


    @POST
    @Secured
    @Path("/invoices/{id}/pay")
    public Response setPay(@Context SecurityContext context, @PathParam("id") long id) {
        try
        {
            if (driverApplicationService.payInvoice(id, context.getUserPrincipal().getName())) {
                return Response.ok().build();
            } else {
                return Response.serverError().build();
            }
        } catch (IOException e)
        {
            return Response.serverError().build();
        }

    }
}
