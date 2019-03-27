package com.proftaak.movementregistrationservice.api.endpoints;

import com.proftaak.invoicesystem.shared.LocationPoint;
import com.proftaak.invoicesystem.shared.Tracker;
import com.proftaak.invoicesystem.shared.Vehicle;
import com.proftaak.movementregistrationservice.service.RegistrationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path(value = "/registration")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class RegistrationSystemEndpoint {

    @Inject
    private RegistrationService registrationService;
    

    @POST
    @Path("addTracker")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response addTracker(Tracker tracker){
        if(registrationService.addTracker(tracker)){
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("editTrackerStatus")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response editTrackerStatus(boolean active, long targetTrackerId){
        if(registrationService.editTrackerActiveStatus(active, targetTrackerId)){
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("editTrackerLocationPoints")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response editTrackerLocationPoints(List<LocationPoint> points, long targetTrackerId){
        if(registrationService.editTrackerLocationPoints(points, targetTrackerId)){
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("editTrackerVehicles")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response editTrackerVehicles(List<Vehicle> vehicles, long targetTrackerId){
        if(registrationService.editTrackerVehicles(vehicles, targetTrackerId)){
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @DELETE
    @Path("removeTracker")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response removeTracker(long targetTrackerId){
        if(registrationService.removeTracker(targetTrackerId)){
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("addVehicle")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response addVehicle(Vehicle vehicle){
        if(registrationService.addVehicle(vehicle)){
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("addTrackerToVehicle")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response addTrackerToVehicle(Tracker tracker, long vehicleId){
        if(registrationService.addTrackerToVehicle(tracker, vehicleId)) {
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @DELETE
    @Path("removeVehicle")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response removeVehicle(long vehicleId){
        if(registrationService.removeVehicle(vehicleId)) {
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @GET
    @Path("getByChassis")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response getByChassis(String chassis){

        Vehicle vehicle = registrationService.getVehicleByChassisNumber(chassis);

        if(vehicle != null){
            return Response.status(200).entity(vehicle).build();
        } else {
            return Response.status(400).build();
        }
    }

    @GET
    @Path("getStolen")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response getStolen(){
        List<Vehicle> vehicles = registrationService.getStolenVehicles();

        if(vehicles != null){
            return Response.status(200).entity(vehicles).build();
        } else {
            return Response.status(400).build();
        }
    }

    @GET
    @Path("getAll")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response getAll(){
        List<Vehicle> vehicles = registrationService.getAllVehicles();

        if(vehicles != null){
            return Response.status(200).entity(vehicles).build();
        } else {
            return Response.status(400).entity(vehicles).build();
        }
    }
}
