package http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MyHttpResponse {
    private Socket socket;

    public MyHttpResponse (Socket socket){
        this.socket = socket;
    }

    /**
     * OutputStream 객체를 생성하여 반환하는 메서드
     * @return OutputStream객체를 반환
     * @throws IOException
     */
    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }
}
