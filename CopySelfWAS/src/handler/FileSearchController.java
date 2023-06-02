package handler;

import base.Util;
import http.HttpPieRequest;
import http.HttpPieResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileSearchController implements HttpPieHandler{
    @Override
    public void getHandle(HttpPieRequest req, HttpPieResponse res) throws IOException {
        InputStream in = req.getInputStream();

        OutputStream out = res.getOutputStream();
        String[] folderList = Util.folderSearch(req.getUrl().replace("/", "\\"));
        int cutURL = req.getUrl().substring(0, req.getUrl().length() - 1).lastIndexOf("/");
        String msg = "";
        msg += "<meta charset='UTF-8'/>\r\n";
        msg += "<link rel='icon' href='data:,'/>\r\n";
        msg += "<script>\r\n";
        msg += "</script>\r\n";
        msg += "<body>\r\n";
        msg += "   <h3>마지막에 붙은 기호에 따라 역할이 다름</h1>\r\n";
        msg += "   <h3>/ - 해당 폴더를 탐색</h1>\r\n";
        msg += "   <h3>* - 해당 파일을 다운로드</h1>\r\n";
        msg += "   <h3>.pdf* - 해당 PDF파일을 브라우저에서 읽기</h1>\r\n";
        msg += "   </br>\r\n";
        msg += "   </br>\r\n";
        if(!req.getUrl().equals("/serverRoot/")) {
            msg += "   <a href='" + req.getUrl().substring(0, cutURL + 1) + "'><h2>../</h2></a>\r\n";
        }
        for(int i=0; i<folderList.length; i++){
            if(folderList[i].contains("/")){

            }else if(folderList[i].contains(".pdf")){

            }else {

            }
        }
        msg += "   <a href='/'>처음으로</a>\r\n";
        msg += "</body>\r\n";

        out.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
        out.write(new String("Content-Length:" + msg.getBytes().length + "\r\n").getBytes());
        out.write(new String("Content-Type:text/html\r\n\r\n").getBytes());
        out.write(msg.getBytes());

        out.flush();
        in.close();
        out.close();
    }

    @Override
    public void postHandle(HttpPieRequest req, HttpPieResponse res) throws IOException {

    }
}
