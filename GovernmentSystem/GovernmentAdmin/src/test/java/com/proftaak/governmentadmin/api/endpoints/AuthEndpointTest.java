package com.proftaak.governmentadmin.api.endpoints;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class AuthEndpointTest {
    private String token;

    @Before
    public void setUp() throws Exception {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = 8080;
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }


        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/GovernmentAdmin/v1/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;

        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.parameter("username", "governmentemployee");
        httpRequest.parameter("password", "governmentemployee");

        Response response = httpRequest.post("auth/login");

        System.out.println("Response Body is =>  " + response.getHeader("AUTHORIZATION"));

        token = response.getHeader("AUTHORIZATION");
    }

    @Test
    public void checkJWT() {
        given().header("Authorization", token).when().get("auth/check").then().statusCode(200);
    }

    @Test
    public void registerUser() {
        given().header("Authorization", "Bearer " + token).when().post("auth/register").then().statusCode(500);
    }

    @Test
    public void alive() {
        given().when().get("health/check").then().statusCode(200);
    }

    @Test
    public void whoAmI() {
        given().when().get("health/whoami").then().statusCode(200);
    }
}