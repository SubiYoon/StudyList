package base;

import http.HttpPieRequest;

import java.io.File;

public class Util {
    //TODO: 구현 해야함
    /**
     * 해당 경로(해당 폴더)에 있는 파일들을 검색해주는 메서드 'ls'와 같은 기능
     * 폴더는 맨뒤에 '/'
     * 파일은 맨뒤에 '*'
     * @param path 검색할 폴더 명
     * @return 해당 폴더에 있는 파일들
     */
    public static String[] folderSearch(String path){
        String searchFoler = "C:\\Users\\Ulim\\Desktop\\Downloads" + path;
        File file = new File(searchFoler);
        String[] folderList = file.list();
        for(int i=0; i<folderList.length; i++){
            String endPoint = new File(searchFoler + "\\" + folderList[i]).isDirectory() ? "/" : "*";
            folderList[i] += endPoint;
        }
        return folderList;
    }

    /**
     *
     * @param req
     */
    public static void makeTempFile(HttpPieRequest req){

    }
}
