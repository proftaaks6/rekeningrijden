package com.proftaak.governmentadmin.api.endpoints;


import com.proftaak.governmentadmin.service.GovernmentAdminService;
import com.proftaak.usersystem.shared.ClientUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(value = "/government")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class GovernmentAdminEndpoint
{

    @Inject
    private GovernmentAdminService governmentAdminService;

    @POST
    @Path("/user")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response addUser(@FormParam("name") String name,
                            @FormParam("address") String address,
                            @FormParam("residence") String residence,
                            @FormParam("email") String email){
        ClientUser user = null;
        try {
            user = governmentAdminService.addUser(name, address, residence, email);
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }

        return Response.status(200).entity(user).build();
    }

    @POST
    @Path("/linkcar")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public Response linkCar(@FormParam("userId") String userId,
                            @FormParam("vehicleChassis") String vehicleChassis){
        int user = Integer.parseInt(userId);

        try {
            return Response.status(200).entity(governmentAdminService.linkCar(user, vehicleChassis)).build();
        } catch (Exception e) {
            return Response.status(400).entity(false).build();
        }
    }

    @GET
    @Path("/users")
    public Response getUsers(){
        List<ClientUser> users;
        try {
            users = governmentAdminService.getUsers();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }

        return Response.status(200).entity(users).build();
    }
}
