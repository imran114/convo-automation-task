package pages.login;

import org.openqa.selenium.By;

public class LoginLocators {

    public static final By userNameTextFieldLocator = By.xpath("//input[@id='username']");
    public static final By passwordFieldLocator = By.xpath("//input[@id='password']");
    public static final By loginButtonLocator = By.xpath("//button[@type='submit']");
    public static final By userNameValidationMsgLocator = By.xpath("//div[@id='flash' and contains(text(),'invalid')]");
    public static final By passwordValidationMsgLocator = By.xpath("//div[@id='flash' and contains(text(),'Your password is invalid')]");
    public static final By logoutButtonLocator = By.xpath("//a[contains(@href,'logout')]");
    public static final By invalidXpathLocator = By.xpath("//a[@id='abc']");
}
