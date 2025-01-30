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

public class PostRequestTest {

    private ExtentReports extent;
    private ExtentTest test;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        String methodName = testInfo.getDisplayName();
        System.out.println("Setting up ExtentReports...");
        extent = ExtentReportManager.getInstance();
        test = extent.createTest("Create User Test: " + methodName, "Test to verify POST /users/ endpoint");
        System.out.println("ExtentReports setup complete.");
    }

    @Test
    public void testCreateUser() {
        // Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api";

        // Request payload (JSON body)
        String requestBody = """
                {
                    "name": "John Doe",
                    "job": "Software Engineer"
                }
                """;

        // Send POST request and store the response
        Response response = RestAssured.given()
                .contentType(ContentType.JSON) // Set content type to JSON
                .body(requestBody) // Attach the request body
                .when()
                .post("/users") // POST request to /users endpoint
                .then()
                .extract()
                .response();

        // Print the response for debugging
        System.out.println("Response Body: " + response.asPrettyString());

        // Assert the status code
        assertEquals(201, response.getStatusCode(), "Expected status code 201 (Created)");

        // Assert specific fields in the response body
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");
        assertNotNull(response.jsonPath().getString("id"), "ID should not be null");
        assertNotNull(response.jsonPath().getString("createdAt"), "CreatedAt should not be null");
        assertEquals("John Doe", name, "Name should match the request payload");
        assertEquals("Software Engineer", job, "Job should match the request payload");
    }

    @Test
    public void testCreateUserWithInvalidInput() {
        RestAssured.baseURI = "https://reqres.in/api";

        // Invalid request payload (missing "name" field)
        String requestBody = """
                test
                      """;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .extract()
                .response();

        // Assert the status code for invalid input
        assertEquals(400, response.getStatusCode(), "Expected status code 400 (Bad Request)");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Flushing ExtentReports...");
        extent.flush();
        System.out.println("ExtentReports flushed.");
    }

}