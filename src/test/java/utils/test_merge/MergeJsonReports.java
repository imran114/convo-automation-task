package utils.test_merge;

import com.google.gson.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class MergeJsonReports {

    public static void mergeJson() {
        String reportDir = "src/test/resources/reports/json_files";
        String mergedJsonPath = "src/test/resources/reports/merged_report.json";

        mergeJsonReports(reportDir, mergedJsonPath);
    }

    public static void mergeJsonReports(String directoryPath, String outputFilePath) {
        File jsonDirectory = new File(directoryPath);
        HashSet<String> uniqueTests = new HashSet<>();
        JsonArray mergedTestArray = new JsonArray();

        if (jsonDirectory.exists() && jsonDirectory.isDirectory()) {
            File[] jsonFiles = jsonDirectory.listFiles();
            if (jsonFiles != null) {
                for (File jsonFile : jsonFiles) {
                    if (jsonFile.isFile() && jsonFile.getName().endsWith(".json")) {
                        try (FileReader reader = new FileReader(jsonFile)) {
                            JsonArray testArray = JsonParser.parseReader(reader).getAsJsonArray();

                            for (JsonElement testElement : testArray) {
                                JsonObject testObject = testElement.getAsJsonObject();

                                // Unique identifier based on test name + timestamp
                                String testName = testObject.get("name").getAsString().trim();
                                String testTime = testObject.get("startTime").getAsString();
                                String uniqueIdentifier = testName + "_" + testTime;

                                // Skip duplicate tests
                                if (uniqueTests.contains(uniqueIdentifier)) {
                                    continue;
                                }

                                uniqueTests.add(uniqueIdentifier);
                                mergedTestArray.add(testObject); // Add unique test case
                            }

                        } catch (IOException e) {
                            System.err.println("Error reading JSON file: " + jsonFile.getName());
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        // Save the merged JSON to a file
        try (FileWriter writer = new FileWriter(outputFilePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(mergedTestArray, writer);
            System.out.println("Merged JSON saved to: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error writing merged JSON file.");
            e.printStackTrace();
        }
    }
}
