import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(80);
            Socket socket;
            MyServer myServer1;

            while((socket = serverSocket.accept()) != null){
                myServer1 = new MyServer(socket);
                myServer1.start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
