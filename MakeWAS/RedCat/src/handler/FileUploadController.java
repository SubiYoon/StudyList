package handler;

import http.HttpPieRequest;
import http.HttpPieResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUploadController implements HttpPieHandler{
    @Override
    public void getHandle(HttpPieRequest req, HttpPieResponse res) throws IOException {
        InputStream in = req.getInputStream();
        OutputStream out = res.getOutputStream();

        String msg = "";
        msg += "<meta charset='UTF-8'/>\r\n";
        msg += "<link rel='icon' href='data:,'/>\r\n";
        msg += "<script>\r\n";
        msg += "    function fileSubmit(){\r\n";
        msg += "        document.getElementById('file_upload_form').submit();\r\n";
        msg += "    };\r\n";
        msg += "</script>\r\n";
        msg += "<body>\r\n";
        msg += "    <form id='file_upload_form' method='post' action='/fileUpload' enctype='multipart/form-data'>\r\n";
        msg += "        <input id='file1' type='file' name='file1'/>\r\n";
        msg += "        <input id='file2' type='file' name='file2'/>\r\n";
        msg += "        <input id='file3' type='file' name='file3'/>\r\n";
        msg += "        <input id='file4' type='file' name='file4'/>\r\n";
        msg += "        <a href='#' onclick='fileSubmit()'>파일 전송</a>\r\n";
        msg += "        </br>\r\n";
        msg += "        </br>\r\n";
        msg += "        <a href='/'>처음으로</a>\r\n";
        msg += "    </form>\r\n";
        msg += "</body>\r\n";

        res.setStatus(200);
        res.setContentType("text/html");
        res.setMassage(msg);
        res.flush();

        in.close();
        out.close();
    }

    @Override
    public void postHandle(HttpPieRequest req, HttpPieResponse res) throws IOException {
        InputStream in = req.getInputStream();
        OutputStream out = res.getOutputStream();

        /* Mac Path */
        req.fileSave("/Users/ABCD/Develop/StudyList/MakeWAS/serverRoot/FileUpload/", "file1");
        req.fileSave("/Users/ABCD/Develop/StudyList/MakeWAS/serverRoot/FileUpload/", "file2");
        req.fileSave("/Users/ABCD/Develop/StudyList/MakeWAS/serverRoot/FileUpload/", "file3");
        req.fileSave("/Users/ABCD/Develop/StudyList/MakeWAS/serverRoot/FileUpload/", "file4");
        /* Window Path */
//        req.fileSave("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\FileUpload\\", "file1");
//        req.fileSave("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\FileUpload\\", "file2");
//        req.fileSave("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\FileUpload\\", "file3");
//        req.fileSave("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\FileUpload\\", "file4");

        String msg = "";
        msg += "<meta charset='UTF-8'/>\r\n";
        msg += "<link rel='icon' href='data:,'/>\r\n";
        msg += "<script>\r\n";
        msg += "    function fileSubmit(){\r\n";
        msg += "        document.getElementById('file_upload_form').submit();\r\n";
        msg += "    };\r\n";
        msg += "</script>\r\n";
        msg += "<body>\r\n";
        msg += "    <h1>파일 업로드 완료!!</h1 \r\n>";
        msg += "    </br>\r\n";
        msg += "    <form id='file_upload_form' method='post' action='/fileUpload' enctype='multipart/form-data'>\r\n";
        msg += "        <input id='file1' type='file' name='file1'/>\r\n";
        msg += "        <input id='file2' type='file' name='file2'/>\r\n";
        msg += "        <input id='file3' type='file' name='file3'/>\r\n";
        msg += "        <input id='file4' type='file' name='file4'/>\r\n";
        msg += "        <a href='#' onclick='fileSubmit()'>파일 전송</a>\r\n";
        msg += "        </br>\r\n";
        msg += "        </br>\r\n";
        msg += "        <a href='/'>처음으로</a>\r\n";
        msg += "    </form>\r\n";
        msg += "</body>\r\n";

        res.setStatus(200);
        res.setContentType("text/html");
        res.setMassage(msg);
        res.flush();

        in.close();
        out.close();
    }
}
