package handler;

import base.Util;
import http.HttpPieRequest;
import http.HttpPieResponse;

import java.io.*;

public class FileSearchController implements HttpPieHandler {
    @Override
    public void getHandle(HttpPieRequest req, HttpPieResponse res) throws IOException {
        InputStream in = req.getInputStream();
        OutputStream out = res.getOutputStream();

//        String serverRoot = "/Users/dongsubyoon/Downloads"; //Mac Path
        String serverRoot = "C:\\Users\\Ulim\\Desktop\\Downloads";    //Window Path
        File file = new File(serverRoot + HttpPieRequest.httpHeader.get("Url"));
        String msg = "";
        if (file.isFile()) {
//            FileInputStream fis = new FileInputStream(file);
//            byte[] fileByte = fis.readAllBytes();
            res.setStatus(200);
            if(HttpPieRequest.httpHeader.get("Url").contains(".pdf")){
                res.setContentType("application/pdf");
                res.setFile(file);
            }else {
                res.setContentType("application/jpeg");
                res.setFile(file);
            }
        } else {
//            String[] folderList = Util.folderSearch(req.getUrl()); //Mac Path
        String[] folderList = Util.folderSearch(HttpPieRequest.httpHeader.get("Url").replace("/", "\\"));   //Window Path
            int cutURL = HttpPieRequest.httpHeader.get("Url").substring(0, HttpPieRequest.httpHeader.get("Url").length() - 1).lastIndexOf("/");
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
            if (!HttpPieRequest.httpHeader.get("Url").equals("/serverRoot/")) {
                msg += "   <a href='" + HttpPieRequest.httpHeader.get("Url").substring(0, cutURL + 1) + "'><h2>../</h2></a>\r\n";
            }
            for (int i = 0; i < folderList.length; i++) {
                if (folderList[i].contains("/")) {
                    msg += "    <a href='" + folderList[i] + "'>" + folderList[i] + "</a></br>\r\n";
                } else if (folderList[i].contains(".pdf")) {
                    msg += "    <a href='" + folderList[i].substring(0, folderList[i].length() - 1) + "'>" + folderList[i] + "</a></br>\r\n";
                } else {
                    msg += "    <a href='" + folderList[i].substring(0, folderList[i].length() - 1) + "' download>" + folderList[i] + "</a></br>\r\n";
                }
            }
            msg += "   <a href='/'>처음으로</a>\r\n";
            msg += "</body>\r\n";

            res.setStatus(200);
            res.setContentType("text/html");
        }
        res.setMassage(msg);
        res.flush();

        in.close();
        out.close();
    }

    @Override
    public void postHandle(HttpPieRequest req, HttpPieResponse res) throws IOException {

    }
}
// java가 jar를 실행할 수 있는지 여부
// Main 메서드가 있는 곳을 지시해주는 파일도 같이 jar로 묶어야함