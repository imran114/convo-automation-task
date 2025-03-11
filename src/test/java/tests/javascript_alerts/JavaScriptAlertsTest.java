package tests.javascript_alerts;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.javascript_alerts.AlertType;
import pages.javascript_alerts.JavaScriptAlerts;
import test_base.PageObjects;

import static test_base.DriverManager.getDriver;


public class JavaScriptAlertsTest extends PageObjects {
    private JavaScriptAlerts javaScriptAlerts;
    private String actualResult;

    @BeforeClass
    public void setup() {
        useCaseName = "JavaScript Alert Handling";
        javaScriptAlerts = new JavaScriptAlerts(getDriver());
        setUpUseCaseName(useCaseName, "Javascript Alerts Handling Tests");
    }

    @Test(priority = 1)
    public void handleJSAlert() {
        extentReport.logChildTestNameAndDescription(useCaseName, "Handle JS Alert");
        extentReport.testInfo("Getting alert text");
        actualResult = javaScriptAlerts.handleJavaScriptAlert(AlertType.ALERT, null, true);
        String expectedMessage = "I am a JS Alert";
        extentReport.logStepResult(actualResult);
        Assert.assertEquals(actualResult, expectedMessage);
    }

    @Test(priority = 2)
    public void handleJSConfirmAccept() {
        extentReport.logChildTestNameAndDescription(useCaseName, "Handle JS Confirm - Accept");
        extentReport.testInfo("Getting alert text");
        actualResult = javaScriptAlerts.handleJavaScriptAlert(AlertType.CONFIRM, null, true);
        String expectedMessage = "I am a JS Confirm";
        extentReport.logStepResult(actualResult);
        Assert.assertEquals(actualResult, expectedMessage);
    }

    @Test(priority = 3)
    public void handleJSConfirmDismiss() {
        extentReport.logChildTestNameAndDescription(useCaseName, "Handle JS Confirm - Dismiss");
        extentReport.testInfo("Getting alert text");
        actualResult = javaScriptAlerts.handleJavaScriptAlert(AlertType.CONFIRM, null, false);
        String expectedMessage = "I am a JS Confirm";
        extentReport.logStepResult(actualResult);
        Assert.assertEquals(actualResult, expectedMessage);
    }

    @Test(priority = 4)
    public void handleJSPromptAcceptWithText() {
        extentReport.logChildTestNameAndDescription(useCaseName, "Handle JS Prompt - Accept with Input");
        extentReport.testInfo("Getting alert text");
        actualResult = javaScriptAlerts.handleJavaScriptAlert(AlertType.PROMPT, "Automation Test", true);
        String expectedMessage = "I am a JS prompt";
        extentReport.logStepResult(actualResult);
        Assert.assertEquals(actualResult, expectedMessage);
    }

    @Test(priority = 5)
    public void handleJSPromptDismiss() {
        extentReport.logChildTestNameAndDescription(useCaseName, "Handle JS Prompt - Dismiss");
        extentReport.testInfo("Getting alert text");
        actualResult = javaScriptAlerts.handleJavaScriptAlert(AlertType.PROMPT, null, false);
        String expectedMessage = "I am a JS prompt";
        extentReport.logStepResult(actualResult);
        Assert.assertEquals(actualResult, expectedMessage);
    }
}
