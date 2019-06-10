package com.proftaak.usersystem.controller;

import com.proftaak.usersystem.converters.ClientUserConverter;
import com.proftaak.usersystem.models.UserVehicle;
import com.proftaak.usersystem.service.UserService;
import com.proftaak.usersystem.service.VehicleService;
import com.proftaak.usersystem.shared.ClientUser;

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

}
