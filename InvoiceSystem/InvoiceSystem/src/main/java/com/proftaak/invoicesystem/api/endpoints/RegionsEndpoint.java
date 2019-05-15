package com.proftaak.invoicesystem.api.endpoints;

import com.proftaak.invoicesystem.converters.RegionConverter;
import com.proftaak.invoicesystem.services.RegionService;
import com.proftaak.invoicesystem.shared.Region;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value = "/region")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class RegionsEndpoint {

    @Inject
    private RegionService regionService;

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean postNewRegion(Region region){
        return regionService.saveSquareRegion(new RegionConverter().toSquareEntity(region));
    }
}
