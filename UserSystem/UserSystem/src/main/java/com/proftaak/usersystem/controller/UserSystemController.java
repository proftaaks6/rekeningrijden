package com.proftaak.usersystem.controller;

import com.proftaak.usersystem.converters.ClientUserConverter;
import com.proftaak.usersystem.converters.SimpleUserVehicleConverter;
import com.proftaak.usersystem.models.UserVehicle;
import com.proftaak.usersystem.service.UserService;
import com.proftaak.usersystem.service.VehicleService;
import com.proftaak.usersystem.shared.ClientUser;

import com.proftaak.usersystem.shared.SimpleUserVehicle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path(value = "/usersystem")
@Produces(MediaType.APPLICATION_JSON)
//@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
@Stateless
public class UserSystemController {

    @Inject
    private UserService userService;

    @Inject
    private VehicleService vehicleService;

    @Inject
    private ClientUserConverter clientUserConverter;

    @Inject
    private SimpleUserVehicleConverter simpleUserVehicleConverter;

    @POST
    @Path("/userInfo")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public ClientUser addUser(@FormParam("name") String name,
                              @FormParam("address") String address,
                              @FormParam("residence") String residence,
                              @FormParam("email") String email) {
        try
        {
            return new ClientUserConverter().toShared(userService.saveUserInformation(name, email, address, residence));
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @POST
    @Path("/{id}/car/{chassisNumber}")
    public Response addCarToUser(@PathParam("id") int userId, @PathParam("chassisNumber") String chassisNumber) {
        if (vehicleService.addCarToUser(userService.getClientUserById(userId), chassisNumber)) {
            return Response.ok().entity(true).build();
        } else {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/users")
    public List<ClientUser> getUsers()
    {
        try
        {
            return clientUserConverter.toShared(userService.getAllUsers());
        } catch (Exception e)
        {
            throw new BadRequestException();
        }
    }

    @GET
    @Path("/users/{id}")
    public ClientUser getUser(@PathParam("id") int userId)
    {
        try
        {
            return clientUserConverter.toShared(userService.getClientUserById(userId));
        } catch (Exception e)
        {
            throw new BadRequestException();
        }
    }

    @GET
    @Path("/userId/{username}")
    public ClientUser getUserByUsername(@PathParam("username") String username) {
        try {
            return clientUserConverter.toShared(userService.getClientUserByName(username));
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @POST
    @Path("/change")
    public ClientUser editUser(@FormParam("id") String id,
                              @FormParam("name") String name,
                              @FormParam("address") String address,
                              @FormParam("residence") String residence,
                              @FormParam("email") String email) {
        try
        {
            com.proftaak.usersystem.models.ClientUser user = new com.proftaak.usersystem.models.ClientUser(Long.parseLong(id),name,address,residence,new ArrayList<UserVehicle>(),email);
            return new ClientUserConverter().toShared(userService.editUser(user));
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @GET
    @Path("/users/{id}/vehicles")
    public List<String> getVehicleChassisForUser(@PathParam("id") long userId) {
        try {
            com.proftaak.usersystem.models.ClientUser user = userService.getClientUserById(userId);
            return user.getAllOwnedVehicleChassisNumbers();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @GET
    @Path("/users/{id}/vehicles/from/{startValue}/to/{endValue}")
    public List<SimpleUserVehicle> getVehicleChassisForUser(@PathParam("id") long userId, @PathParam("startValue") long startValue, @PathParam("endValue") long endValue) {
        try {
            Date startDate = new Date(startValue);
            Date endDate = new Date(endValue);
            com.proftaak.usersystem.models.ClientUser user = userService.getClientUserById(userId);
            return simpleUserVehicleConverter.fromUser(user, startDate, endDate);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
