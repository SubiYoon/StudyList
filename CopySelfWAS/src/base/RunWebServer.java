package base;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RunWebServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket;
            ExecutorService executorService = new ThreadPoolExecutor(
                    3,
                    10,
                    120,
                    TimeUnit.SECONDS,
                    new LinkedBlockingDeque<>(5)
            );
            while((socket = serverSocket.accept()) != null){
                executorService.execute(new WebServer(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}