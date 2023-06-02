package handler;

import http.HttpPieRequest;
import http.HttpPieResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainController implements HttpPieHandler {
    @Override
    public void getHandle(HttpPieRequest req, HttpPieResponse res) throws IOException {
        //남은 body데이터를 읽어올 inputStream
        InputStream in = req.getInputStream();
        // 응답해줄 OutputStream
        OutputStream out = res.getOutputStream();
        // 응답
        String msg = "";
        msg += "<meta charset='UTF-8'/>\r\n";
        msg += "<link rel='icon' href='data:,'>\r\n";
        msg += "<script>\r\n";
        msg += "</script>\r\n";
        msg += "<body>\r\n";
        msg += "   <form method='get' action='/'>\r\n";
        msg += "       <label>id</label>\r\n";
        msg += "       <input type='text' name='id'/>\r\n";
        msg += "       <label>pwd</label>\r\n";
        msg += "       <input type='text' name='pwd'/>\r\n";
        msg += "       <button>Get Submit</button>\r\n";
        msg += "   </form>\r\n";
        msg += "   <a href='/serverRoot/' >폴더 탐색</a>\r\n";
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
