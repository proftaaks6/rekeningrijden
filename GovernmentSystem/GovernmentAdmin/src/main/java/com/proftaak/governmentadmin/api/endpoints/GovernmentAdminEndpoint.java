package com.proftaak.governmentadmin.api.endpoints;


import com.proftaak.governmentadmin.security.Secured;
import com.proftaak.governmentadmin.service.GovernmentAdminService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(value = "/government")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@Secured
public class GovernmentAdminEndpoint
{
    @Inject
    private GovernmentAdminService governmentAdminService;

    @GET
    public Response defaultRoute(){
        return Response.ok().build();
    }


}
