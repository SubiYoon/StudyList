package http;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class HttpPieRequest {
    private Socket socket;
    public static Map<String, String> httpHeader;
    public static Map<String, String> param;

    public static Map<String, String> file;

    public HttpPieRequest(Socket socket) {
        this.socket = socket;
    }

    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    public void setHttpHeader(Map<String, String> map) {
        this.httpHeader = map;
    }

    public static void setFile(Map<String, String> file) {
        HttpPieRequest.file = file;
    }

    public void setParam(Map<String, String> param) {
        HttpPieRequest.param = param;
    }

    /**
     * 파일을 지정한 위치에 저장하는 메서드
     *
     * @param path    파일을 저장할 위치를 지정
     * @param varName HTML태그에 있는 파일의 변수명
     */
    public boolean fileSave(String path, String varName) {
        boolean isSave = false;
        File tempDir = new File("/Users/ABCD/Develop/StudyList/MakeWAS/serverRoot/temp/");
        if(!tempDir.exists()){
            tempDir.mkdirs();
        }
        File file = new File("/Users/ABCD/Develop/StudyList/MakeWAS/serverRoot/temp/" + varName + ".tmp"); //Mac Path
//        File file = new File("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\temp\\" + varName + ".tmp"); //Window Path
        if(file.exists()) {
            File resultDir = new File(path);
            if(!resultDir.exists()){
                resultDir.mkdirs();
            }
            File outPut = new File(path + HttpPieRequest.file.get(varName)); //Mac Path
//            File outPut = new File(path + HttpPieRequest.file.get(varName)); //Window Path
            try (FileInputStream fis = new FileInputStream(file);
                 FileOutputStream fos = new FileOutputStream(outPut);) {
                fos.write(fis.readAllBytes());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                file.delete();
            }
        }
        return isSave;
    }
}
