package utils.grid_configurations;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GridConfig {

    private static final String SELENIUM_SERVER_PATH = new File("src/test/java/utils/grid_configurations/selenium-server.jar").getAbsolutePath();
    private static final Logger logger = Logger.getLogger(GridConfig.class.getName());
    /**
     * Checks if the Selenium Grid Hub is already running.
     *
     * @return true if Grid is running, false otherwise
     */
    private static boolean isGridRunning() {
        try {
            Process process = Runtime.getRuntime().exec("netstat -ano | findstr :4444");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return reader.readLine() != null; // If output exists, Grid is running
        } catch (IOException e) {
            return false;
        }
    }
    /**
     * Starts Selenium Grid (Hub + Node) only if it's not already running.
     */
    public static void startSeleniumGrid() {
        if (isGridRunning()) {
            logger.info("Selenium Grid is already running. Skipping startup");
            System.out.println("Selenium Grid is already running. Skipping startup");
            return;
        }

        try {
            logger.info("Selenium JAR Path: " + SELENIUM_SERVER_PATH);

            // Ensure the JAR file exists
            File jarFile = new File(SELENIUM_SERVER_PATH);
            if (!jarFile.exists()) {
                throw new RuntimeException("Selenium server JAR file not found at: " + SELENIUM_SERVER_PATH);
            }

            logger.info("Starting Selenium Grid Hub...");
            System.out.println("Starting Selenium Grid Hub...");
            ProcessBuilder hubProcess = new ProcessBuilder("java", "-jar", SELENIUM_SERVER_PATH, "hub");
            hubProcess.start();

            Thread.sleep(5000); // Wait for hub to initialize

            logger.info("Starting Selenium Grid Node...");
            System.out.println("Starting Selenium Grid Node...");
            ProcessBuilder nodeProcess = new ProcessBuilder("java", "-jar", SELENIUM_SERVER_PATH, "node", "--selenium-manager", "true");
            nodeProcess.start();

            logger.info("Selenium Grid Hub and Node started successfully.");
            System.out.println("Selenium Grid Hub and Node started successfully.");
        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, "Error starting Selenium Grid!", e);
            System.out.println("Error starting Selenium Grid!");
            throw new RuntimeException("Failed to start Selenium Grid", e);
        }
    }
}
