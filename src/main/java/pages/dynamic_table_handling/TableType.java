package pages.dynamic_table_handling;


import org.openqa.selenium.By;

public enum TableType {
    TABLE_ONE(By.xpath("//table[@id='table1']/tbody/tr")),
    TABLE_TWO(By.xpath("//table[@id='table2']/tbody/tr"));

    private final By locator;

    TableType(By locator) {
        this.locator = locator;
    }

    public By getLocator() {
        return locator;
    }
}
