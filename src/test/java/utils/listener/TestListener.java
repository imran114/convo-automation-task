package utils.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

public class TestListener implements ITestListener {
    private boolean isLoggedIn;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Starting");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Start");
//        isLoggedIn = CredentialsLoader.loadCredentials()
        if (!isLoggedIn) {
            throw new SkipException("Login failed. Skipping further tests.");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}

