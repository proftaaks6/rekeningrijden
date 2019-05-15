package com.proftaak.governmentadmin.api.endpoints;

import com.proftaak.governmentadmin.models.GovernmentEmployee;
import com.proftaak.governmentadmin.models.Role;
import com.proftaak.governmentadmin.security.AuthenticationFilter;
import com.proftaak.governmentadmin.security.Secured;
import com.proftaak.governmentadmin.service.GovernmentAdminService;
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
    private static final Logger LOGGER = Logger.getLogger(AuthEndpoint.class.getName());

    @Inject
    private GovernmentAdminService service;

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

    /**
     * Register an user.
     * Consumes form urlencoded so information will be in the request body and not in the browser
     * Roles should be seperated by a ',' character
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/register")
    public String registerUser(@FormParam("username") String username,
                               @FormParam("password") String password,
                               @FormParam("roles") String roles) {
        List<Role> roleList = new ArrayList<>();
        for (String s : roles.split(",")) {
            try {
                roleList.add(Role.valueOf(s));
            } catch (Exception e ) {
                roleList.add(Role.NOTIMPLEMENTEDYET);
                break;
            }
        }

        GovernmentEmployee user = new GovernmentEmployee(username, password, roleList);

        return service.registerUser(user);
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