package test_base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

import static utils.grid_configurations.GridConfig.startSeleniumGrid;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // Setup WebDriver dynamically (Supports Selenium Grid & Local Execution)
    public static void setupDriver(String browser, boolean runOnGrid) {
        WebDriver driver = null;

        try {
            if (runOnGrid) {

                startSeleniumGrid();

                // Setup Remote WebDriver (Selenium Grid)
                DesiredCapabilities capabilities = new DesiredCapabilities();

                if (browser.equalsIgnoreCase("chrome")) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-extensions");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--remote-allow-origins=*");  // Fix security issues
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    capabilities.setBrowserName("chrome");

                } else if (browser.equalsIgnoreCase("firefox")) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("--disable-extensions");
                    options.addArguments("--disable-gpu");
                    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
                    capabilities.setBrowserName("firefox");

                } else if (browser.equalsIgnoreCase("edge") || browser.equalsIgnoreCase("MicrosoftEdge")) {
                    EdgeOptions options = new EdgeOptions();
                    options.addArguments("--disable-extensions");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-gpu");
                    capabilities.setCapability(EdgeOptions.CAPABILITY, options);
                    capabilities.setBrowserName("MicrosoftEdge");
                } else {
                    throw new IllegalArgumentException("Unsupported Browser: " + browser);
                }

                System.out.println("STARTING TESTS ON " + capabilities.getBrowserName() + " BROWSER");

                // Change "localhost" to Grid Hub URL if needed
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            } else {
                // Setup Local WebDriver
                driver = switch (browser.toLowerCase()) {
                    case "chrome" -> new ChromeDriver(new ChromeOptions().addArguments("--disable-extensions"));
                    case "firefox" -> new FirefoxDriver(new FirefoxOptions().addArguments("--disable-extensions"));
                    case "edge" -> new EdgeDriver(new EdgeOptions().addArguments("--disable-extensions"));
                    default -> throw new IllegalArgumentException("Unsupported Browser: " + browser);
                };
            }

            // Set timeouts to prevent unresponsive browsers
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(20));
            driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(60));
            driver.manage().timeouts().scriptTimeout(java.time.Duration.ofSeconds(60));

            driverThreadLocal.set(driver); // Store WebDriver in ThreadLocal for parallel execution
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL!", e);
        }
    }

    // Get WebDriver for the current thread
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    // Quit WebDriver and remove it from ThreadLocal
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
