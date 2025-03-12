package tests.login;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import test_base.PageObjects;
import utils.test_validator.TestValidator;


public class LoginTest extends PageObjects {

    private LoginPage loginPage;
    private final String validUsername = "tomsmith";
    private final String validPassword = "SuperSecretPassword!";


    @BeforeClass
    private void setup() {
        useCaseName = "Login Test";
        loginPage = new LoginPage(driver);
        setUpUseCaseName(useCaseName, "User should be able to enter login credentials and verify login functionality");
    }


    @Test(priority = 1)
    public void invalidUsernameLogin() {
        extentReport.logChildTestNameAndDescription(useCaseName, "Attempt login with invalid username");
        loginPage.enterUsername("invalidUser");
        loginPage.enterPassword(validPassword);
        loginPage.clickLoginButton();
        actualResult = loginPage.verifyUsernameValidation();
        extentReport.testInfo("Checking if error message is displayed for invalid username");
        extentReport.logStepResult(actualResult);
        TestValidator.validateTest(actualResult);
    }

    @Test(priority = 2)
    public void invalidPasswordLogin() {
        extentReport.logChildTestNameAndDescription(useCaseName, "Attempt login with invalid password");
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword("invalidPassword");
        loginPage.clickLoginButton();
        actualResult = loginPage.verifyPasswordValidation();
        extentReport.testInfo("Checking if error message is displayed");
        extentReport.logStepResult(actualResult);
        TestValidator.validateTest(actualResult);
    }

    @Test(priority = 3)
    public void emptyUsernameLogin() {
        extentReport.logChildTestNameAndDescription(useCaseName, "Attempt login with empty username");
        loginPage.enterUsername("");
        loginPage.enterPassword(validPassword);
        loginPage.clickLoginButton();
        actualResult = loginPage.verifyUsernameValidation();
        extentReport.testInfo("Checking if error message is displayed for empty username");
        extentReport.logStepResult(actualResult);
        TestValidator.validateTest(actualResult);
    }

    @Test(priority = 4)
    public void emptyPasswordLogin() {
        extentReport.logChildTestNameAndDescription(useCaseName, "Attempt login with empty password");
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
        actualResult = loginPage.verifyPasswordValidation();
        extentReport.testInfo("Checking if error message is displayed");
        extentReport.logStepResult(actualResult);
        TestValidator.validateTest(actualResult);
    }

    @Test(priority = 5)
    public void enterUsername() {
        extentReport.logChildTestNameAndDescription(useCaseName, "Enter username in the username field");
        actualResult = loginPage.enterUsername(validUsername);
        extentReport.testInfo("Verify if username is entered");
        extentReport.logStepResult(actualResult);
        TestValidator.validateTest(actualResult);
    }

    @Test(priority = 6)
    public void enterPassword() {
        extentReport.logChildTestNameAndDescription(useCaseName, "Enter password in the password field");
        actualResult = loginPage.enterPassword(validPassword);
        extentReport.testInfo("Verify if password is entered successfully");
        extentReport.logStepResult(actualResult);
        TestValidator.validateTest(actualResult);
    }

    @Test(priority = 7)
    public void clickLoginButton() {
        extentReport.logChildTestNameAndDescription(useCaseName, "Click Login Button");
        actualResult = loginPage.clickLoginButton();
        extentReport.testInfo("Verify if Login Button is clicked");
        extentReport.logStepResult(actualResult);
        TestValidator.validateTest(actualResult);
    }

    @Test(priority = 8)
    public void verifySuccessfulLogin() {
        extentReport.logChildTestNameAndDescription(useCaseName, "Verify successful login by checking Logout button");
        actualResult = loginPage.verifyLogoutButtonDisplay();
        extentReport.testInfo("Checking if logout button is present");
        extentReport.logStepResult(actualResult);
        TestValidator.validateTest(actualResult);
    }

    @Test(priority = 9)
    public void screenshotTest() {
        extentReport.logChildTestNameAndDescription(useCaseName, "Taking screenshot of the page for the assessment purpose");
        actualResult = loginPage.verifyScreenshotIsTaken();
        extentReport.testInfo("Taking screenshot of the page");
        extentReport.logStepResult(actualResult);
        TestValidator.validateTest(actualResult);
    }


}
