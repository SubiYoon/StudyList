package base;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class WebServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(80);
            Socket socket;
            ExecutorService service = new ThreadPoolExecutor(
                    3,
                    5,
                    120,
                    TimeUnit.SECONDS,
                    new LinkedBlockingDeque<>(15)
            );
            while((socket = serverSocket.accept()) != null){
                service.execute(new MyServer(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
