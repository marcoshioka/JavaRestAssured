package com.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Define the report directory path
            String reportPath = System.getProperty("user.dir") + "/target/extent-reports/index.html";
            System.out.println("Report path: " + reportPath);

            // Create the report directory if it doesn't exist
            File reportDir = new File(System.getProperty("user.dir") + "/target/extent-reports");
            if (!reportDir.exists()) {
                boolean created = reportDir.mkdirs();
                System.out.println("Directory created: " + created);
            } else {
                System.out.println("Directory already exists: " + reportDir.getAbsolutePath());
            }

            // Initialize ExtentReports and attach the SparkReporter
            try {
                ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
                extent = new ExtentReports();
                extent.attachReporter(sparkReporter);
                System.out.println("ExtentReports initialized successfully.");
            } catch (Exception e) {
                System.err.println("Error initializing ExtentReports: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return extent;
    }
}