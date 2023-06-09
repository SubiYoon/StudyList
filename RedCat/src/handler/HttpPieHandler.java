package handler;

import http.HttpPieRequest;
import http.HttpPieResponse;

import java.io.IOException;

public interface HttpPieHandler {
    public void getHandle(HttpPieRequest req, HttpPieResponse res) throws IOException;
    public void postHandle(HttpPieRequest req, HttpPieResponse res) throws IOException;
}
