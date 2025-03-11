package pages.dynamic_table_handling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.selenium_utils.SeleniumUtils;

import java.util.ArrayList;
import java.util.List;

public class DynamicTableHandlingPage extends SeleniumUtils {

    public DynamicTableHandlingPage(WebDriver driver) {
        super(driver);
    }


    public String extractAndValidateCompanyNames(TableType tableType) {
        By tableRowsLocator = tableType.getLocator();
        List<WebElement> rows = returnWebElementsList(tableRowsLocator);
        List<String> companyNames = new ArrayList<>();

        for (WebElement row : rows) {
            // Locate Last Name (1st column) and First Name (2nd column)
            WebElement lastNameElement = row.findElement(By.xpath("./td[1]"));
            WebElement firstNameElement = row.findElement(By.xpath("./td[2]"));

            // Combine First & Last Name
            String fullName = firstNameElement.getText() + " " + lastNameElement.getText();
            companyNames.add(fullName);
        }

        if (companyNames.isEmpty()) {
            return "Fail: No company names found in the table!";
        } else {
            // print company names
            for (String name : companyNames) {
                System.out.println(name);
            }
            return "Pass: Company Names Extracted and printed successfully!";
        }
    }

}