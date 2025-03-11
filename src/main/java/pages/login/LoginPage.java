package pages.login;


import org.openqa.selenium.WebDriver;
import utils.common_components.button_actions.ButtonActions;
import utils.common_components.edit_text_fields.EditText;
import utils.selenium_utils.SeleniumUtils;

import static pages.login.LoginLocators.*;

public class LoginPage extends SeleniumUtils {


    private final EditText editText;
    private final ButtonActions buttonActions;
//    private final By userNameTextFieldLocator = By.xpath("//input[@id='username']");
//    private final By passwordFieldLocator = By.xpath("//input[@id='password-text-field']");
//    private final By signInButtonLocator = By.xpath("//button[@id='signin Button']");


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
         * like if we expect something and it is not there, we then take a screenshot
         * */
        return isDisplayed(invalidXpathLocator) ? "Pass: Screenshot is taken" : "Fail: Screenshot is not taken";
    }

    public String enterPassword(String password) {
        return editText.enterTextBoxValue(passwordFieldLocator, password, "Password");
    }

    public String clickLoginButton() {
        return buttonActions.clickOnButton(loginButtonLocator, "Login");
    }
}
