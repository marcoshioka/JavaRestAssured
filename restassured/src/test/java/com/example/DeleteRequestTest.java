package com.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.utils.ExtentReportManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class DeleteRequestTest {
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        String methodName = testInfo.getDisplayName();
        System.out.println("Setting up ExtentReports...");
        extent = ExtentReportManager.getInstance();
        test = extent.createTest("Delete User Test: " + methodName, "Test to verify DELETE /users/2 endpoint");
        System.out.println("ExtentReports setup complete.");
    }

    @Test
    public void testDeleteUser() {
        // Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api";

        // Send DELETE request and store the response
        Response response = RestAssured.given()
                .when()
                .delete("/users/2") // DELETE request to /users/2 endpoint
                .then()
                .extract()
                .response();

        // Print the response for debugging
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Assert the status code
        assertEquals(204, response.getStatusCode(), "Expected status code 204 (No Content)");

        // Assert that the response body is empty
        assertTrue(response.getBody().asString().isEmpty(), "Response body should be empty");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Flushing ExtentReports...");
        extent.flush();
        System.out.println("ExtentReports flushed.");
    }
}