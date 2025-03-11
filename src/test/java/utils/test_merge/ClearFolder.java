package utils.test_merge;

import org.testng.annotations.Test;
import test_base.PageObjects;
import utils.email.EmailBody;

import java.io.File;

public class ClearFolder extends PageObjects {

    public static EmailBody emailBody = new EmailBody();

    @Test(priority = 1)
    public void clearFolder() {
        System.out.println("Clear Folder Called: ");
        String folderPath = System.getProperty("user.dir") + "/src/test/resources/reports/json_files";
        clearJsonFolder(folderPath);
        System.out.println("merge done");

    }

    @Test(priority = 2)
    public void clearEmailBody() {
        emailBody = new EmailBody();
        emailBody.clearEmailBody();
        emailBody.enterTextToEmailBody("Use Cases Details"+"\n");
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
                        if (file.isDirectory()) {

                            // Recursive call to clear subdirectories
                            clearJsonFolder(file.getAbsolutePath());
                        } else {
                            // Delete the file
                            if (!file.delete()) {
                                System.err.println("Failed to delete file: " + file.getAbsolutePath());
                            }
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
