package com.proftaak.movementregistrationservice.api.endpoints;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.proftaak.movementregistrationservice.converters.LocationPointConverter;
import com.proftaak.movementregistrationservice.converters.TrackerConverter;
import com.proftaak.movementregistrationservice.converters.VehicleConverter;
import com.proftaak.movementregistrationservice.models.*;
import com.proftaak.movementregistrationservice.service.RegistrationService;

import java.io.IOException;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;
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

    @GET
    @Path("/tracker/{trackerId}/points")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response getTrackerLocationPoints(@PathParam("trackerId") long trackerId){
        return Response.ok().entity(pointConverter.toShared(registrationService.getLocationPointsForTracker(trackerId))).build();
    }

    @GET
    @Path("/vehicle/{vehicleId}/points/from/{startValue}/to/{endValue}")
    public Response getVehicleLocationPoints(@PathParam("vehicleId") long vehicleId, @PathParam("startValue") long startValue, @PathParam("endValue") long endValue){
        Date startDate = new Date(startValue);
        Date endDate = new Date(endValue);
        return Response.ok().entity(pointConverter.toShared(registrationService.getLocationPointsForVehicle(vehicleId, startDate, endDate))).build();
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
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addVehicle(String message){
        ObjectMapper mapper = new ObjectMapper();

        Vehicle vehicle = null;
        try {
            vehicle = mapper.readValue(message, Vehicle.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(registrationService.addVehicle(vehicle)){
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("/vehicle/{vehicleId}/tracker/{trackerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackerToVehicle(@PathParam("trackerId") long trackerId, @PathParam("vehicleId") long vehicleId){
        if(registrationService.addTrackerToVehicle(trackerId, vehicleId)) {
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
    @Path("vehicles")
    public List<com.proftaak.movementregistrationservice.shared.Vehicle> getAll(){
        List<Vehicle> vehicles = registrationService.getAllVehicles();

        return vehicleConverter.toShared(vehicles);
    }

    @GET
    @Path("trackers")
    public List<com.proftaak.movementregistrationservice.shared.Tracker> getAllTrackers(){
        return trackerConverter.toShared(registrationService.getAllTrackers());
    }
}
