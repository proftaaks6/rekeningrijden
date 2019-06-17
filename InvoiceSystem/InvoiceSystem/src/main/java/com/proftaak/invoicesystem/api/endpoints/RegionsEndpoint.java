package com.proftaak.invoicesystem.api.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proftaak.invoicesystem.converters.RegionConverter;
import com.proftaak.invoicesystem.models.JsonRegion;
import com.proftaak.invoicesystem.services.RegionService;
import com.proftaak.invoicesystem.shared.Point;
import com.proftaak.invoicesystem.shared.Region;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.ValidationException;

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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postNewRegions(List<JsonRegion> regions){
        regionService.reloadRegionsInMemory();
        try
        {
            regionService.saveNewRegions(regions);
            return getAllRegions();
        } catch (ValidationException e)
        {
            return Response.serverError().build();
        }

    }

    @GET
    public Response getAllRegions(){
        List<JsonRegion> jsonRegions = new ArrayList<>();
        List<Region> regions = regionService.getRegions().stream().map(x->regionConverter.fromSquareEntity(x)).collect(Collectors.toList());
        for(Region region : regions){
            jsonRegions.add(new JsonRegion(region.getId(), region.getTopLeft().getLatitude(), region.getTopLeft().getLongitude(), region.getBottomRight().getLatitude(), region.getBottomRight().getLongitude(), region.getPrice()));
        }
        return Response.ok(jsonRegions).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public boolean deleteRegion(@PathParam("id") String id){
        return regionService.removeRegionById(Integer.parseInt(id));
    }

}
