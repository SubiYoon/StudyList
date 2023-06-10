package handler;

import http.MyHttpRequest;
import http.MyHttpResponse;

import java.io.IOException;

public interface MyHttpHandler {
    public void getHanlde(MyHttpRequest req, MyHttpResponse res) throws IOException;

    public void postHandle(MyHttpRequest req, MyHttpResponse res) throws IOException;
}
