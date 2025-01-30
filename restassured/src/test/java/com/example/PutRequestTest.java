package com.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.utils.ExtentReportManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class PutRequestTest {
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        String methodName = testInfo.getDisplayName();
        System.out.println("Setting up ExtentReports...");
        extent = ExtentReportManager.getInstance();
        test = extent.createTest("Update User Test: " + methodName, "Test to verify PUT /users/2 endpoint");
        System.out.println("ExtentReports setup complete.");
    }

    @Test
    public void testUpdateUser() {
        // Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api";

        // Request payload (JSON body)
        String requestBody = """
                {
                    "name": "John Doe",
                    "job": "Senior Software Engineer"
                }
                """;

        // Send PUT request and store the response
        Response response = RestAssured.given()
                .contentType(ContentType.JSON) // Set content type to JSON
                .body(requestBody) // Attach the request body
                .when()
                .put("/users/2") // PUT request to /users/2 endpoint
                .then()
                .extract()
                .response();

        // Print the response for debugging
        System.out.println("Response Body: " + response.asPrettyString());

        // Assert the status code
        assertEquals(200, response.getStatusCode(), "Expected status code 200 (OK)");

        // Assert specific fields in the response body
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");
        assertNotNull(response.jsonPath().getString("updatedAt"), "UpdatedAt should not be null");
        assertEquals("John Doe", name, "Name should match the request payload");
        assertEquals("Senior Software Engineer", job, "Job should match the request payload");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Flushing ExtentReports...");
        extent.flush();
        System.out.println("ExtentReports flushed.");
    }
}