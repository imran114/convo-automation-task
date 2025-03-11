package test_base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import static test_base.DriverManager.getDriver;


@Slf4j
// This class initializes the page objects before each test class.
public class PageObjects extends BaseClass {
    protected String actualResult = "";
    protected String useCaseName;
    protected WebDriver driver;

    protected void parentTest(String useCaseName, String description) {
        extentReport.createTest(useCaseName, description);
    }

    protected void setUpUseCaseName(String useCaseName, String childTestName) {
        emailBody.enterTextToEmailBody("- " + useCaseName);
        parentTest(useCaseName, "Test cases of " + useCaseName);
        extentReport.logChildTestNameAndDescription(useCaseName, childTestName);
    }

    @BeforeClass
    public void initializeDriver(){
        driver =  getDriver();
    }

}
