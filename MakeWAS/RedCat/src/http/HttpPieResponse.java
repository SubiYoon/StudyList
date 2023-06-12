package http;

import java.io.*;
import java.net.Socket;
import java.nio.channels.FileLockInterruptionException;

public class HttpPieResponse {
    private Socket socket;

    public HttpPieResponse(Socket socket){
        this.socket = socket;
    }

    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }
}
