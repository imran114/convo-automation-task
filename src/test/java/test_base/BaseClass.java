package test_base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;
import utils.email.EmailBody;
import utils.email.EmailSender;
import utils.file_reader.PropertiesFileReader;
import utils.reporter.ExtentReport;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import static test_base.DriverManager.*;


// This class sets up and tears down the test environment before and after each suite.
public class BaseClass {

    public static ExtentReport extentReport;
    public static EmailBody emailBody;
    private static final Logger logger = LogManager.getLogger(BaseClass.class);


    // This method sets up the test environment before each suite.
    @BeforeSuite
    @Parameters({"runOnGrid"})
    public void setUp(boolean runOnGrid) {
        logger.info("Setting up test suite...");
        System.out.println("getDriver(): "+getDriver());
        extentReport = new ExtentReport(getDriver());
        extentReport.createReport();
//        setExtentReport(extentReport);
        emailBody = new EmailBody();
        emailBody.clearEmailBody();
        logger.info("Test suite setup complete.");
    }


    @BeforeTest
    @Parameters({"browser", "runOnGrid", "taskType"})
    public void initializeDriver(@Optional("chrome") String browser, @Optional("false") boolean runOnGrid, @Optional("login_automation") String taskType) {
        extentReport.createReport();
        // Setup WebDriver for each test
        setupDriver(browser, runOnGrid);

        getDriver().get(getUrl(taskType));
        getDriver().manage().window().maximize();

        logger.info("Opened URL in Browser: " + browser);
    }


    // This method tears down the test environment after each suite.
    @AfterSuite
    public void tearDown() {
        logger.info("Tearing down test suite...");
        // Finalize and generate the extent report by flushing any pending logs and resources.
        extentReport.flushReport();
    }

    @AfterTest
    public void quitTheDriver() {
        quitDriver();
    }

    public static String getUrl(String taskType) {
        return switch (taskType) {
            case "login_automation" -> "https://the-internet.herokuapp.com/login";
            case "dynamic_table_handling" -> "https://the-internet.herokuapp.com/tables";
            case "javaScript_alerts" -> "https://the-internet.herokuapp.com/javascript_alerts";
            case "file_upload" -> "https://the-internet.herokuapp.com/upload";
            default -> throw new RuntimeException("Invalid task type: " + taskType);
        };
    }



}