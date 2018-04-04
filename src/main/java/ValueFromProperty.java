import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ekaterina Semenova on 04.04.2018.
 */
public class ValueFromProperty {
    //private final Properties property;
    private Map<String, String> map = new HashMap<>();

    public ValueFromProperty(File directory) {
        List<File> list = new ArrayList<>();
        if (directory.listFiles() != null) {
            list = Arrays.stream(directory.listFiles())
                    .filter(file -> file.getName().endsWith(".properties"))
                    .collect(Collectors.toList());
        }
        for (File file : list) {
            try (InputStream inputStream = new FileInputStream(file)) {
                Properties properties2 = new Properties();
                properties2.load(inputStream);
                    Enumeration<?> enumeration = properties2.propertyNames();
                    if (enumeration != null) {
                        while (enumeration.hasMoreElements()) {
                            String key = enumeration.nextElement().toString();
                            map.put(key, properties2.getProperty(key));

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String , String> getMap(){
        return map;
    }
    public String getValue(String key) {
        return map.get(key);
    }

    public static void main(String[] args) {
        ValueFromProperty valueFromProperty = new ValueFromProperty(new File("src\\main\\java"));
        System.out.println(valueFromProperty.getMap());
        System.out.println(valueFromProperty.getValue("wow"));
        System.out.println(valueFromProperty.getValue("hey"));

    }
}
