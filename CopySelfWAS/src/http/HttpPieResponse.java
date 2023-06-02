package http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class HttpPieResponse {
    Socket socket;

    /**
     * 해당 socket을 가지고 객체를 생성하는 생성자
     * @param socket 사용할 소켓
     */
    public HttpPieResponse(Socket socket){
        this.socket = socket;
    }


    /**
     * 생성한 소켓의 OutputStream을 생성
     * @return OutputStream
     * @throws IOException
     */
    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }
}
