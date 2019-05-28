package com.proftaak.invoicesystem.api.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proftaak.invoicesystem.models.Invoice;
import com.proftaak.invoicesystem.services.VehicleProcessingService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(value = "/vehicleprocessing")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class VehicleProcessingEndpoint {

    @Inject
    private VehicleProcessingService service;

    @POST
    @Path("/vehicle")
    public Response addNewVehicle(int vehicleId){
        if (service.addNewVehicle(vehicleId)) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/vehicle")
    public String getAllVehicles() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(service.getAllVehicles());
    }

}
