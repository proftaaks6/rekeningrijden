package com.proftaak.driverapplication.api.endpoints;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class DriverApplicationEndpointTestEndpoint {

    private static String token;
    private static String url = "http://localhost:8080/DriverSystem/v1/";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Before
    public void setUp() {
//        String port = System.getProperty("server.port");
//        if (port == null) {
//            RestAssured.port = 8080;
//        }
//        else{
//            RestAssured.port = Integer.valueOf(port);
//        }
//
//
//        String basePath = System.getProperty("server.base");
//        if(basePath==null){
//            basePath = "/DriverSystem/v1/";
//        }
//        RestAssured.basePath = basePath;
//
//        String baseHost = System.getProperty("server.host");
//        if(baseHost==null){
//            baseHost = "http://localhost";
//        }
//        RestAssured.baseURI = baseHost;

        RequestSpecification httpRequest = given();
        httpRequest.parameter("username", "Piet");
        httpRequest.parameter("password", "welkom123");

        Response response = httpRequest.post(url + "auth/login");

        token = response.getHeader("AUTHORIZATION");

    }

    @Test
    public void alive() {
        given().when().get(url + "health/check").then().statusCode(200);
    }

    @Test
    public void getInvoices() {
        given().header(AUTHORIZATION_HEADER, token).when().get(url + "driverapplication/getInvoices").then().statusCode(200);
    }

    @Test
    public void addNewUser() {
        given().formParam("username", "test").formParam("password", "Test123").header(AUTHORIZATION_HEADER, token).when().post(url + "driverapplication/createUser").then().statusCode(200);
    }

    @Test
    public void getUserStatistics() {
        given().pathParam("id", 1).header(AUTHORIZATION_HEADER, token).when().get(url + "driverapplication/{id}").then().statusCode(200);
    }
}