import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
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
                // 읽어오는 양을 정해놓고 사용함(정적방법 - 메모리 이슈 가능성있음)
//                byte[] buffer = new byte[1024];
//                int read = inputStream.read(buffer);
//                ByteArrayOutputStream by = new ByteArrayOutputStream();
//                by.write(buffer, 0, read);
//                byte[] bytesArray = by.toByteArray();

                // TODO:하나하나 읽어오면서 헤더 부분인지 확인하고 close해주도록 설정
                byte[] bytesArray = new byte[Integer.MAX_VALUE];
                // 헤더의 마지막 Index
                int headerEndPoint = 0;
                // 읽어낼 byte
                int n,point1=0,point2=0,point3=0,point4=0;
                while((n = inputStream.read()) != -1){
                    bytesArray[headerEndPoint] = (byte)n;
                    if(n == 13){
                        point1 = n;
                    }
                    if(point1 == 13 && n == 10){
                        point2 = n;
                    }
                    break;
                }

                for(int i=0; i<bytesArray.length; i++){
                    if(bytesArray[i]==13 && bytesArray[i+1]==10 && bytesArray[i+2]==13 && bytesArray[i+3]==10){
                        headerEndPoint = i;
                        break;
                    }
                }
                // Header와 Body부분의 Btye를 답을 배열
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

                // Charset 확인용 print
                System.out.println("Charset.defaultCharset(): " + Charset.defaultCharset());

                // Header 분석하여 전달값 받기
                String[] varFull = header.split(" ");
                if(varFull[0].equals("GET") && varFull[1].contains("?")) {
                    String[] var = varFull[1].split("\\?");
                    String[] var2 = var[1].split("&");
                    System.out.println("==========GET방식!!==========");
                    for(int i=0; i<var2.length; i++){
                        System.out.println(var2[i]);
                    }
                }else if(varFull[0].equals("POST")){
                  //  System.out.println(body);
                }

                OutputStream out = socket.getOutputStream();
                String msg = "";
                msg += "<script type='text/javascript'>\r\n";
                msg += "    function console_print(){\r\n";
                msg += "        var id = document.getElementById('Id').value;\r\n";
                msg += "        console.log(id);\r\n";
                msg += "    };\r\n";
                msg += "</script>\r\n";
                msg += "<body>\r\n";
                msg += "    <input id='Id' type='text'/>\r\n";
                msg += "    <a id='click' href='#' onclick='console_print()'>click</a></br></br>\r\n";
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
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
