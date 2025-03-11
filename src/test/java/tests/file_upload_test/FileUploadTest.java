package tests.file_upload_test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.file_upload.FileUploadPage;
import test_base.PageObjects;
import utils.test_validator.TestValidator;

public class FileUploadTest extends PageObjects {

    private FileUploadPage fileUploadPage;

    @BeforeClass
    private void setUp() {
        useCaseName = "File Upload Test";
        fileUploadPage = new FileUploadPage(driver);
        setUpUseCaseName(useCaseName, "User should be able to upload file in the file upload field");
    }

    @Test(priority = 1)
    public void uploadFileTest() {
        extentReport.logChildTestNameAndDescription(useCaseName, "Upload a file");
        extentReport.testInfo("Verify if file is uploaded");
        actualResult = fileUploadPage.uploadFile();
        extentReport.logStepResult(actualResult);
        TestValidator.validateTest(actualResult);
    }

    @Test(priority = 2)
    public void verifyFileNameAppearsAfterUploadTest() {
        extentReport.logChildTestNameAndDescription(useCaseName, "verify file name after uploading");
        extentReport.testInfo("Verify if file name is displayed after uploading");
        actualResult = fileUploadPage.verifyFileNameDisplayAfterUploading();
        extentReport.logStepResult(actualResult);
        TestValidator.validateTest(actualResult);
    }
}
