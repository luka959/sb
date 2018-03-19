package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestProperties {
    private static TestProperties instance = null;
    private final Properties properties;

    private TestProperties() {
        Properties properties = new Properties();
        try (InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(
                        System.getProperty("environment", "application") + ".properties"
                )
        ) {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.properties = properties;
    }

    public static TestProperties getInstance() {
        TestProperties curInstance = instance;
        if (curInstance == null) {
            curInstance = new TestProperties();
            instance = curInstance;
        }
        return curInstance;
    }

    public Properties getProperties() {
        return properties;
    }
}
