package com.proftaak.invoicesystem.api.endpoints;

import com.proftaak.invoicesystem.converters.RegionConverter;
import com.proftaak.invoicesystem.services.RegionService;
import com.proftaak.invoicesystem.shared.Region;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path(value = "/region")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class RegionsEndpoint {

    @Inject
    private RegionService regionService;

    @Inject
    private RegionConverter regionConverter;

    @POST
    @Path("")
    public boolean postNewRegion(Region region){
        return regionService.saveSquareRegion(new RegionConverter().toSquareEntity(region));
    }

    @GET
    public Response getAllRegions(){
        List<Region> regions = regionService.getRegions().stream().map(x->regionConverter.fromSquareEntity(x)).collect(Collectors.toList());
        return Response.ok(regions).build();
    }
}
