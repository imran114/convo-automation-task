package utils.listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import utils.email.EmailSender;
import utils.test_merge.ClearFolderAndMergeReport;

public class SuiteListener implements ISuiteListener {

    ClearFolderAndMergeReport clearFolderAndMergeReport = new ClearFolderAndMergeReport();

    @Override
    public void onStart(ISuite suite) {

    }

    @Override
    public void onFinish(ISuite suite) {
        EmailSender emailSender = new EmailSender();
        System.out.println("email is started.");
        String subject = "Convo Automation Report";
        String recipient = "email";
        System.out.println("email sent successfully.");
        emailSender.sendEmail(recipient, subject);
        clearFolderAndMergeReport.mergeReport();
        clearFolderAndMergeReport.clearFolder();
    }




}
