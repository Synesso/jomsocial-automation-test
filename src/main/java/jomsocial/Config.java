package jomsocial;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config extends Properties {

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Config instance = new Config();

    public static String get(String key) {
        return instance.getProperty(key);
    }

    private Config() {
        try {
            this.load(new FileReader(".env"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
