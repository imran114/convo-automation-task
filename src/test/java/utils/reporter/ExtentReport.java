package utils.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.nio.file.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import utils.test_merge.MergeJsonReports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExtentReport {
    private WebDriver driver ;
    private static final Logger logger = LogManager.getLogger(ExtentReport.class);
    private final ExtentReports extentReports;
    private ExtentTest extentTest;
    private ExtentSparkReporter extentSparkReporter;
    private final String screenshotPath ;

    // Test maps for hierarchy
    Map<String, ExtentTest> parentTestsMap = new HashMap<>();
    Map<String, ExtentTest> childTestsMap = new HashMap<>();


    public ExtentReport(WebDriver driver) {
        this.driver = driver;
        this.extentReports = new ExtentReports();
        screenshotPath = System.getProperty("user.dir") +"/src/main/resources/screenShots/screenShot.png";
    }

    // Creates the report with configuration matching the original
    public void createTest(String testName, String testDescription) {
        if (!parentTestsMap.containsKey(testName)) {
            ExtentTest parentTest = extentReports.createTest(testName, testDescription)
                    .assignCategory(testName)
                    .assignDevice(System.getProperty("os.name"));
            parentTestsMap.put(testName, parentTest);
            extentTest = parentTest;
        }
    }

    public void createReport() {
        String reportFilePath = System.getProperty("user.dir") + "/src/test/resources/reports/json_files";
        File reportDir = new File(reportFilePath);

        if (reportDir.exists() && reportDir.isDirectory()) {
            System.out.println("yes, directory is created");
        } else {
            System.out.println("no, directory is not created");
        }

        // Use current timestamp for unique filenames
        long timestamp = System.currentTimeMillis();
        String jsonFileName = "extent_" + timestamp + ".json";
        System.out.println("jsonFileName: "+jsonFileName);
        String jsonPath = reportFilePath + "/" + jsonFileName;

        JsonFormatter jsonFormatter = new JsonFormatter(jsonPath);
        try {
            extentReports.createDomainFromJsonArchive(jsonPath);
        } catch (IOException e) {
            logger.error("Error creating domain from JSON archive", e);
            throw new RuntimeException(e);
        }
        extentReports.attachReporter(jsonFormatter);
    }


    public void logChildTestNameAndDescription(String parentTestName, String childTestName) {
        if (parentTestsMap.containsKey(parentTestName)) {
            ExtentTest childTest = parentTestsMap.get(parentTestName).createNode(childTestName).assignCategory(parentTestName);
            childTestsMap.put(childTestName, childTest);
            extentTest = childTest;
        }
    }


    public void testPass(String passInfo) {
        extentTest.pass(passInfo);
    }

    public void testFail(String failInfo) {
        try {
            String base64Image = convertImageToBase64(screenshotPath);
            extentTest.fail(failInfo, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
            if (isFilePresent(screenshotPath)) deleteScreenshot();
        } catch (Exception e) {
            logger.error("Error while adding screenshot for failed test", e);
        }
    }

    public void testSkip(String reasonToSkip) {
        extentTest.skip(reasonToSkip);
    }

    public void testInfo(String testInfo) {
        extentTest.info(testInfo);
    }

    public void logStepResult(String stepResult) {
        if (stepResult.contains("Pass")) {
            testPass(stepResult);
        } else if (stepResult.contains("Fail")) {
            testFail(stepResult);
        } else if (stepResult.contains("Skip")) {
            testSkip(stepResult);
        } else {
            testInfo(stepResult);
        }
    }

    public void captureScreenshot(String screenshotPath) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotPath);
            FileHandler.copy(srcFile, destFile);
            System.out.println("Screenshot captured: " + screenshotPath);
        } catch (IOException e) {
            System.err.println("Error capturing screenshot: " + e.getMessage());
        }
    }
    private void deleteScreenshot() {
        File screenshotFile = new File(screenshotPath);
        if (screenshotFile.exists()) {
            if (!screenshotFile.delete()) {
                logger.error("Failed to delete the screenshot file.");
            }
        }
    }



    public void mergeReports() {
        // Generate date in YYMMDD format
        String currentDate = new SimpleDateFormat("yyMMdd").format(new Date()).toLowerCase();

        // Construct dynamic report name
        String reportFileName = "convo_task_" + currentDate + "_report.html";
        String reportFilePath = "src/test/resources/reports/" + reportFileName;

        // Merge JSON reports
        MergeJsonReports.mergeJson();

        // Create ExtentSparkReporter with the new dynamic name
        ExtentSparkReporter mergedSpark = new ExtentSparkReporter(reportFilePath);
        extentReportConfig(mergedSpark);

        // Initialize ExtentReports
        ExtentReports extentMerged = new ExtentReports();
        try {
            extentMerged.createDomainFromJsonArchive("src/test/resources/reports/merged_report.json");
        } catch (IOException e) {
            throw new RuntimeException("Error while merging JSON reports: " + e.getMessage(), e);
        }

        // Attach the renamed report
        extentMerged.attachReporter(mergedSpark);
        extentMerged.flush();

        System.out.println("Merged report generated: " + reportFilePath);
    }

    private void extentReportConfig(ExtentSparkReporter mergedSpark) {
        String cssFilePath = "extentReport.css";
        String jsFilePath = "reportConfigJs.js";
        String cssContent;
        String jsContent;
        cssContent = createStringFromFileContent(cssFilePath);
        jsContent = createStringFromFileContent(jsFilePath);
        mergedSpark.config().setDocumentTitle("Convo Task Automation Report");
        mergedSpark.config().setTheme(Theme.DARK);
        mergedSpark.config().setCss(cssContent);
        mergedSpark.config().setJs(jsContent);
        mergedSpark.viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY, ViewName.DEVICE}).apply();
    }

    private String createStringFromFileContent(String filePath) {
        try {
            return new String(java.nio.file.Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String convertImageToBase64(String imagePath) {
        File screenshotFile = new File(imagePath);
        if (!screenshotFile.exists()) {
            System.err.println("Screenshot file not found at: " + imagePath);
            return "Screenshot file not found at: " + imagePath;
        }
        File imageFile = new File(imagePath);
        try (FileInputStream fis = new FileInputStream(imageFile)) {
            byte[] imageData = fis.readAllBytes();
            return Base64.getEncoder().encodeToString(imageData);
        } catch ( IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isFilePresent(String screenshotPath) {
        return new File(screenshotPath).exists();
    }

    public void flushReport() {
        extentReports.flush();
        deleteScreenshot();
    }
}
