package base;

import handler.HttpHandlerFactory;
import handler.MyHttpHandler;
import http.MyHttpRequest;
import http.MyHttpResponse;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyServer implements Runnable {
    private Socket socket;
    MyHttpRequest req;
    MyHttpResponse res;

    public MyServer(Socket socket) {
        this.socket = socket;
        req = new MyHttpRequest(socket);
        res = new MyHttpResponse(socket);
    }

    @Override
    public void run() {
        System.out.println("============================\r\n" +
                Thread.currentThread().getName() +
                "\r\n============================");
        try {
            InputStream inputStream = req.getInutStream();
            OutputStream outputStream = res.getOutputStream();
            // HTTP의 정보를 담을 byte 배열
            byte[] totalReadByte = new byte[8000];    //Tomcat최대 제한 길이가 8K(Default) - 48k(최대)
            // 헤더의 마지막 Index
            int headerEndPoint = 0;
            // 읽어낼 byte
            int n;
            // Header부분을 탐색할 point
            int point1 = 0, point2 = 0, point3 = 0;
            // boundary부분을 탐색할 check
            int check1 = 0, check2 = 0, check3 = 0, check4 = 0;
            byte[] boundaryByte = new byte[100];
            boundaryByte[0] = 45;
            boundaryByte[1] = 45;
            int off = 6;
            boolean boundaryCheck = false;
            // Header부분 추출
            while ((n = inputStream.read()) != -1) {
                totalReadByte[headerEndPoint++] = (byte) n;
                // Header부분 추출하면 break
                if (point1 == 13 && point2 == 10 && point3 == 13 && n == 10) {
                    break;
                } else if (point1 == 13 && point2 == 10 && n == 13) {
                    point3 = 13;
                } else if (point1 == 13 && n == 10) {
                    point2 = n;
                } else if (n == 13) {
                    point1 = 13;
                } else {
                    point1 = 0;
                    point2 = 0;
                    point3 = 0;
                }
                if (!boundaryCheck) {
                    if (check1 == 45 && check2 == 45 && check3 == 45 && check4 == 45) {
                        boundaryByte[2] = 45;
                        boundaryByte[3] = 45;
                        boundaryByte[4] = 45;
                        boundaryByte[5] = 45;
                        if (n == 13) {
                            check1 = 0;
                            check2 = 0;
                            check3 = 0;
                            check4 = 0;
                            off = 6;
                            boundaryCheck = true;
                        } else {
                            boundaryByte[off++] = (byte) n;
                        }
                    } else {
                        if (check1 == 45 && check2 == 45 && check3 == 45 && n == 45) {
                            check4 = 45;
                        } else if (check1 == 45 && check2 == 45 && n == 45) {
                            check3 = 45;
                        } else if (check1 == 45 && n == 45) {
                            check2 = 45;
                        } else if (n == 45) {
                            check1 = 45;
                        } else {
                            check1 = 0;
                            check2 = 0;
                            check3 = 0;
                            check4 = 0;
                        }
                    }
                }
            }
            // header String으로 변환
            String decodeingBeforeHeader = new String(Arrays.copyOf(totalReadByte, headerEndPoint));
            String header = URLDecoder.decode(decodeingBeforeHeader, "UTF-8");

            if (header.length() <= 10) {
                // Header 프린트
                System.out.println("<Header>=====================\r\n" +
                        "살아있니??\r\n" +
                        "</Header>=====================");
            } else {
                // Header 정보 배열로 변경
                String[] headerInfo = header.split("\r\n");
                // Header정보를 담을 Map
                Map<String, String> headerData = new LinkedHashMap<String, String>();
                // 첫번째 Row의 정보를 공백을 기준으로 자른 후 Map에 담음
                String[] headerFirstRowInfo = headerInfo[0].split(" ");
                headerData.put("Method", headerFirstRowInfo[0]);
                headerData.put("URL", headerFirstRowInfo[1]);
                headerData.put("Protocol", headerFirstRowInfo[2]);
                // 두번째 이후의 정보를 ": "을 기준으로 자른 후 Map에 담음
                for (int i = 1; i < headerInfo.length; i++) {
                    if (headerInfo[i].contains(": ")) {
                        String[] headerRowInfo = headerInfo[i].split(": ");
                        headerData.put(headerRowInfo[0], headerRowInfo[1]);
                        if (headerRowInfo[1].contains("boundary")) {
                            String boundary = "--" + headerRowInfo[1].substring(headerRowInfo[1].indexOf("boundary=") + 9, headerRowInfo[1].length());
                            headerData.put("boundaryName", boundary);
                        }
                    }
                }
                headerData.put("Charset", String.valueOf(Charset.defaultCharset()));
                // header파싱해서 request에 넣기
                req.headerParsing(headerData);
                req.setBoundaryByte(boundaryByte);

                if (headerData.get("Method").equals("GET")) {
                    MyHttpHandler handler = (MyHttpHandler) HttpHandlerFactory.getInstance(headerData.get("URL")).getConstructor().newInstance();
                    handler.getHanlde(req, res);
                } else {
                    MyHttpHandler handler = (MyHttpHandler) HttpHandlerFactory.getInstance(headerData.get("URL")).getConstructor().newInstance();
                    handler.postHandle(req, res);
                }

                //화면 출력
                outputStream.flush();
                inputStream.close();
                outputStream.close();
                socket.close();
            }
            //}
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
