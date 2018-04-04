import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Ekaterina Semenova on 04.04.2018.
 */
public class ValueFromProperty {
    private final Properties property;
    private Map<String, String> map;

    public ValueFromProperty(File file) {
        Properties property1;
        if (!file.exists()) {
            property1 = null;
        } else {
            try (InputStream inputStream = new FileInputStream(file)) {
                Properties properties = new Properties();
                properties.load(inputStream);
                property1 = properties;
            } catch (IOException exception) {
                exception.printStackTrace();
                property1 = null;
            }
        }
        property = property1;
        initializeProperties();
    }

    public void initializeProperties() {
        map = new HashMap<>();
        if (property != null) {
            Enumeration<?> enumeration = property.propertyNames();
            if (enumeration != null) {
                while (enumeration.hasMoreElements()) {
                    String key = enumeration.nextElement().toString();
                    map.put(key, property.getProperty(key));
                }
            }
        }
    }

    public String getValue(String key) {
        return map.get(key);
    }

    public static void main(String[] args) {
        ValueFromProperty valueFromProperty = new ValueFromProperty(new File("src\\main\\java\\property.properties"));
        System.out.println(valueFromProperty.getValue("wow"));
        System.out.println(valueFromProperty.getValue("hey"));

    }
}
