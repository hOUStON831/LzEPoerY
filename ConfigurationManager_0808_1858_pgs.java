// 代码生成时间: 2025-08-08 18:58:56
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigurationManager {

    // The path to the configuration file
    private String configFilePath;

    // Constructor
    public ConfigurationManager(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    /**
     * Load the configuration file into a Properties object.
     *
     * @return A Map representing the configuration data.
     * @throws IOException If there is an error reading the file.
     */
    public Map<String, String> loadConfiguration() throws IOException {
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get(configFilePath)));
            // Convert Properties to Map
            Map<String, String> configMap = new HashMap<>();
            for (String key : properties.stringPropertyNames()) {
                configMap.put(key, properties.getProperty(key));
            }
            return configMap;
        } catch (IOException e) {
            throw new IOException("Error loading configuration file: " + e.getMessage(), e);
        }
    }

    /**
     * Save the configuration data to the file.
     *
     * @param configData The Map containing the configuration data to save.
     * @throws IOException If there is an error writing to the file.
     */
    public void saveConfiguration(Map<String, String> configData) throws IOException {
        Properties properties = new Properties();
        // Convert Map to Properties
        for (Map.Entry<String, String> entry : configData.entrySet()) {
            properties.setProperty(entry.getKey(), entry.getValue());
        }
        try {
            properties.store(Files.newOutputStream(Paths.get(configFilePath)), "Configuration Data");
        } catch (IOException e) {
            throw new IOException("Error saving configuration file: " + e.getMessage(), e);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        try {
            ConfigurationManager configManager = new ConfigurationManager("config.properties");
            Map<String, String> config = configManager.loadConfiguration();
            System.out.println("Loaded configuration: " + config);

            // Update configuration
            config.put("newKey", "newValue");
            configManager.saveConfiguration(config);
            System.out.println("Configuration saved.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
