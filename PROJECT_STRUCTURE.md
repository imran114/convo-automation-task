# **Framework Structure**
The project follows a structured (POM) approach to ensure clean code, better maintenance, and scalability.


##  Project Tree

```
ª   .gitignore
ª   extentReport.css
ª   LICENSE
ª   pom.xml
ª   project_structure.txt
ª   README.md
ª   reportConfigJs.js
ª   testng.xml
ª
+---src
ª   +---main
ª   ª   +---java
ª   ª   ª   +---pages 
ª   ª   ª   ª   +---dynamic_table_handling
ª   ª   ª   ª   ª       DynamicTableHandlingPage.java
ª   ª   ª   ª   ª       TableType.java
ª   ª   ª   ª   ª       
ª   ª   ª   ª   +---file_upload
ª   ª   ª   ª   ª       FileUploadPage.java
ª   ª   ª   ª   ª       
ª   ª   ª   ª   +---javascript_alerts
ª   ª   ª   ª   ª       AlertType.java
ª   ª   ª   ª   ª       JavaScriptAlerts.java
ª   ª   ª   ª   ª       
ª   ª   ª   ª   +---login
ª   ª   ª   ª           LoginLocators.java
ª   ª   ª   ª           LoginPage.java
ª   ª   ª   ª           
ª   ª   ª   +---utils
ª   ª   ª       +---common_components
ª   ª   ª       ª   +---button_actions
ª   ª   ª       ª   ª       ButtonActions.java
ª   ª   ª       ª   ª       
ª   ª   ª       ª   +---edit_text_fields
ª   ª   ª       ª   ª       EditText.java
ª   ª   ª       ª   ª       
ª   ª   ª       ª   +---notifications
ª   ª   ª       ª   ª       Notifications.java
ª   ª   ª       ª   ª       
ª   ª   ª       ª   +---scroll_methods
ª   ª   ª       ª   ª       ScrollingMethods.java
ª   ª   ª       ª   ª       
ª   ª   ª       ª   +---validation_messages
ª   ª   ª       ª           Validations.java
ª   ª   ª       ª           
ª   ª   ª       +---file_writer
ª   ª   ª       ª       PropertiesFileWriter.java
ª   ª   ª       ª       
ª   ª   ª       +---random
ª   ª   ª       ª       RandomUtils.java
ª   ª   ª       ª       
ª   ª   ª       +---selenium_utils
ª   ª   ª               SeleniumUtils.java
ª   ª   ª               
ª   ª   +---resources
ª   ª       +---screenshots
ª   ª               note
ª   ª               
ª   +---test
ª       +---java
ª       ª   +---tests
ª       ª   ª   +---dynamic_table_handling
ª       ª   ª   ª       DynamicTableHandlingTest.java
ª       ª   ª   ª       
ª       ª   ª   +---file_upload_test
ª       ª   ª   ª       FileUploadTest.java
ª       ª   ª   ª       
ª       ª   ª   +---javascript_alerts
ª       ª   ª   ª       JavaScriptAlertsTest.java
ª       ª   ª   ª       
ª       ª   ª   +---login
ª       ª   ª           LoginTest.java
ª       ª   ª           
ª       ª   +---test_base
ª       ª   ª       BaseClass.java
ª       ª   ª       DriverManager.java
ª       ª   ª       PageObjects.java
ª       ª   ª       
ª       ª   +---utils
ª       ª       +---email
ª       ª       ª       EmailBody.java
ª       ª       ª       EmailSender.java
ª       ª       ª       
ª       ª       +---fileWriter
ª       ª       ª       PropertiesFileWriter.java
ª       ª       ª       
ª       ª       +---file_reader
ª       ª       ª       PropertiesFileReader.java
ª       ª       ª       
ª       ª       +---grid_configurations
ª       ª       ª       GridConfig.java
ª       ª       ª       selenium-server.jar
ª       ª       ª       
ª       ª       +---listener
ª       ª       ª       SuiteListener.java
ª       ª       ª       TestListener.java
ª       ª       ª       
ª       ª       +---reporter
ª       ª       ª       ExtentReport.java
ª       ª       ª       
ª       ª       +---test_merge
ª       ª       ª       ClearFolder.java
ª       ª       ª       ClearFolderAndMergeReport.java
ª       ª       ª       MergeJsonReports.java
ª       ª       ª       
ª       ª       +---test_validator
ª       ª               TestValidator.java
ª       ª               
ª       +---resources
ª           +---reports
ª           ª   ª   convo_task-250311-report.html
ª           ª   ª   merged_report.json
ª           ª   ª   
ª           ª   +---json_files
ª           +---test_data_files
ª               ª   email_body.README
ª               ª   email_configuration.properties
ª               ª   
ª               +---upload_files
ª                       test_file_to_upload.png
ª                       
```



## **1. pom.xml**
The Maven Project Object Model (POM) file defines:
- Dependencies
- Build plugins and configurations for test execution.
- WebDriver binaries management.

## **2. testng.xml**
Configures TestNG to:
- Define test suites and test cases.
- Manage execution order of tests.

## **3. src/main/java/pages/** *(Page Object Model)*
This directory contains POM-based page classes, each representing a webpage with its elements and interactions.

### **Page Classes:**
- `LoginPage.java`: Manages login page elements and actions (e.g., entering credentials, clicking login button).
- `DynamicTableHandlingPage.java`: Handles dynamic table data extraction and validation.
- `JavaScriptAlerts.java`: Manages JavaScript pop-ups handling.
- `FileUploadPage.java`: Automates file upload functionality.

## **4. src/main/java/utils/** *(Reusable Utilities)*
Contains utility classes for common Selenium actions:
- `SeleniumUtils.java`: General helper methods for web element interactions, waits, sendKeys, button clicks, etc.
- `RandomUtils.java`: Generates random test data (emails, phone numbers, strings).
- `ButtonActions.java`: Encapsulates common button actions (clicking).
- `ScrollingMethods.java`: Handles scrolling actions (scroll up/down/right/left).
- `Validations.java`: Provides validation functions for UI testing.

## **5. src/test/java/tests/** *(Test Classes)*
Contains the test cases for different modules, calling methods from the Page classes.

### **Test Classes:**
- `LoginTest.java`: Automates login functionality (valid/invalid credentials).
- `DynamicTableHandlingTest.java`: Extracts and validates table data dynamically.
- `JavaScriptAlertsTest.java`: Automates handling of different JavaScript alerts.
- `FileUploadTest.java`: Handles file upload verification.

## 6.  *Test Execution Management `src/test/java/test_base`*
- `BaseClass.java`: Manages WebDriver setup, and teardown.
- `PageObjects.java`: Initializes Page Objects for test classes.

## **7. src/test/java/utils/** *(Additional Utilities)*
Contains utilities for:
- Email reporting (`EmailSender.java`)
- Test report generation (`ExtentReport.java`)
- Selenium Grid configurations for parallel execution of tests (`GridConfig.java`)
- Listeners to track, log, and modify test execution behavior (`TestListener.java`, `SuiteListener.java`)

## **8. src/main/resources/**
Stores:
1. Screenshots of failed tests
2. Test data files
3. Email configuration file (contains email sender credentials)

---

#  Automated Tasks

## **1️⃣ Login Automation**
- **URL:** [Login Page](https://the-internet.herokuapp.com/login)

### **Features Implemented:**
- Automates login functionality with valid and invalid credentials.
- Verifies successful login by checking the presence of a logout button.
- Captures a screenshot if login fails.
- Implements exception handling and error reporting.

## **2️⃣ Dynamic Table Handling**
- **URL:** [Dynamic Table](https://the-internet.herokuapp.com/tables)

### **Features Implemented:**
- Extracts and prints company names from the dynamic table.
- Verifies if a specific company (e.g., "Jason Doe") exists in the table.
- Implements a generic function to extract data from any table.

## **3️⃣ JavaScript Alerts & Pop-ups Handling**
- **URL:** [JavaScript Alerts](https://the-internet.herokuapp.com/javascript_alerts)

### **Features Implemented:**
- Clicks on each JavaScript Alert (JS Alert, JS Confirm, JS Prompt).
- Handles pop-ups dynamically and verifies alert messages.
- Implements a generic method to handle any JavaScript pop-up.

## **4️⃣ File Upload Automation**
- **URL:** [File Upload](https://the-internet.herokuapp.com/upload)

### **Features Implemented:**
- Automates file upload using Selenium WebDriver.
- Verifies that the uploaded file name appears after a successful upload.

---

## **📌 Extra Features Implemented**
✅ **Parallel execution** of test cases using Selenium Grid

✅ **Merged test reports** for a consolidated view
 
✅ Implemented **exception handling** across all methods  

✅ Created an **email reporting system** for test results

✅ Added css and js files for **customized HTML reports**

✅ Tests can be executed on different browsers, either in parallel or sequentially, by setting the browser attribute in the test tags of the `testng.xml` file