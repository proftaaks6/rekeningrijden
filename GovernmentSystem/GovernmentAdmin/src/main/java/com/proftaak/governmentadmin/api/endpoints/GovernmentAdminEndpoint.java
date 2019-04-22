package com.proftaak.governmentadmin.api.endpoints;


import com.proftaak.governmentadmin.service.GovernmentAdminService;
import com.proftaak.usersystem.shared.ClientUser;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
    public Response addUser(String name, String email){
        ClientUser user = null;
        try {
            user = governmentAdminService.addUser(name, email);
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }

        return Response.status(200).entity(user).build();
    }
}
