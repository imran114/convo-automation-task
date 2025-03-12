package pages.login;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.common_components.button_actions.ButtonActions;
import utils.common_components.edit_text_fields.EditText;
import utils.selenium_utils.SeleniumUtils;


public class LoginPage extends SeleniumUtils {

    /*
     * Helper classes for entering text and clicking buttons
     * */

    private final EditText editText;
    private final ButtonActions buttonActions;
    /*
     * Locator for the elements on the login page
     * */
    public static final By userNameTextFieldLocator = By.xpath("//input[@id='username']");
    public static final By passwordFieldLocator = By.xpath("//input[@id='password']");
    public static final By loginButtonLocator = By.xpath("//button[@type='submit']");
    public static final By userNameValidationMsgLocator = By.xpath("//div[@id='flash' and contains(text(),'invalid')]");
    public static final By passwordValidationMsgLocator = By.xpath("//div[@id='flash' and contains(text(),'Your password is invalid')]");
    public static final By logoutButtonLocator = By.xpath("//a[contains(@href,'logout')]");
    public static final By invalidXpathLocator = By.xpath("//a[@id='abc']");


    public LoginPage(WebDriver driver) {
        super(driver); // Pass the driver to the parent class
        this.editText = new EditText(driver);
        this.buttonActions = new ButtonActions(driver);
    }

    public String enterUsername(String username) {
        return editText.enterTextBoxValue(userNameTextFieldLocator, username, "Username");
    }

    public String verifyUsernameValidation() {
        return isElementDisplayed(userNameValidationMsgLocator) ? "Pass: Username validation message is displayed" : "Fail: Username validation message is not displayed";
    }

    public String verifyPasswordValidation() {
        return isElementDisplayed(passwordValidationMsgLocator) ? "Pass: Password validation message is displayed" : "Fail: Password validation message is not displayed";
    }

    public String verifyLogoutButtonDisplay() {
        return isElementDisplayed(logoutButtonLocator) ? "Pass: Logout button displayed and login is successful" : "Fail: Logout button not displayed";
    }

    public String verifyScreenshotIsTaken() {
        /*
         * I am intentionally using an invalid xpath locator to take the screenshot
         * like if we expect some scenario and it is not fulfilled, then screenshot is taken
         * */
        if (isDisplayed(invalidXpathLocator)) {
            return "Pass: invalid locator displayed";
        } else {
            getFailedElementScreenShot();
            return "Fail: invalid locator not displayed, screenshot is taken";
        }
    }

    public String enterPassword(String password) {
        return editText.enterTextBoxValue(passwordFieldLocator, password, "Password");
    }

    public String clickLoginButton() {
        return buttonActions.clickOnButton(loginButtonLocator, "Login");
    }
}
