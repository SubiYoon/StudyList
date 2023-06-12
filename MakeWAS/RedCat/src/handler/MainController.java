package handler;

import http.HttpPieRequest;
import http.HttpPieResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainController implements HttpPieHandler {
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
        msg += "    function postCheck(){\r\n";
        msg += "        if(document.getElementById('postId').value==null || document.getElementById('postId').value==''){\r\n";
        msg += "            alert('아이디를 입력하세요.');\r\n";
        msg += "            return false;\r\n";
        msg += "        } else if(document.getElementById('postPwd').value==null || document.getElementById('postPwd').value==''){\r\n";
        msg += "            alert('비밀번호를 입력하세요.');\r\n";
        msg += "            return false;\r\n";
        msg += "        } else {\r\n";
        msg += "            document.getElementById('postForm').submit();\r\n";
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
        msg += "    <form id='postForm' method='post' action='/'>\r\n";
        msg += "        <label>id</label>\r\n";
        msg += "        <input id='postId' type='text' name='id'/>\r\n";
        msg += "        <label>pwd</label>\r\n";
        msg += "        <input id='postPwd' type='password' name='pwd'/>\r\n";
        msg += "        <a href='#' onclick='postCheck()'>Post Submit</a>\r\n";
        msg += "    </form>\r\n";
        msg += "    <a href='/serverRoot/' >폴더 탐색</a>\r\n";
        msg += "    </br>\n";
        msg += "    <a href='fileUpload' >파일 업로드</a>\r\n";
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
        InputStream in = req.getInputStream();
        OutputStream out = res.getOutputStream();

        String msg = "";
        msg += "<meta charset='UTF-8'/>\r\n";
        msg += "<link rel='icon' href='data:,'>\r\n";
        msg += "<script>\r\n";
        msg += "    function postCheck(){\r\n";
        msg += "        if(document.getElementById('postId').value==null || document.getElementById('postId').value==''){\r\n";
        msg += "            alert('아이디를 입력하세요.');\r\n";
        msg += "            return false;\r\n";
        msg += "        } else if(document.getElementById('postPwd').value==null || document.getElementById('postPwd').value==''){\r\n";
        msg += "            alert('비밀번호를 입력하세요.');\r\n";
        msg += "            return false;\r\n";
        msg += "        } else {\r\n";
        msg += "            document.getElementById('postForm').submit();\r\n";
        msg += "        };\r\n";
        msg += "    };\r\n";
        msg += "</script>\r\n";
        msg += "<body>\r\n";
        msg += "    <form id='postForm' method='post' action='/'>\r\n";
        msg += "        <label>id</label>\r\n";
        msg += "        <input id='postId' type='text' name='id'/>\r\n";
        msg += "        <label>pwd</label>\r\n";
        msg += "        <input id='postPwd' type='password' name='pwd'/>\r\n";
        msg += "        <a href='#' onclick='postCheck()'>Post Submit</a>\r\n";
        msg += "    </form>\r\n";
        msg += "    <label>id = "+ HttpPieRequest.param.get("id") + "</label>\r\n";
        msg += "    </br>\r\n";
        msg += "    <label>pwd = "+ HttpPieRequest.param.get("pwd") + "</label>\r\n";
        msg += "    </br>\r\n";
        msg += "    </br>\r\n";
        msg += "    <a href='/'>처음으로</a>\r\n";
        msg += "</body>\r\n";

        out.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
        out.write(new String("Content-Length:" + msg.getBytes().length + "\r\n").getBytes());
        out.write(new String("Content-Type:text/html\r\n\r\n").getBytes());
        // res.setStatus(200)
        // res.setHeader("Content-Type", "text/html")
        out.write(msg.getBytes());

        out.flush();
        in.close();
        out.close();
    }
}
