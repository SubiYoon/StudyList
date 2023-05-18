import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Wrapper;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            // 소켓 생성
            ServerSocket serversocket = new ServerSocket(80);
            while (true) {
                // 응답 대기
                Socket socket = serversocket.accept();
                // 받은 응답을 inputStream으로 받아옴
                InputStream inputStream = socket.getInputStream();
                // 받아온 데이터를 byte형식으로 저장
                byte[] bytesArray = inputStream.readAllBytes();

                // Header의 마지막 index
                int headerEndPoint = 0;

                for(int i=0; i<bytesArray.length; i++){
                    if(bytesArray[i]==13 && bytesArray[i+1]==10 && bytesArray[i+2]==13 && bytesArray[i+3]==10){
                        headerEndPoint = i;
                        break;
                    }
                }
                byte[] headerByte = Arrays.copyOf(bytesArray,headerEndPoint);
                byte[] bodyByte = Arrays.copyOfRange(bytesArray, headerEndPoint+4, bytesArray.length);
                // Header추출
                String header = new String(headerByte);
                // Body추출
                String body = new String(bodyByte);
                // Byte 배열 프린트
                System.out.println("<Header>=====================\r\n" +
                        header +
                        "\r\n</Header>=====================\r\n");
                System.out.println("<Body>===========================\r\n" +
                        body +
                        "\r\n</Body>===========================");
                //System.out.println("Charset.defaultCharset(): " + Charset.defaultCharset());

                String[] varFull = header.split(" ");
                if(varFull[0].equals("GET") && varFull[1].contains("?")) {
                    String[] var = varFull[1].split("\\?");
                    String[] var2 = var[1].split("&");
                    System.out.println("==========GET방식!!==========");
                    for(int i=0; i<var2.length; i++){
                        System.out.println(var2[i]);
                    }
                }else if(varFull[0].equals("POST")){
                    System.out.println(body);
                }

                socket = serversocket.accept();

                OutputStream out = socket.getOutputStream();
                String msg = "";

                msg += "<head>";
                msg += "</head>";
                msg += "<script type='text/javascript'>\r\n";
                msg += "    function console_print(){\r\n";
                msg += "        var id = document.getElementById('Id').value;\r\n";
                msg += "        console.log(id);\r\n";
                msg += "    };\r\n";
                msg += "</script>\r\n";
                msg += "<body>\r\n";
                msg += "    <input id='Id' type='text'/>\r\n";
                msg += "    <a id='click' href='#' onclick='console_print()'>click</a>\r\n";
                msg += "    <form method='POST' action='/'>\r\n";
                msg += "        <input type='text' name='sampleIdPost'/>\r\n";
                msg += "        <input type='password' name='samplePwdPost'/>\r\n";
                msg += "        <button>POST_sumbit</button>\r\n";
                msg += "    </form>\r\n";
                msg += "    <form method='GET' action='/'>\r\n";
                msg += "        <input type='text' name='sampleIdGet'/>\r\n";
                msg += "        <input type='text' name='samplePwdGet'/>\r\n";
                msg += "        <button>GET_sumbit</button>\r\n";
                msg += "    </form>\r\n";
                msg += "    <form method='POST' action='/' enctype='multipart/form-data'>\r\n";
                msg += "        <input type='file' name='sampleFile'/>\r\n";
                msg += "        <button>File_sumbit</button>\r\n";
                msg += "    </form>\r\n";
                msg += "</body>\r\n";

                out.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
                out.write(new String("Content-Length:" + msg.getBytes().length + "\r\n").getBytes());
                out.write(new String("Content-Type:text/html,charset=UTF-8\r\n\r\n").getBytes());
                out.write(msg.getBytes());

                //화면 출력
                out.flush();

                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
