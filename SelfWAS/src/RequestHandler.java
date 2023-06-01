import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface RequestHandler {
    public void GetHanlde(HttpRequest req, HttpResponse res);
    public void PostHandle(HttpRequest req, HttpResponse res);
}