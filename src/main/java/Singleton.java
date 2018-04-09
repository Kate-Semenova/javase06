import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ekaterina Semenova on 09.04.2018.
 */
public class Singleton {
    private static Singleton instance;
    private Map<String, String> map = new HashMap<>();
    private final File DIRECTORY;

    private Singleton(File directory){
        DIRECTORY = directory;
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

    public static Singleton getInstance(File directory){
        if (instance == null){
            instance = new Singleton(directory);

        }
           return instance;
    }

    public String getValueByKey(String key){
        return map.get(key);
    }
    public String getDirectoryName(){
        return DIRECTORY.getName();
    }


    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance(new File("src\\main\\java"));
        System.out.println(singleton.getValueByKey("wow"));
    }
}
