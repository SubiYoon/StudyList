package http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class HttpPieResponse {
    private Socket socket;

    public HttpPieResponse(Socket socket){
        this.socket = socket;
    }

    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }
}
