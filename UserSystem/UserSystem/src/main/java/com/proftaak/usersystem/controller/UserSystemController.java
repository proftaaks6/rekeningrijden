package com.proftaak.usersystem.controller;

import com.proftaak.usersystem.converters.ClientUserConverter;
import com.proftaak.usersystem.models.UserVehicle;
import com.proftaak.usersystem.models.Vehicle;
import com.proftaak.usersystem.service.UserService;
import com.proftaak.usersystem.service.VehicleService;

import com.proftaak.usersystem.shared.ClientUser;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.registry.infomodel.User;

@Path(value = "/usersystem")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
@Stateless
public class UserSystemController {

    @Inject
    private UserService userService;

    @Inject
    private VehicleService vehicleService;

    @Inject
    private ClientUserConverter clientUserConverter;

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
            return new ClientUserConverter().toShared(userService.getClientUserById(userId));
        } catch (Exception e)
        {
            throw new BadRequestException();
        }
    }

    @POST
    @Path("/change")
    public ClientUser editUser(@FormParam("name") String name,
                              @FormParam("address") String address,
                              @FormParam("residence") String residence,
                              @FormParam("email") String email) {
        try
        {
            com.proftaak.usersystem.models.ClientUser user = new com.proftaak.usersystem.models.ClientUser(name,address,residence,new ArrayList<UserVehicle>(),email);
            return new ClientUserConverter().toShared(userService.editUser(user));
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

}
