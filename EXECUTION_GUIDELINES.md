
# **📌 How to Run the Tests**

## **Pre-requisites**
Ensure you have the following installed:
- **Java (JDK 8 or later)**
- **Maven**
- **WebDriver binaries** (managed via Maven dependencies)

## **Sequential Execution**
1. Navigate to `testng.xml` file located at the bottom of the project structure.
2. Right-click inside the file and select `Run 'testng.xml'`.
## **Test Reports**
- **HTML test reports** are generated in:
  ```
  📂 src/test/resources/reports/convo_task-250311-report.html
  ```
- **the report name format I've used is** 
    `convo-task-<todays_date>-report.html` 

  ## **Parallel Execution**
1. Navigate to `testng.xml` file.
2. Change the `parallel` attribute in the `<suite>` tag to `tests`. like this:
``` parallel="tests" ``` and thread-count to the number of threads you want to run in parallel.
3. change `runOnGrid` attribute in the `<parameter>` tag to `true`. like 
    `runOnGrid="true"` on suit level
4. Right-click inside the file and select `Run 'testng.xml'`.
5. To run the tests on the grid, **we need to start the selenium grid server.** 
    - Open the terminal and navigate to the folder where the jar file is downloaded, in this case it is in the `src/test/java/utils/grid_configurations/selenium-server.jar` directory.
    - Run the command `java -jar selenium-server.jar -hub` to start the hub.
    - Run the command `java -jar selenium-server.jar node` to start the node.
    - Now the grid is ready to run the tests.
**But I have handled this programmatically** like grid will start automatically when we set `runOnGrid` to `true` in the `testng.xml` file.
6. Tests can be executed on different browsers, either in parallel or sequentially, by setting the browser attribute in the test tags of the `testng.xml` file
7. Parallel execution significantly reduces the execution time of the tests.

## **Emailing Automation Report**
1. To send the test report via email, navigate to `src/test/resources/test_data_files/email_configuration.properties`.
2. Update the email configuration properties with your email credentials (this will be the email which will be used to send the report).
3. Then navigate to `src/test/java/utils/email/EmailSender.java` and provide the `cc` email addresses here in this line ` private final String[] cc = {"email1","email2"};`
4. Navigate to SuiteListeners.java file `src/test/java/utils/listener/SuiteListener.java` and provide the recipient email addresses here in this line `String recipient = "recipient email";
5. Now, in the SuiteListeners.java file, uncomment the `sendEmail()` method.
6. After the test execution, the test report will be sent automatically to the email addresses provided.