package http;

import java.io.*;
import java.net.Socket;
import java.nio.channels.FileLockInterruptionException;

public class HttpPieResponse {
    private Socket socket;
    private byte[] status;
    private byte[] contentType;
    private byte[] massage;
    private File file;

    public HttpPieResponse(Socket socket) {
        this.socket = socket;
    }

    public void setStatus(int status) {
        byte[] s2b = null;
        if (status == 200) {
            s2b = new String("HTTP/1.1 200 OK\r\n").getBytes();
        } else if (status == 404) {
            s2b = new String("HTTP/1.1 404 NotFound\r\n").getBytes();
        }
        this.status = s2b;
    }

    public void setContentType(String contentType) {
        byte[] s2b = new String("Content-Type:" + contentType + "\r\n\r\n").getBytes();
        this.contentType = s2b;
    }

    public void setMassage(String massage) {
        byte[] s2b = new String(massage).getBytes();
        this.massage = s2b;
    }

    public void setFile(File file) {
        this.file = file;
    }

    /**
     * 작성한 HTTP 헤더 및 Body를 클라이언트에게 전송
     * @throws IOException
     */
    public void flush() throws IOException {
        OutputStream out = socket.getOutputStream();

        out.write(status);
        if (file != null) {
            FileInputStream fis = new FileInputStream(file);
            byte[] fileByte = fis.readAllBytes();

            out.write(new String("Content-Length:" + file.length() +"\r\n").getBytes());
            out.write(contentType);
            out.write(fileByte);
        } else {
            out.write(new String("Content-Length:" + massage.length + "\r\n").getBytes());
            out.write(contentType);
            out.write(massage);
        }

    }

    /**
     * 소켓의 OutputStream 인스턴스를 reutrn하는 메서드
     *
     * @return OutputStream 객체
     * @throws IOException
     */
    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }
}
