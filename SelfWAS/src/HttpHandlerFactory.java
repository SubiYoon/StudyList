import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class HttpHandlerFactory {
    private static AntPathMatcher antPathMatcher = new AntPathMatcher();
    public static Class getInstance(String url){
        String propertiesPath = "C:\\Users\\Ulim\\Desktop\\StudyList\\SelfWAS\\src\\url.properties";
        Properties properties = new Properties();
        File propertiesFile = new File(propertiesPath);
        Class obj = null;
        try {
            Reader reader = new FileReader(propertiesFile);
            properties.load(reader);
            Enumeration<Object> keys = properties.keys();
            while (keys.hasMoreElements()) {
                String k = (String)keys.nextElement();
                if (antPathMatcher.match(k, url)) {
                    obj = Class.forName(properties.getProperty(k));
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }
}
