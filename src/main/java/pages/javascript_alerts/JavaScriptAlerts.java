package pages.javascript_alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.selenium_utils.SeleniumUtils;

public class JavaScriptAlerts extends SeleniumUtils {
    public JavaScriptAlerts(WebDriver driver) {
        super(driver);
    }

    private final By jsAlertButton = By.xpath("//button[text()='Click for JS Alert']");
    private final By jsConfirmButton = By.xpath("//button[text()='Click for JS Confirm']");
    private final By jsPromptButton = By.xpath("//button[text()='Click for JS Prompt']");


    public String handleJavaScriptAlert(AlertType alertType, String inputText, boolean acceptAlert) {

        switch (alertType) {
            case ALERT:
                click(jsAlertButton);
                break;
            case CONFIRM:
                click(jsConfirmButton);
                break;
            case PROMPT:
                click(jsPromptButton);
                break;
            default:
                throw new IllegalArgumentException("Unsupported alert type: " + alertType);
        }

        try {

            // Switch to alert
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("JavaScript Alert Message: " + alertText);

            // Handle input for JS Prompt
            if (alertType == AlertType.PROMPT && inputText != null && !inputText.trim().isEmpty()) {
                alert.sendKeys(inputText);
                System.out.println("Entered text in alert: " + inputText);
            }

            // Handle OK (accept) or Cancel (dismiss)
            if (acceptAlert) {
                alert.accept();
                System.out.println("Alert accepted (OK clicked)");
            } else {
                alert.dismiss();
                System.out.println("Alert dismissed (Cancel clicked)");
            }

            return alertText;
        } catch (Exception e) {
            throw new RuntimeException("Failed to handle JavaScript alert: " + e.getMessage());
        }
    }
}



