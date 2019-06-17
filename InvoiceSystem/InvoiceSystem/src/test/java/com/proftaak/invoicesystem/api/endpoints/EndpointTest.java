package com.proftaak.invoicesystem.api.endpoints;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class EndpointTest {

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
            basePath = "/InvoiceSystem/v1/";
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
    public void alive() {
        given().when().get("health/check").then().statusCode(200);
    }

    @Test
    public void whoAmI() {
        given().when().get("health/whoami").then().statusCode(200);
    }

    @Test
    public void markAsPaid(){
        given().pathParam("id", "1").header("Authorization", "Bearer " + token).when().get("/invoicesystem/markAsPaid/{id}").then().statusCode(500);
    }

    @Test
    public void getInvoicesForUser(){
        given().pathParam("vehicleIds", 0).header("Authorization", "Bearer " + token).when().get("/invoicesystem/vehicle/{vehicleIds}").then().statusCode(200);
    }

    @Test
    public void regenerateInvoice(){
        given().pathParam("id", "0").header("Authorization", "Bearer " + token).when().post("/invoicesystem/regenerate/{id}").then().statusCode(500);
    }

    @Test
    public void getInvoiceById(){
        given().pathParam("id", "0").header("Authorization", "Bearer " + token).when().get("/invoicesystem/id/{id}").then().statusCode(204);
    }

    @Test
    public void getAllRegions(){
        given().header("Authorization", "Bearer " + token).when().get("/region").then().statusCode(200);
    }

    @Test
    public void postNewRegion(){
        given().header("Authorization", "Bearer " + token).header("Content-Type", "APPLICATION_JSON").when().post("/region").then().statusCode(400);
    }

    @Test
    public void addNewVehicle(){
        given().pathParam("chassis", "TESTDATA PLEASE IGNORE").header("Authorization", "Bearer " + token).when().post("/vehicleprocessing/vehicle/{chassis}").then().statusCode(500);
    }

    @Test
    public void getAllVehicles(){
        given().header("Authorization", "Bearer " + token).when().get("/vehicleprocessing/vehicle").then().statusCode(200);
    }
}