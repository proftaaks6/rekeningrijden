package com.proftaak.invoicesystem.services;

import com.google.gson.Gson;
import com.proftaak.movementregistrationservice.shared.LocationPoint;

import javax.ejb.Stateless;
import javax.enterprise.util.TypeLiteral;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.proftaak.invoicesystem.helpers.RestCommuncationHelper.getResponseString;

@Stateless
public class LocationPointService {
    public List<LocationPoint> getLocationPoints(long vehicleId, Date from, Date to){
        try {
            return (List<LocationPoint>) callUrlAndCastResultMethode(
                    (new TypeLiteral<List<LocationPoint>>(){}).getType(),
                    "http://movementregistrationservice/deploy/v1/registration/vehicle/"+vehicleId+"/points/from/"+from.getTime()+"/to/"+to.getTime(), "GET");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();

    }
    private Object callUrlAndCastResultMethode(Type type, String url, String requestMethode) throws IOException {
        String stringResult = getResponseString(url, requestMethode);

        if (stringResult != null) {
            return new Gson().fromJson(stringResult, type);
        } else {
            return null;
        }
    }
}
