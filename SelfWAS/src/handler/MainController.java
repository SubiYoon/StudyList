package handler;

import base.Util;
import http.MyHttpRequest;
import http.MyHttpResponse;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainController implements MyHttpHandler {
    @Override
    public void getHanlde(MyHttpRequest req, MyHttpResponse res) throws IOException {
        // header정보 출력
        System.out.println(req.toString());

        OutputStream outputStream = res.getOutputStream();

        // Body의 데이터를 담을 Map
        Map<String, String> bodyData = new LinkedHashMap<String, String>();

        // Get방식일 경우 Body 정보 담기
        if (req.getMethod().equals("GET") && req.getUrl().contains("?")) {
            String[] values = req.getUrl().replaceFirst("/\\?", "").split("&");
            Util.parameterPut(values, bodyData);
        }


        String msg = "";

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
        outputStream.write(msg.getBytes());

        // body정보 출력
        System.out.println(bodyData);
    }

    @Override
    public void postHandle(MyHttpRequest req, MyHttpResponse res) throws IOException {
        InputStream inputStream = req.getInutStream();
        OutputStream outputStream = res.getOutputStream();

        // header정보 출력
        System.out.println(req.toString());
        // Body의 데이터를 담을 Map
        Map<String, String> bodyData = new LinkedHashMap<String, String>();

        // stream을 읽어낼 변수
        int n;
        // multipart/form-data boundary의 body의 시작,끝 체크를 위한 index
        int boundaryBodyStartCheckPoint = 0;
        // header에서 뽑아낸 body(Content의 길이)
        int contentLength = 0;
        if (req.getContentLength() != null && !req.getContentLength().isBlank()) {
            contentLength = Integer.parseInt(req.getContentLength());
        }
        // Multipart Related MIME 타입 처리
        if (req.getContentType().contains("application/x-www-form-urlencoded") || req.getContentType().contains("Multipart/related")) {
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
        //TODO: 나중에 파일외 다른것도 같이 보내게 되면 수정 필요
        else if(contentLength == 497){
            //스킵
        }
        // Multipart/form-data 타입 처리
        else if (req.getContentType().contains("form-data")) {
            // 바운더리 이름을 바이트코드로 저장하기 위한 배열
            byte[] boundaryNameByte = Arrays.copyOfRange(req.getBoundaryByte(), 0, req.getBoundaryName().length());
            byte[] splitPoint;
            byte[] buff = new byte[contentLength + 5000];
            int boundaryStartPoint = 0;
            int boundaryEndPoint = 0;
            int len = 4096 < contentLength ? 4096 : contentLength;
            int off2 = 0;

            while ((n = inputStream.read(buff, off2, len)) != -1) {
                off2 += n;
                if (n < len) {
                    break;
                }
            }
            // 100메가 초과하면 body부분을 tmp파일로 임시 저장
            if (off2 > 100000000) {
//                File tempFile = new File("/Users/dongsubyoon/Downloads/ServerRoot/temp/" + req.getBoundaryName() + ".tmp");     //Mac
                File tempFile = new File("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\temp\\" + req.getBoundaryName() + ".tmp");     //Window
                FileOutputStream out = new FileOutputStream(tempFile);
                out.write(buff, 0, off2);
                out.close();
                // tempFile을 읽을 int 변수
                boolean headerStart = true;
                // tempFile의 boundary header부분을 추출할 변수
                int bnbCehck = 0;
                // boundaryHead 부분을 추출할 배열
                byte[] boundaryHead;
                // 바운더리 헤더 배열 인덱스
                int bhOff = 0;
                // 바운더리 헤더 부분 추출할 CRLF
                int cr1 = 0, lf1 = 0, cr2 = 0;
                // boundaryHeaderData를 답을 Map
                Map<String, String> boundaryHeaderData = new LinkedHashMap<String, String>();
                // 파일 서칭 시작점을 표시해줄 skipPoint
                int skipPoint = 0;

                // Body 읽기
                for (int i = 0; i < 3; i++) {
                    FileInputStream readTempFile = new FileInputStream(tempFile);
                    if (skipPoint != 0) {
                        readTempFile.skip(skipPoint);
                    }
                    boundaryHead = new byte[200];
                    int skipPointAdd = 0;
                    while (n != -1) {
                        n = readTempFile.read();
                        skipPointAdd++;
                        if (headerStart && n == boundaryNameByte[bnbCehck]) {
                            if (boundaryNameByte.length == bnbCehck + 1) {
                                headerStart = false;
                            } else {
                                bnbCehck++;
                            }
                        } else if (bnbCehck + 1 == boundaryNameByte.length) {
                            if (cr1 == 13 && lf1 == 10 && cr2 == 13 && n == 10) {
                                String parseBoundaryHeader = new String(boundaryHead);
                                System.out.println("=============boundaryHeader=============");
                                // 바운더리 헤더 부분 formData Map에 데이터 삽입
                                String[] strs = parseBoundaryHeader.split("\r\n");
                                for (int k = 1; k < strs.length - 1; k++) {
                                    System.out.println(strs[k]);
                                    if (k == 1) {
                                        String[] str2 = strs[k].split("; ");
                                        boundaryHeaderData.put(str2[0].split(": ")[0], str2[0].split(": ")[1]);
                                        for (int t = 1; t < str2.length; t++) {
                                            String[] str3 = str2[t].split("=");
                                            boundaryHeaderData.put(str3[0], str3[1].substring(1, str3[1].length() - 1));
                                        }
                                    } else {
                                        String[] strs2 = strs[k].split(": ");
                                        boundaryHeaderData.put(strs2[0], strs2[1]);
                                    }
                                }
                                System.out.println("=============boundaryHeader=============");
                                if (boundaryHeaderData.get("filename") == null
                                        || boundaryHeaderData.get("filename").equals("")
                                        || boundaryHeaderData.get("filename").isEmpty()
                                        || boundaryHeaderData.get("filename").isBlank()) {
                                    skipPoint += skipPointAdd;
                                }
                                break;
                            } else if (cr1 == 13 && lf1 == 10 && n == 13) {
                                cr2 = 13;
                            } else if (cr1 == 13 && n == 10) {
                                lf1 = 10;
                            } else if (n == 13) {
                                cr1 = 13;
                            } else {
                                cr1 = 0;
                                cr2 = 0;
                                lf1 = 0;
                            }
                            boundaryHead[bhOff++] = (byte) n;
                        } else {
                            bnbCehck = 0;
                        }
                    }
                    // 바이트쓰기
                    if (boundaryHeaderData.get("filename") != null
                            && !boundaryHeaderData.get("filename").equals("")
                            && !boundaryHeaderData.get("filename").isEmpty()
                            && !boundaryHeaderData.get("filename").isBlank()) {
                        byte[] boundaryBodyByte = new byte[4096];
                        int boundaryBodylen = boundaryBodyByte.length;
//                        File outFile = new File("/Users/dongsubyoon/Downloads/ServerRoot/download/" + boundaryHeaderData.get("filename"));  //Mac Path
                        File outFile = new File("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\download\\" + boundaryHeaderData.get("filename"));  //Window Path
                        rename:
                        while (true) {
                            for (int nameNum = 1; ; nameNum++) {
                                if (outFile.exists()) {
//                                    outFile = new File("/Users/dongsubyoon/Downloads/ServerRoot/download/" + boundaryHeaderData.get("filename").split("\\.")[0] + "(" + nameNum + ")." + boundaryHeaderData.get("filename").split("\\.")[1]);  //Mac Path
                                    outFile = new File("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\download\\" + boundaryHeaderData.get("filename").split("\\.")[0] + "(" + nameNum + ")." + boundaryHeaderData.get("filename").split("\\.")[1]); //Window Path
                                } else {
                                    break rename;
                                }
                            }
                        }
                        FileOutputStream FileoutputStream = new FileOutputStream(outFile, true);
                        read:
                        while (true) {
                            readTempFile.read(boundaryBodyByte, 0, boundaryBodylen);
                            for (int x = 0; x < boundaryBodyByte.length - 6; x++) {
                                if (boundaryBodyByte[x] == 45 && boundaryBodyByte[x + 1] == 45 && boundaryBodyByte[x + 2] == 45 && boundaryBodyByte[x + 3] == 45 && boundaryBodyByte[x + 4] == 45 && boundaryBodyByte[x + 5] == 45) {
                                    FileoutputStream.write(Arrays.copyOf(boundaryBodyByte, x));
                                    skipPoint += x;
                                    break read;
                                }
                            }
                            skipPoint += 4096;
                            FileoutputStream.write(boundaryBodyByte);
                        }
                        FileoutputStream.close();
                    }
                    cr1 = 0;
                    cr2 = 0;
                    lf1 = 0;
                    bnbCehck = 0;
                    bhOff = 0;
                    headerStart = true;
                    readTempFile.close();
                }
                tempFile.delete();
            } else { // 100메가 이하면 그냥 바로쓰기
                boolean charCheck = false;
                for (int i = 0; i < contentLength; i++) {
                    if (buff[i] == 45 && buff[i + 1] == 45 && buff[i + 2] == 45 && buff[i + 3] == 45 && buff[i + 4] == 45 && buff[i + 5] == 45) {
                        charCheck = true;
                    }
                    if (charCheck && i >= boundaryNameByte.length) {
                        splitPoint = Arrays.copyOfRange(buff, i - boundaryNameByte.length, i);
                        //바운더리 이름에 걸리면 작업 시작
                        if (Arrays.equals(splitPoint, boundaryNameByte)) {
                            if (boundaryStartPoint == 0) {
                                boundaryStartPoint = i + 1;
                                charCheck = false;
                            } else if (boundaryEndPoint == 0) {
                                boundaryEndPoint = i - boundaryNameByte.length;
                                //같은거 i~from 기준으로 잘라서 카피
                                byte[] boundaryData = Arrays.copyOfRange(buff, boundaryStartPoint, boundaryEndPoint);
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
                                                boundaryHeaderData.put(str2[0].split("\n")[1].split(": ")[0], str2[0].split("\n")[1].split(": ")[1]);
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
//                                    File outFile = new File("/Users/dongsubyoon/Downloads/ServerRoot/download/" + boundaryHeaderData.get("filename"));  //Mac Path
                                    File outFile = new File("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\download\\" + boundaryHeaderData.get("filename"));  //Window Path
                                    rename:
                                    while (true) {
                                        for (int nameNum = 1; ; nameNum++) {
                                            if (outFile.exists()) {
//                                                outFile = new File("/Users/dongsubyoon/Downloads/ServerRoot/download/" + boundaryHeaderData.get("filename").split("\\.")[0] + "(" + nameNum + ")." + boundaryHeaderData.get("filename").split("\\.")[1]);  //Mac Path
                                                outFile = new File("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\download\\" + boundaryHeaderData.get("filename").split("\\.")[0] + "(" + nameNum + ")." + boundaryHeaderData.get("filename").split("\\.")[1]);
                                            } else {
                                                break rename;
                                            }
                                        }
                                    }
                                    FileOutputStream FileoutputStream = new FileOutputStream(outFile);
                                    FileoutputStream.write(boundaryBodyData);
                                    FileoutputStream.close();

                                }
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


        String msg = "";

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
        outputStream.write(msg.getBytes());

        System.out.println(bodyData);
    }
}
