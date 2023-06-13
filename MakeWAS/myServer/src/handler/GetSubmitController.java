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
        OutputStream out = res.getOutputStream();

        String msg = "";
        msg += "<meta charset='UTF-8'/>\r\n";
        msg += "<link rel='icon' href='data:,'>\r\n";
        msg += "<script>\r\n";
        msg += "    function getCheck(){\r\n";
        msg += "        if(document.getElementById('getId').value==null || document.getElementById('getId').value==''){\r\n";
        msg += "            alert('아이디를 입력하세요.');\r\n";
        msg += "            return false;\r\n";
        msg += "        } else if(document.getElementById('getPwd').value==null || document.getElementById('getPwd').value==''){\r\n";
        msg += "            alert('비밀번호를 입력하세요.');\r\n";
        msg += "            return false;\r\n";
        msg += "        } else {\r\n";
        msg += "            document.getElementById('getForm').submit();\r\n";
        msg += "        };\r\n";
        msg += "    };\r\n";
        msg += "</script>\r\n";
        msg += "<body>\r\n";
        msg += "    <form id='getForm' method='get' action='/'>\r\n";
        msg += "        <label>id</label>\r\n";
        msg += "        <input id='getId' type='text' name='id'/>\r\n";
        msg += "        <label>pwd</label>\r\n";
        msg += "        <input id='getPwd' type='password' name='pwd'/>\r\n";
        msg += "        <a href='#' onclick='getCheck()'>Get Submit</a>\r\n";
        msg += "    </form>\r\n";
        msg += "    <label>id = "+ HttpPieRequest.param.get("id") + "</label>\r\n";
        msg += "    </br>\r\n";
        msg += "    <label>pwd = "+ HttpPieRequest.param.get("pwd") + "</label>\r\n";
        msg += "    </br>\r\n";
        msg += "    </br>\r\n";
        msg += "    <a href='/'>처음으로</a>\r\n";
        msg += "</body>\r\n";

        res.setStatus(200);
        res.setContentType("text/html");
        res.setMassage(msg);
        res.flush();

        out.flush();
        in.close();
        out.close();
    }

    @Override
    public void postHandle(HttpPieRequest req, HttpPieResponse res) throws IOException {

    }
}
