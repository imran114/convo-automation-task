package utils.email;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.file_reader.PropertiesFileReader;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class EmailSender {
    private static final Logger logger = LogManager.getLogger(EmailSender.class);

    private final PropertiesFileReader fileReader;

    private final String[] cc = {


    };

    public EmailSender() {
        fileReader = new PropertiesFileReader();
    }

    public void sendEmail(String recipients, String subject) {
        logger.info("Preparing to send email with subject: " + subject);

        Properties properties = new Properties();
        String host = fileReader.getHost();
        String port = fileReader.getPort();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        System.out.println("Properties set.");

        String email = fileReader.getRecipientEmail();
        String pass = fileReader.getRecipientEmailPassword();

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pass);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
            System.out.println("Recipient added.");

            Address[] ccAddresses = new Address[cc.length];
            for (int i = 0; i < cc.length; i++) {
                ccAddresses[i] = new InternetAddress(cc[i]);
            }
            message.addRecipients(Message.RecipientType.CC, ccAddresses);

            message.setSubject(subject);
            System.out.println("Subject added.");

            Multipart multipart = new MimeMultipart();
            MimeBodyPart attachmentPart = new MimeBodyPart();
            MimeBodyPart textPart = new MimeBodyPart();

            System.out.println("Message body added.");
            String filePath = "src/test/resources/test_data_files/email_body.README";
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
            String currentDate = new SimpleDateFormat("yyMMdd").format(new Date()).toLowerCase();

            String absoluteReportPath = System.getProperty("user.dir") + "/src/test/resources/reports/convo_task_" + currentDate + "_report.html";
            File file = new File(absoluteReportPath);

            if (!file.exists()) {
                throw new FileNotFoundException("Report file not found: " + absoluteReportPath);
            }

            attachmentPart.attachFile(file);
            textPart.setText(fileContent);
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);
            System.out.println("Report attached.");

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email sent successfully.");
            logger.info("Email sent successfully.");
        } catch (MessagingException | IOException e) {
            logger.error("Failed to send email: ", e);
        }
    }

    private String getReportName(){
        String currentDate = new SimpleDateFormat("yyMMdd").format(new Date());
        // Construct dynamic report name according to the current date
        String reportFileName = "convo_automation_report" + currentDate + "-.html";
        return  "src/test/resources/reports/" + reportFileName;
    }

}
