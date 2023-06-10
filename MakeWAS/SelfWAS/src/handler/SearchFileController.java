package handler;

import base.Util;
import http.MyHttpRequest;
import http.MyHttpResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SearchFileController implements MyHttpHandler{
    @Override
    public void getHanlde(MyHttpRequest req, MyHttpResponse res) throws IOException {
        Socket socket = res.getSocket();
        OutputStream outputStream = res.getOutputStream();

        String msg = "";
//        String serverRoot = "/Users/dongsubyoon/Downloads"; //Mac
        String serverRoot = "C:\\Users\\Ulim\\Desktop\\Downloads"; //Window
//        File file = new File(serverRoot + req.getUrl());   //Mac
        File file = new File(serverRoot + req.getUrl().replace("/", "\\"));    //Window
        if (!file.exists()) {
            outputStream.write(new String("HTTP/1.1 404 Not Found\r\n").getBytes());
            socket.close();
        }
        boolean isFile = file.isFile();
        if (isFile) {
            Path path = Paths.get(serverRoot + req.getUrl());
            long fileSize = Files.size(path);
            outputStream.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
            outputStream.write(new String("Content-Length:" + fileSize + "\r\n").getBytes());
            // 파일이 pdf 형식일 시 브라우저에서 뷰어 실행
            if (req.getUrl().contains(".pdf")) {
                outputStream.write(new String("Content-Type:application/pdf\r\n\r\n").getBytes());

                msg += "<meta charset='UTF-8'>";
                msg += "<title>pdf 뷰어 테스트</title>";
                msg += "<body>";
                outputStream.write(msg.getBytes());
                msg = "";
                FileInputStream fis = new FileInputStream(serverRoot + req.getUrl());
                byte[] fileBytes = fis.readAllBytes();
                outputStream.write(fileBytes);
                msg += "</body>";
                outputStream.write(msg.getBytes());
            } else {
                outputStream.write(new String("Content-Type:application/jpeg\r\n\r\n").getBytes());

                FileInputStream fis = new FileInputStream(serverRoot + req.getUrl());
                byte[] fileBytes = fis.readAllBytes();
                outputStream.write(fileBytes);
            }
        } else {
            // directory
//                            String[] folderList = Util.searchAllFolderList(req.getUrl());   //Mac
            String[] folderList = Util.searchAllFolderList(req.getUrl().replace("/", "\\"));   //Window
            int cutURL = req.getUrl().substring(0, req.getUrl().length() - 1).lastIndexOf("/");
            msg += "<meta charset='utf-8'>\r\n";
            msg += "<link rel='icon' href='data:,'/>\r\n";  //favicon.ico 를 로드하지 않게 설정
            msg += "<body>\r\n";
            msg += "<h3>마지막에 '*'가 붙어있으면 파일을 다운로드 합니다.</h1>\r\n";
            msg += "<h3>마지막에 'pdf' 파일은 뷰어를 실행시킵니다.</h1>\r\n";
            msg += "    <ul>\r\n";
            msg += "        <li><a href='" + req.getUrl().substring(0, cutURL + 1) + "'>../</a></li>\r\n";
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

        outputStream.write(msg.getBytes());
    }

    @Override
    public void postHandle(MyHttpRequest req, MyHttpResponse res) throws IOException {

    }
}
