package utils.test_merge;

import org.openqa.selenium.WebDriver;
import test_base.DriverManager;
import utils.email.EmailSender;
import utils.reporter.ExtentReport;

import java.io.File;

public class ClearFolderAndMergeReport {

    ExtentReport extentReport;

    public void mergeReport() {
        extentReport = new ExtentReport(DriverManager.getDriver());
        extentReport.mergeReports();
        System.out.println("merge done");
    }

    public void clearFolder() {
        String folderPath = System.getProperty("user.dir") + "/src/test/resources/reports/json_files";
        clearJsonFolder(folderPath);
        System.out.println("merge done");

    }

    private void clearJsonFolder(String folderPath) {
        try {
            File folder = new File(folderPath);
            // Check if the folder exists
            if (folder.exists()) {
                // List all files and subdirectories in the folder
                File[] files = folder.listFiles();
                // Delete each file and subdirectory
                if (files != null) {
                    for (File file : files) {
                        // Delete the file
                        if (!file.delete()) {
                                System.err.println("Failed to delete file: " + file.getAbsolutePath());
                            }
                        }
                    }

            } else {
                System.out.println("Folder does not exist: " + folderPath);
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
