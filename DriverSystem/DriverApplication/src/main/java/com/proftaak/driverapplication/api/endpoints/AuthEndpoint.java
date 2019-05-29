package com.proftaak.driverapplication.api.endpoints;

import com.proftaak.driverapplication.security.AuthenticationFilter;
import com.proftaak.driverapplication.security.Secured;
import com.proftaak.driverapplication.service.DriverApplicationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/auth")
public class AuthEndpoint {
    @Inject
    private DriverApplicationService service;

    @GET
    @Secured
    @Path("check")
    public Response checkJWT() {
            return Response.status(200).build();
    }

    @POST
    @Path("login")
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            // Authenticvate the user using credentials, and check if the user is activated or not
            if (service.validateUser(username, password) != null) {
                // Issue a token for the user
                String token = issueToken(username);

                // Return the roken on the response
                return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();

            } else {
                return Response.status(UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(UNAUTHORIZED).build();
        }
    }



    private String issueToken(String username) {
        DefaultClaims claims = new DefaultClaims();
        claims.put("username", username);

        String jws = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setExpiration(new Date(new Date().getTime() + 300000))
                .setClaims(claims)
                .signWith(AuthenticationFilter.serverKey).compact();

        return jws;
    }

}