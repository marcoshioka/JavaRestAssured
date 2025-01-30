# RestAssured API Testing Project

This project demonstrates how to automate API testing using **Java**, **Rest Assured**, and **JUnit 5**. It includes examples of **GET**, **POST**, **PUT**, and **DELETE** requests, along with **ExtentReports** for generating detailed test reports.

---

## **Table of Contents**
1. [Project Overview](#project-overview)
2. [Technologies Used](#technologies-used)
3. [Setup Instructions](#setup-instructions)
4. [Running Tests](#running-tests)
5. [Generating Reports](#generating-reports)
6. [GitHub Actions Integration](#github-actions-integration)
7. [Contributing](#contributing)
8. [License](#license)

---

## **Project Overview**
This project is designed to automate API testing for RESTful services. It uses:
- **Rest Assured** for making HTTP requests and validating responses.
- **JUnit 5** for writing and running tests.
- **ExtentReports** for generating detailed HTML test reports.

The tests cover common API operations such as:
- **GET**: Retrieve resources.
- **POST**: Create resources.
- **PUT**: Update resources.
- **DELETE**: Delete resources.

---

## **Technologies Used**
- **Java**: 15
- **Rest Assured**: 5.3.2
- **JUnit 5**: 5.10.0
- **ExtentReports**: 5.0.9
- **Maven**: For dependency management and build automation.

---

## **Setup Instructions**
Follow these steps to set up the project on your local machine.

### **Prerequisites**
- **Java Development Kit (JDK)**: Version 15 or higher.
- **Maven**: Version 3.8.1 or higher.
- **IDE**: IntelliJ IDEA, Eclipse, or Visual Studio Code (recommended).

### **Steps**
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/marcoshioka/JavaRestAssured.git
   cd JavaRestAssured/restassured

### **Install Dependencies**
Run the following command to download all dependencies:
```bash
mvn clean install
```

### **Open the Project in Your IDE**
Import the project as a Maven project in your IDE.

---

## **Running Tests**
To run the tests locally, use the following command:
```bash
mvn test
```

---

## **Test Reports**
### **ExtentReports**
After running the tests, the HTML report will be generated in the `target/extent-reports/` directory. Open `index.html` in your browser to view the report.

---

## **Generating Reports**
The project uses ExtentReports to generate detailed test reports. The reports include:
- Test case names.
- Status (Pass/Fail).
- Request and response details.

To view the report:
1. Navigate to the `target/extent-reports/` directory.
2. Open the `index.html` file in your browser.

---

## **GitHub Actions Integration**
This project is integrated with GitHub Actions to automate testing on every push or pull request. The workflow:
- Sets up Java 15.
- Runs the Maven tests.
- Uploads the ExtentReports as a build artifact.

### **Viewing Reports in GitHub Actions**
1. Go to the `Actions` tab in your repository.
2. Select the latest workflow run.
3. Download the `extent-reports` artifact to view the test report.

---

## **Contributing**
Contributions are welcome! If you'd like to contribute, please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes.
4. Submit a pull request.

---

## **License**
This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## **Acknowledgements**
- Rest Assured for simplifying API testing.
- JUnit 5 for providing a robust testing framework.
- ExtentReports for generating detailed test reports.

---

## **Contact**
For questions or feedback, please contact:
- **Email**: marcosribeirohioka@gmail.com
- **GitHub**: marcoshioka

