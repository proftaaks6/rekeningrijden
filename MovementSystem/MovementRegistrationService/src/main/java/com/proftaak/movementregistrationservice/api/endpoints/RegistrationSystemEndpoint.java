package com.proftaak.movementregistrationservice.api.endpoints;


import com.proftaak.movementregistrationservice.converters.LocationPointConverter;
import com.proftaak.movementregistrationservice.converters.TrackerConverter;
import com.proftaak.movementregistrationservice.converters.VehicleConverter;
import com.proftaak.movementregistrationservice.models.*;
import com.proftaak.movementregistrationservice.service.RegistrationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;


@Path(value = "/registration")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class RegistrationSystemEndpoint {

    @Inject
    private RegistrationService registrationService;

    @Inject
    private TrackerConverter trackerConverter;

    @Inject
    private VehicleConverter vehicleConverter;

    @Inject
    private LocationPointConverter pointConverter;

    @POST
    @Path("/tracker")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addTracker(){
        if(registrationService.addTracker(new Tracker())){
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("/tracker/{trackerId}/status")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response editTrackerStatus(boolean active, @PathParam("trackerId") long targetTrackerId){
        if(registrationService.editTrackerActiveStatus(active, targetTrackerId)){
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("/tracker/{trackerId}/points")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response editTrackerLocationPoints(List<com.proftaak.movementregistrationservice.shared.LocationPoint> points, @PathParam("trackerId") long targetTrackerId){
        if(registrationService.editTrackerLocationPoints(points.stream().map(x->pointConverter.toEntity(x)).collect(Collectors.toList()), targetTrackerId)){
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("/tracker/{trackerId}/vehicles")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response editTrackerVehicle(com.proftaak.movementregistrationservice.shared.Vehicle vehicle, @PathParam("trackerId") long targetTrackerId){
        if (registrationService.editTrackerVehicle(vehicleConverter.toEntity(vehicle), targetTrackerId)) {
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }



    @DELETE
    @Path("/tracker/{trackerId}")
    public Response removeTracker(@PathParam("trackerId") long targetTrackerId){
        if(registrationService.removeTracker(targetTrackerId)){
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("/vehicle")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response addVehicle(com.proftaak.movementregistrationservice.shared.Vehicle vehicle){
        if(registrationService.addVehicle(vehicleConverter.toEntity(vehicle))){
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("/vehicle/{vehicleId}/tracker")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response addTrackerToVehicle(com.proftaak.movementregistrationservice.shared.Tracker tracker, @PathParam("vehicleId") long vehicleId){
        if(registrationService.addTrackerToVehicle(trackerConverter.toEntity(tracker), vehicleId)) {
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @DELETE
    @Path("/vehicle/{vehicleId}")
    public Response removeVehicle(@PathParam("vehicleId") long vehicleId){
        if(registrationService.removeVehicle(vehicleId)) {
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @GET
    @Path("/vehicle/{chassis}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public com.proftaak.movementregistrationservice.shared.Vehicle getByChassis(@PathParam("chassis") String chassis){

        Vehicle vehicle = registrationService.getVehicleByChassisNumber(chassis);
        if(vehicle == null){
            throw new NotFoundException();
        }
        return vehicleConverter.toShared(vehicle);
    }

    @GET
    @Path("stolen")
    public List<com.proftaak.movementregistrationservice.shared.Vehicle> getStolen(){
        return registrationService.getStolenVehicles().stream().map(x->vehicleConverter.toShared(x)).collect(Collectors.toList());
    }

    @GET
    @Path("exi")
    public List<com.proftaak.movementregistrationservice.shared.Vehicle> getAll(){
        return registrationService.getAllVehicles().stream().map(x->vehicleConverter.toShared(x)).collect(Collectors.toList());
    }
}
