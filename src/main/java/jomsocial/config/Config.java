package jomsocial.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config extends Properties {

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Config instance = new Config();

    public static String get(String key) {
        return instance.getProperty(key);
    }

    public static String get(String key, Object defaultValue) {
        return instance.getProperty(key, String.valueOf(defaultValue));
    }

    public static Long get(String key, Long defaultValue) {
        return (instance.contains(key)) ? Long.parseLong(instance.getProperty(key)) : defaultValue;
    }

    private Config() {
        try {
            this.load(new FileReader(".env"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
