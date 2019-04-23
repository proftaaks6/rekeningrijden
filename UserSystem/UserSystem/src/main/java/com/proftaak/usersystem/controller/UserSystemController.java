package com.proftaak.usersystem.controller;

import com.proftaak.usersystem.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;


@Path(value = "/usersystem")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class UserSystemController {

    @Inject
    private UserService userService;

    @POST
    @Path("/userInfo")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addTracker(@FormParam("name") String name,
                               @FormParam("address") String address,
                               @FormParam("residence") String residence,
                               @FormParam("email") String email) {
        if (userService.saveUserInformation(name, address, residence, email)) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }
}
