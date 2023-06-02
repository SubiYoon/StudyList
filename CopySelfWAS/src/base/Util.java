package base;

import java.io.File;

public class Util {
    public static String[] folderSearch(String folder){
        String path = "C:\\Users\\Ulim\\Desktop\\Downloads" + folder;
        File file = new File(path);
        String[] fileList = file.list();
        for(int i=0; i<fileList.length; i++){
            String endPoint = new File(path +"/" + fileList[i]).isDirectory()? "/" : "*";
            fileList[i] += endPoint;
        }
        return fileList;
    }
}
