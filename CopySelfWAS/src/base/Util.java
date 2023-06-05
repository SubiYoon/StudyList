package base;

import http.HttpPieRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Util {
    /**
     * url정보를 토대로 해당 폴더의 데이터를 Searching하는 메서드
     * terminal의 ls기능과 같은 기능
     * 해당 폴더의 하위 목록에 마지막에 붙은 구분자에 따라 폴더인지 파일인지 구분
     * '/' - 폴더
     * '*' - 파일
     * @param folder 해당 url정보를 토대로 local의 폴더를 탐색하기 위한 변수
     * @return 해당 폴더에 있는 list를 String 배열에 담아 return
     */
    public static String[] folderSearch(String folder){
        String path = "/Users/dongsubyoon/Downloads" + folder;   //Mac Path
//        String path = "C:\\Users\\Ulim\\Desktop\\Downloads" + folder;   //Window Path
        File file = new File(path);
        String[] fileList = file.list();
        for(int i=0; i<fileList.length; i++){
            String endPoint = new File(path +"/" + fileList[i]).isDirectory()? "/" : "*";
            fileList[i] += endPoint;
        }
        return fileList;
    }

    /**
     * Post방식의 데이터가 넘어왔을 때 해당 데이터가 form-data의 파일형태 일경우 임시 파일 생성
     * @param req HTTP의 body데이터를 읽어들일 HttpPieRequrst
     * @throws IOException
     */
    public static void makeTempFile(HttpPieRequest req) throws IOException {
        int n;
        File tempFile = new File("/Users/dongsubyoon/Downloads/ServerRoot/temp/" + req.getBoundaryName() + ".tmp");    // Mac Temp Path
        FileOutputStream fos = new FileOutputStream(tempFile, true);
        byte[] bodyAllByte = new byte[4096];
        while((n = req.getInputStream().read(bodyAllByte, 0, 4096)) != -1){
            fos.write(bodyAllByte);
            if(req.getInputStream().available() <= 4096){
                req.getInputStream().read(bodyAllByte, 0, 4096);
                fos.write(bodyAllByte);
                break;
            }
        }
        //TODO: 파일로 만든 tmp파일을 boundary를 기준으로 나눔

        //TODO: 나눈 byte[]의 header를 파싱해서 filename이 비어있지 않으면 다시 임시파일 생성

        //TODO: 임시파일이 모두 완성 되었으면 기존 original tmp파일 삭제

        //TODO: HttpPieRequest에 파일을 읽는 메서드 작성
    }
}
