package com.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.utils.ExtentReportManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class GetRequestTest {
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        String methodName = testInfo.getDisplayName();
        System.out.println("Setting up ExtentReports...");
        extent = ExtentReportManager.getInstance();
        test = extent.createTest("Get User By ID Test: " + methodName, "Test to verify GET /users/2 endpoint");
        System.out.println("ExtentReports setup complete.");
    }

    @Test
    public void testGetUserById() {
        // Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api";

        // Send GET request and store the response
        Response response = RestAssured.given()
                .when()
                .get("/users/2") // GET request to /users/2 endpoint
                .then()
                .extract()
                .response();

        // Print the response for debugging
        System.out.println("Response Body: " + response.asPrettyString());

        // Assert the status code
        assertEquals(200, response.getStatusCode(), "Expected status code 200 (OK)");

        // Assert specific fields in the response body
        String email = response.jsonPath().getString("data.email");
        String firstName = response.jsonPath().getString("data.first_name");
        String lastName = response.jsonPath().getString("data.last_name");

        assertEquals("janet.weaver@reqres.in", email, "Email should match");
        assertEquals("Janet", firstName, "First name should match");
        assertEquals("Weaver", lastName, "Last name should match");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Flushing ExtentReports...");
        extent.flush();
        System.out.println("ExtentReports flushed.");
    }
}