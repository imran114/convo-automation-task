package tests.dynamic_table_handling;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.dynamic_table_handling.DynamicTableHandlingPage;
import pages.dynamic_table_handling.TableType;
import test_base.PageObjects;
import utils.test_validator.TestValidator;

public class DynamicTableHandlingTest extends PageObjects {

    DynamicTableHandlingPage dynamicTableHandlingPage;

    @BeforeClass
    private void setup() {
        useCaseName = "Dynamic Table Handling Test";
        dynamicTableHandlingPage = new DynamicTableHandlingPage(driver);
        setUpUseCaseName(useCaseName, "Printing Company names from Table");
    }

    @Test(priority = 1)
    public void HandleTableOneDynamicallyTest() {
        extentReport.logChildTestNameAndDescription(useCaseName, "printing company names from table one");
        actualResult = dynamicTableHandlingPage.extractAndValidateCompanyNames(TableType.TABLE_ONE);
        extentReport.testInfo("I am printing company names from table one");
        extentReport.logStepResult(actualResult);
        TestValidator.validateTest(actualResult);
    }

    @Test(priority = 2)
    public void HandleTableTwoDynamicallyTest() {
        extentReport.logChildTestNameAndDescription(useCaseName, "printing company names from table two");
        actualResult = dynamicTableHandlingPage.extractAndValidateCompanyNames(TableType.TABLE_TWO);
        extentReport.testInfo("I am printing company names from table two");
        extentReport.logStepResult(actualResult);
        TestValidator.validateTest(actualResult);
    }

}
