package com.proftaak.invoicesystem.services;

import com.google.gson.Gson;
import com.proftaak.movementregistrationservice.shared.LocationPoint;

import javax.ejb.Stateless;
import javax.enterprise.util.TypeLiteral;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.proftaak.invoicesystem.helpers.RestCommuncationHelper.getResponseString;

@Stateless
public class LocationPointService {
    public List<LocationPoint> getLocationPoints(String vehicleChassis, Date from, Date to){
        try {
            String url = "http://localhost:8080/MovementRegistration/v1/registration/vehicle/";

            if(System.getenv("environment") != null && System.getenv("environment").equals("production")) {
                url = "http://movementregistrationservice:8080/deploy/v1/registration/vehicle/";
            }

            url += vehicleChassis+"/points/from/"+from.getTime()+"/to/"+to.getTime();

            System.out.println(url);

            return (List<LocationPoint>) callUrlAndCastResultMethode(
                    (new TypeLiteral<List<LocationPoint>>(){}).getType(),
                    url, "GET");
        } catch (IOException e) {

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
