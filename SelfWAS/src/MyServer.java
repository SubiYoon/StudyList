import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyServer extends Thread{
    Socket socket;
    public MyServer(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        try {
            // 소켓 생성
//            ServerSocket serversocket = new ServerSocket(80);
           //while (true) {
                // 응답 대기
               // Socket socket = serversocket.accept();
                // 받은 응답을 inputStream으로 받아옴
                InputStream inputStream = socket.getInputStream();
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
                // Header 프린트
                System.out.println("<Header>=====================\r\n" +
                        header +
                        "\r\n</Header>=====================");
                if (header.length() > 10) {
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

                    // Body의 데이터를 담을 Map
                    Map<String, String> bodyData = new LinkedHashMap<String, String>();
                    // Get방식일 경우 Body 정보 담기
                    if (headerData.get("Method").equals("GET") && headerData.get("URL").contains("?")) {
                        String[] values = headerData.get("URL").replaceFirst("/\\?", "").split("&");
                        Util.parameterPut(values, bodyData);
                    }
                    // multipart/form-data boundary의 body의 시작,끝 체크를 위한 index
                    int boundaryBodyStartCheckPoint = 0;
                    // Post방식을 경우 Body 정보 담기
                    if (headerData.get("Method").equals("POST")) {
                        // header에서 뽑아낸 body(Content의 길이)
                        int contentLength = 0;
                        if (headerData.get("Content-Length") != null && !headerData.get("Content-Length").isBlank()) {
                            contentLength = Integer.parseInt(headerData.get("Content-Length"));
                        }
                        // Multipart Related MIME 타입 처리
                        if (headerData.get("Content-Type").contains("application/x-www-form-urlencoded") || headerData.get("Content-Type").contains("Multipart/related")) {
                            byte[] bodyByte = new byte[contentLength];
                            for (int i = 0; i < contentLength; i++) {
                                int b = inputStream.read();
                                bodyByte[i] = (byte) b;
                            }
                            String body = new String(bodyByte);
                            String[] values = body.split("&");
                            // String으로 해석한 데이터를 Map에 넣어줌
                            Util.parameterPut(values, bodyData);

                        }
                        // Multipart/form-data 타입 처리
                        else if (headerData.get("Content-Type").contains("form-data")) {
                            // 바운더리 이름을 바이트코드로 저장하기 위한 배열
                            byte[] boundaryNameByte = Arrays.copyOfRange(boundaryByte, 0, headerData.get("boundaryName").length());
                            byte[] bodyByte;
                            byte[] splitPoint;
                            byte[] buff = new byte[contentLength*2];
                            int boundaryStartPoint = 0;
                            int boundaryEndPoint = 0;
                            int len = 4096 < contentLength ? 4096 : contentLength;
                            int off2 = 0;
                            int num;
                            while ((num =inputStream.read(buff, off2, len)) != -1) {
                                off2 += num;
                                if(num < len){
                                    break;
                                }
                            }
                            bodyByte = Arrays.copyOf(buff, off2);
                            boolean charCheck = false;
                            for (int i = 0; i < contentLength; i++) {
                                if (bodyByte[i] == 45 && bodyByte[i+1] == 45 && bodyByte[i+2] == 45 && bodyByte[i+3] == 45) {
                                    charCheck = true;
                                }
                                if (charCheck && i >= boundaryNameByte.length) {
                                    splitPoint = Arrays.copyOfRange(bodyByte, i - boundaryNameByte.length, i);
                                    //바운더리 이름에 걸리면 작업 시작
                                    if (Arrays.equals(splitPoint, boundaryNameByte)) {
                                        if (boundaryStartPoint == 0) {
                                            boundaryStartPoint = i + 1;
                                            charCheck = false;
                                        } else if (boundaryEndPoint == 0) {
                                            boundaryEndPoint = i - boundaryNameByte.length;
                                            //같은거 i~from 기준으로 잘라서 카피
                                            byte[] boundaryData = Arrays.copyOfRange(bodyByte, boundaryStartPoint, boundaryEndPoint);
                                            Map<String, String> boundaryHeaderData = new LinkedHashMap<String, String>();
                                            for (int j = 0; j < boundaryData.length; j++) {
                                                //자른 배열들 각각 \r\n\r\n 기준으로 헤더와 바디를 나눔
                                                if (boundaryData[j] == 13 && boundaryData[j + 1] == 10 && boundaryData[j + 2] == 13 && boundaryData[j + 3] == 10) {
                                                    byte[] boundaryHeader = Arrays.copyOf(boundaryData, j + 4);
                                                    boundaryBodyStartCheckPoint = j + 4;    // boundaryBody 시작부분
                                                    String parseBoundaryHeader = new String(boundaryHeader);
                                                    System.out.println("=============boundaryHeader=============\r\n" +
                                                            parseBoundaryHeader +
                                                            "=============boundaryHeader=============\r\n"
                                                    );
                                                    // 바운더리 헤더 부분 formData Map에 데이터 삽입
                                                    String[] strs = parseBoundaryHeader.split("\r\n");
                                                    for (int k = 0; k < strs.length; k++) {
                                                        if (k == 0) {
                                                            String[] str2 = strs[k].split("; ");
                                                            for (int t = 1; t < str2.length; t++) {
                                                                String[] str3 = str2[t].split("=");
                                                                boundaryHeaderData.put(str3[0], str3[1].substring(1, str3[1].length() - 1));
                                                            }
                                                        } else {
                                                            String[] strs2 = strs[k].split(": ");
                                                            boundaryHeaderData.put(strs2[0], strs2[1]);
                                                        }
                                                    }
                                                    break;
                                                }
                                            }
                                            //바이트 쓰기
                                            if (boundaryHeaderData.get("filename") != null
                                                    && !boundaryHeaderData.get("filename").equals("")
                                                    && !boundaryHeaderData.get("filename").isEmpty()
                                                    && !boundaryHeaderData.get("filename").isBlank()) {
                                                byte[] boundaryBodyData = Arrays.copyOfRange(boundaryData, boundaryBodyStartCheckPoint, boundaryEndPoint);
                                                File outFile = new File("/Users/dongsubyoon/Downloads/ServerRoot/download/" + boundaryHeaderData.get("filename"));  //Mac Path
//                                                File outFile = new File("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\download\\" + boundaryHeaderData.get("filename"));  //Window Path
                                                FileOutputStream outputStream = new FileOutputStream(outFile);
                                                outputStream.write(boundaryBodyData);
                                                outputStream.close();
                                                //새로운 바운더리 데이터 얻기 위해 초기화
                                                boundaryStartPoint = i + 1; //현재 걸려있는 바운더리 이름 기준으로 그 다음번째 시작부분
                                                boundaryEndPoint = 0;
                                                charCheck = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    // Body정보 출력
                    System.out.println("<Body>===========================\r\n" +
                            bodyData +
                            "\r\n</Body>===========================");

                    // Charset 확인용 print
                    System.out.println("Charset.defaultCharset(): " + Charset.defaultCharset());

                    OutputStream outputStream = socket.getOutputStream();
                    String msg = "";
                    String serverRoot = "/Users/dongsubyoon/Downloads"; //Mac
//                    String serverRoot = "C:\\Users\\Ulim\\Desktop\\Downloads"; //Window
                    if (headerData.get("Method").equals("GET") && headerData.get("URL").contains("serverRoot")) {
                        File file = new File(serverRoot + headerData.get("URL"));   //Mac
//                        File file = new File(serverRoot + headerData.get("URL").replace("/", "\\"));    //Window
                        if (!file.exists()) {
                            outputStream.write(new String("HTTP/1.1 404 Not Found\r\n").getBytes());
                            socket.close();
                            //continue;
                        }
                        boolean isFile = file.isFile();
                        if (isFile) {
                            Path path = Paths.get(serverRoot + headerData.get("URL"));
                            long fileSize = Files.size(path);
                            outputStream.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
                            outputStream.write(new String("Content-Length:" + fileSize + "\r\n").getBytes());
                            // 파일이 pdf 형식일 시 브라우저에서 뷰어 실행
                            if (headerData.get("URL").contains(".pdf")) {
                                outputStream.write(new String("Content-Type:application/pdf\r\n\r\n").getBytes());

                                msg += "<meta charset='UTF-8'>";
                                msg += "<title>pdf 뷰어 테스트</title>";
                                msg += "<body>";
                                outputStream.write(msg.getBytes());
                                msg = "";
                                FileInputStream fis = new FileInputStream(serverRoot + headerData.get("URL"));
                                byte[] fileBytes = fis.readAllBytes();
                                outputStream.write(fileBytes);
                                msg += "</body>";
                                outputStream.write(msg.getBytes());
                            } else {
                                outputStream.write(new String("Content-Type:application/jpeg\r\n\r\n").getBytes());

                                FileInputStream fis = new FileInputStream(serverRoot + headerData.get("URL"));
                                byte[] fileBytes = fis.readAllBytes();
                                outputStream.write(fileBytes);
                            }
                        } else {
                            // directory
                            String[] folderList = Util.searchAllFolderList(headerData.get("URL"));   //Mac
//                            String[] folderList = list.searchAllFolderList(headerData.get("URL").replace("/", "\\"));   //Window
                            int cutURL = headerData.get("URL").substring(0, headerData.get("URL").length() - 1).lastIndexOf("/");
                            msg += "<meta charset='utf-8'>\r\n";
                            msg += "<link rel='icon' href='data:,'/>\r\n";  //favicon.ico 를 로드하지 않게 설정
                            msg += "<body>\r\n";
                            msg += "<h3>마지막에 '*'가 붙어있으면 파일을 다운로드 합니다.</h1>\r\n";
                            msg += "<h3>마지막에 'pdf' 파일은 뷰어를 실행시킵니다.</h1>\r\n";
                            msg += "    <ul>\r\n";
                            msg += "        <li><a href='" + headerData.get("URL").substring(0, cutURL + 1) + "'>../</a></li>\r\n";
                            for (int i = 0; i < folderList.length; i++) {

                                if (folderList[i].contains(".pdf")) {
                                    msg += "        <li><a href='" + folderList[i].substring(0, folderList[i].length() - 1) + "'>" + folderList[i].substring(0, folderList[i].length() - 1) + "</a></li>\r\n";
                                } else if (folderList[i].substring(folderList[i].length() - 1, folderList[i].length()).equals("*")) {
                                    msg += "        <li><a href='" + folderList[i].substring(0, folderList[i].length() - 1) + "' download>" + folderList[i] + "</a></li>\r\n";
                                } else if (folderList[i].substring(folderList[i].length() - 1, folderList[i].length()).equals("/")) {
                                    msg += "        <li><a href='" + folderList[i] + "'>" + folderList[i] + "</a></li>\r\n";
                                }
                            }
                            msg += "    </ul>\r\n";
                            msg += "    <a href='/'>처음으로 돌아가기</a>";
                            msg += "</body>\r\n";
                            outputStream.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
                            outputStream.write(new String("Content-Length:" + msg.getBytes().length + "\r\n").getBytes());
                            outputStream.write(new String("Content-Type:text/html\r\n\r\n").getBytes());
                        }


                    } else if (headerData.get("Method").equals("GET") && headerData.get("URL").contains("serverRoot")) {

                    } else {
                        msg += "<meta charset='utf-8'>\r\n";
                        msg += "<link rel='icon' href='data:,'/>\r\n";  //favicon.ico 를 로드하지 않게 설정
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
                        msg += "        <input type='file' name='sampleFile1'/>\r\n";
                        msg += "        <input type='file' name='sampleFile2'/>\r\n";
                        msg += "        <input type='file' name='sampleFile3'/>\r\n";
                        msg += "        <button>File_sumbit</button>\r\n";
                        msg += "    </form>\r\n";
                        msg += "    <a href='/serverRoot/'>폴더 탐색</a>\r\n";
                        msg += "</body>\r\n";
                        outputStream.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
                        outputStream.write(new String("Content-Length:" + msg.getBytes().length + "\r\n").getBytes());
                        outputStream.write(new String("Content-Type:text/html\r\n\r\n").getBytes());
                    }
                    outputStream.write(msg.getBytes());

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
