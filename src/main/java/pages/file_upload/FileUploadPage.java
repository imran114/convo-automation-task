package pages.file_upload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.selenium_utils.SeleniumUtils;

import java.io.File;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class FileUploadPage extends SeleniumUtils {
    public FileUploadPage(WebDriver driver) {
        super(driver);
    }

    private final By fileUploadFieldLocator = By.xpath("//input[@id='file-upload']");
    private final String filePath = "src/test/resources/test_data_files/upload_files/test_file_to_upload.png";

    public String uploadFile() {
        String result;
        try {
            String absolutePath = Paths.get(filePath).toAbsolutePath().toString();
            File file = new File(absolutePath);

            if (!file.exists()) {
                throw new RuntimeException("File not found at: " + absolutePath);
            }

            returnWebElement(fileUploadFieldLocator).sendKeys(absolutePath);
            result = "Pass: File uploaded successfully";
        } catch (Exception e) {
            result = "Fail: File upload failed - " + e.getMessage();
        }
        return result;
    }


    private String getActualFileName() {
        return Paths.get(filePath).getFileName().toString();
    }


    public String verifyFileNameDisplayAfterUploading() {
        String result = "";
        try {
            String actualFileName = getActualFileName();
            String expectedFileName = returnAttribute(fileUploadFieldLocator, "value");

            // removing "C:\fakepath\"
            expectedFileName = expectedFileName.substring(expectedFileName.lastIndexOf("\\") + 1);

            System.out.println("expectedFileName: " + expectedFileName);
            System.out.println("actualFileName: " + actualFileName);
            result = expectedFileName.equals(actualFileName) ? "Pass: File name is displayed after uploading" : "Fail: File name mismatch";

        } catch (Exception e) {
            result = "Fail: File name is not displayed after uploading - " + e.getMessage();
        }
        return result;
    }


}
