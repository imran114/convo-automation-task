package utils.file_reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


// This class reads properties from configuration and email configuration files.
public class PropertiesFileReader {

    // test PropertiesFileReader

    private final Properties emailProperties;
    private final String emailFilePath = "src/test/resources/test_data_files/email_configuration.properties";
    private final static String userDir = System.getProperty("user.dir");

    public PropertiesFileReader() {
        emailProperties = readProperties(emailFilePath);
    }

    public static String returnFilePath(String path) {
        return userDir + path;
    }




    public String getPort() {
        return getProperty(emailProperties, "port");
    }

    public String getHost() {
        return getProperty(emailProperties, "host");
    }

    public String getEmailPassword() {
        return getProperty(emailProperties, "passwordEmail");
    }


    private Properties readProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }


    private static String getProperty(Properties properties, String propertyName) {
        if (properties != null) {
            return properties.getProperty(propertyName);
        }
        return null;
    }


}

