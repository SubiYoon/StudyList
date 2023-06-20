package handler;

import base.AntPathMatcher;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class HttpPieHandlerFactory {
    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * Class의 정보를 가져오는 메서드
     * @param url HTTP에서 가져온 URL 맵핑 주소가 된다.
     * @return 맵핑된 해당 Class
     */
    public static Class getInstance(String url){
        Class obj = null;
        File propertiesFile = new File("/Users/dongsubyoon/Develop/StudyList/MakeWAS/RedCat/src/url.properties");  //Mac Path
        // java -Durl.mapping=c:/users/asldkfj/url.properties
//        String urlMappingPath = System.getProperty("url.mapping");
//        File propertiesFile = new File("C:\\Users\\Ulim\\Desktop\\StudyList\\MakeWAS\\RedCat\\src\\url.properties");    //Window Path
//        File propertiesFile = new File(urlMappingPath);
        Properties properties = new Properties();

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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }
}
