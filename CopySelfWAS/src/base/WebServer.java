package base;

import handler.HttpPieHandler;
import handler.HttpPieHandlerFactory;
import http.HttpPieRequest;
import http.HttpPieResponse;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class WebServer implements Runnable {
    private Socket socket;
    private HttpPieRequest req;
    private HttpPieResponse res;

    public WebServer(Socket socket) {
        this.socket = socket;
        req = new HttpPieRequest(socket);
        res = new HttpPieResponse(socket);
    }

    @Override
    public void run() {
        int n;
        int headerCheckCount = 0;
        // HTTP헤더 정보를 담을 바이트 배열
        byte[] headerByteData = new byte[8000]; //Tomcat 최대 헤더 정보 8k(default) - 48k
        int off = 0;
        try {
            InputStream in = req.getInputStream();
            while ((n = in.read()) != -1) {
                headerByteData[off++] = (byte) n;
                if (n == 13 || n == 10) {
                    headerCheckCount++;
                    if (headerCheckCount == 4) {
                        break;
                    }
                } else {
                    headerCheckCount = 0;
                }
            }
            // 파싱할 header바이트 코드를 String으로 변환하여 Map에 삽입
            String headerDataStr = URLDecoder.decode(new String(Arrays.copyOf(headerByteData, off)));
            if (headerDataStr.length() > 10) {
                String[] headerDataStrArr = headerDataStr.split("\r\n");
                Map<String, String> headerData = new LinkedHashMap<String, String>();
                for (int i = 0; i < headerDataStrArr.length; i++) {
                    if (i == 0) {
                        String[] firstRow = headerDataStrArr[i].split(" ");
                        headerData.put("Method", firstRow[0]);
                        headerData.put("URL", firstRow[1]);
                        headerData.put("Protocol", firstRow[2]);
                    } else {
                        String[] rowData = headerDataStrArr[i].split(": ");
                        headerData.put(rowData[0], rowData[1]);
                        if (rowData[1].contains("boundary")) {
                            String boundary = "--" + rowData[1].substring(rowData[1].indexOf("boundary=") + 9, rowData[1].length());
                            headerData.put("boundaryName", boundary);
                        }
                    }
                }
                headerData.put("Charset", String.valueOf(Charset.defaultCharset()));
                // header HttpPieRequest에 파싱
                req.headerDataParsing(headerData);

                // Get방식으로 날라온 Data 파싱 후 Map에 담기
                if (req.getMethod().equals("GET") && req.getUrl().contains("?")) {
                    Map<String, String> requestParam = new LinkedHashMap<String, String>();
                    String[] getUrlData = req.getUrl().split("/\\?");
                    String[] getData = getUrlData[1].split("&");
                    for (int i = 0; i < getData.length; i++) {
                        requestParam.put(getData[i].split("=")[0], getData[i].split("=")[1]);
                    }
                    req.setRequestParam(requestParam);
                }
                // Post방식으로 날라온 Data 파싱 후 Map에 담기
                if (req.getMethod().equals("POST")) {
                    if (req.getContentType().contains("form-data")) {
                        Util.makeTempFile(req);
                    } else {
                        Map<String, String> requestParam = new LinkedHashMap<String, String>();
                        byte[] postFormData = new byte[Integer.parseInt(req.getContentLength())];
                        while (true) {
                            in.read(postFormData, 0, Integer.parseInt(req.getContentLength()));
                            if (in.available() < 1) {
                                break;
                            }
                        }
                        String[] postData = new String(postFormData).split("&");
                        for (int i = 0; i < postData.length; i++) {
                            requestParam.put(postData[i].split("=")[0], postData[i].split("=")[1]);
                        }
                        req.setRequestParam(requestParam);
                    }
                }
                // handler실행
                if (req.getMethod().equals("GET")) {
                    HttpPieHandler httpPieHandler = (HttpPieHandler) HttpPieHandlerFactory.getInstance(req.getUrl()).getConstructor().newInstance();
                    httpPieHandler.getHandle(req, res);
                } else if (req.getMethod().equals("POST")) {
                    HttpPieHandler httpPieHandler = (HttpPieHandler) HttpPieHandlerFactory.getInstance(req.getUrl()).getConstructor().newInstance();
                    httpPieHandler.postHandle(req, res);
                }
            }
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
