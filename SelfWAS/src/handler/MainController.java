package handler;

import http.MyHttpRequest;
import http.MyHttpResponse;

import java.io.IOException;

public class MainController implements MyHttpHandler{
    @Override
    public void getHanlde(MyHttpRequest req, MyHttpResponse res) throws IOException {
        System.out.println(req.toString());

    }

    @Override
    public void postHandle(MyHttpRequest req, MyHttpResponse res) throws IOException {
        System.out.println(req.toString());
    }
}