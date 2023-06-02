package handler;

import http.HttpPieRequest;
import http.HttpPieResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GetSubmitController implements HttpPieHandler {
    @Override
    public void getHandle(HttpPieRequest req, HttpPieResponse res) throws IOException {
        InputStream in = req.getInputStream();
        System.out.println(req.getRequestParam());

        OutputStream out = res.getOutputStream();

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
        msg += "   <label>id = "+req.getRequestParam().get("id") + "</label>\r\n";
        msg += "   </br>\r\n";
        msg += "   <label>pwd = "+req.getRequestParam().get("pwd") + "</label>\r\n";
        msg += "   </br>\r\n";
        msg += "   </br>\r\n";
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
