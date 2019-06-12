package com.proftaak.invoicesystem.api.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proftaak.invoicesystem.models.Invoice;
import com.proftaak.invoicesystem.services.InvoiceProcessingService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path(value = "/invoicesystem")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class InvoiceProcessingEndpoint {

    @Inject
    private InvoiceProcessingService service;

    @GET
    @Path("/markAsPaid/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response markAsPaid(@PathParam("id") int invoiceId){
        if (service.markAsPaid(invoiceId)) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/user/{userId}/vehicles/{vehicleIds}")
    public Response getInvoicesForUser(@PathParam("userId") long userId, @PathParam("vehicleIds") String unparsedVehicleIds)  {
        List<Invoice> invoices = service.getInvoicesForUser(userId, unparsedVehicleIds);
        return Response.ok(invoices).build();
    }

    @POST
    @Path("/regenerate/{id}")
    public Response regenerateInvoice(@PathParam("id") long invoiceId) {

        if (service.regenerateInvoice(invoiceId)  != null) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/id/{id}")
    public Response getInvoiceById(@PathParam("id") long id){
        Invoice invoice = service.getInvoiceById(id);
        if(invoice != null){
            return Response.ok(invoice).build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/markForGeneration/{vehicleIds}")
    public Response markForGeneration(@PathParam("vehicleIds") String unparsedVehicleIds) {
        service.markForGeneration(unparsedVehicleIds);
        return Response.ok(true).build();
    }

//    @POST
//    @Path("/generate")
//    public Response generateInvoice(
//            @FormParam("vehicleId") int vehicleId,
//            @FormParam("totalDistance") int
//    )
}
