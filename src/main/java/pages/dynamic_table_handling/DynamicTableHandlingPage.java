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

    //  method to extract company names as a list
    private List<String> getCompanyNames(TableType tableType) {
        By tableRowsLocator = tableType.getLocator();
        List<WebElement> rows = returnWebElementsList(tableRowsLocator);
        List<String> companyNames = new ArrayList<>();

        for (WebElement row : rows) {
            WebElement lastNameElement = row.findElement(By.xpath("./td[1]"));
            WebElement firstNameElement = row.findElement(By.xpath("./td[2]"));

            String fullName = firstNameElement.getText().trim() + " " + lastNameElement.getText().trim();
            companyNames.add(fullName);
        }

        return companyNames;
    }


    public String extractAndValidateCompanyNames(TableType tableType) {
        List<String> companyNames = getCompanyNames(tableType);

        if (companyNames.isEmpty()) {
            return "Fail: No company names found in the table!";
        } else {
            // Print names for debugging
            companyNames.forEach(System.out::println);
            return "Pass: Company Names Extracted and printed successfully!";
        }
    }

    public String isCompanyPresent(TableType tableType, String companyName) {
        List<String> companyNames = getCompanyNames(tableType); // Using common method

        boolean found = companyNames.stream()
                .anyMatch(name -> name.trim().equalsIgnoreCase(companyName.trim()));

        return found ? "Pass: Company Name found in the table!" : "Fail: Company Name not found in the table!";
    }
}
