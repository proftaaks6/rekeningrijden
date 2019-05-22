package com.proftaak.invoicesystem.api.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proftaak.invoicesystem.services.InvoiceProcessingService;
import com.proftaak.invoicesystem.services.VehicleProcessingService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(value = "/invoicesystem")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class InvoiceProcessingEndpoint {

    @Inject
    private InvoiceProcessingService service;

    @POST
    @Path("/markAsPaid")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response markAsPaid(int invoiceId){
        if (service.markAsPaid(invoiceId)) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/vehicle/{vehicleIds}")
    public String getInvoicesForUser(@PathParam("vehicleIds") String unparsedVehicleIds) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(service.getInvoicesForUser(unparsedVehicleIds));
    }

    @POST
    @Path("/regenerate/{id}")
    public Response regenerateInvoice(@PathParam("id") int invoiceId) {
        if (service.regenerateInvoice(invoiceId)) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }

//    @POST
//    @Path("/generate")
//    public Response generateInvoice(
//            @FormParam("vehicleId") int vehicleId,
//            @FormParam("totalDistance") int
//    )
}
