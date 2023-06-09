package http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;

public class HttpPieRequest {
    private Socket socket;
    public static Map<String, String> httpHeader;
    public static Map<String, String> param;

    public HttpPieRequest(Socket socket){
        this.socket = socket;
    }

    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    public void setHttpHeader(Map<String, String> map){
        this.httpHeader = map;
    }

    public static void setParam(Map<String, String> param) {
        HttpPieRequest.param = param;
    }

    //TODO: 파일저장 메서드 작성 필요함
    /**
     * 파일을 지정한 위치에 저장하는 메서드
     * @param path 파일을 저장할 위치를 지정
     * @param varName HTML태그에 있는 파일의 변수명
     */
    public void fileSave(String path, String varName){

    }
}
