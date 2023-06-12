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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class WebServer implements Runnable {
    private Socket socket;
    private HttpPieRequest req;
    private HttpPieResponse res;

    public WebServer(Socket socket) throws IOException {
        this.socket = socket;
        req = new HttpPieRequest(socket);
        res = new HttpPieResponse(socket);
    }

    @Override
    public void run() {
        int n;
        int headerCheck = 0;
        int off = 0;
        byte[] headerByte = new byte[8000];
        try {
            InputStream in = req.getInputStream();
            Map<String, String> headerData = new LinkedHashMap<String, String>();
            String headerStr = null;

            while ((n = in.read()) != -1) {
                headerByte[off++] = (byte) n;
                if (headerCheck == 3 && n == 10) {
                    headerStr = URLDecoder.decode(new String(Arrays.copyOf(headerByte, off)));
                    off = 0;
                    break;
                } else if (n == 13 || n == 10) {
                    headerCheck++;
                } else {
                    headerCheck = 0;
                }
            }

            String[] headerRow = headerStr.split("\r\n");
            for (int i = 0; i < headerRow.length; i++) {
                if (i == 0) {
                    headerData.put("Method", headerRow[i].substring(0, headerRow[i].indexOf(" ")));
                    headerData.put("Url", headerRow[i].substring(headerRow[i].indexOf(" ")+1, headerRow[i].lastIndexOf(" ")));
                    headerData.put("Protocol", headerRow[i].substring(headerRow[i].lastIndexOf(" ")+1));
                } else {
                    String[] row = headerRow[i].split(": ");
                    headerData.put(row[0], row[1]);
                    if (row[1].contains("Boundary")) {
                        headerData.put("BoundaryName", "--" + row[1].split("boundary=")[1]);
                    }
                }
            }
            // HTTP Header 파싱
            req.setHttpHeader(headerData);

            Map<String, String> param = new LinkedHashMap<String, String>();
            if (headerData.get("Method").equals("GET") && headerData.get("Url").contains("?")) {
                String[] urlData = headerData.get("Url").substring(2).split("&");
                for (int i = 0; i < urlData.length; i++) {
                    param.put(urlData[i].split("=")[0], urlData[i].split("=")[1]);
                }
                req.setParam(param);
            }
            if (headerData.get("Method").equals("POST")) {
                if (headerData.get("Content-Type").equals("application/x-www-form-urlencoded")) {
                    byte[] postParam = new byte[Integer.parseInt(headerData.get("Content-Length"))];
                    while ((n = in.read(postParam, 0, postParam.length)) != -1) {
                        String data = new String(postParam);
                        String[] postData = data.split("&");
                        for (int i = 0; i < postData.length; i++) {
                            param.put(postData[i].split("=")[0], postData[i].split("=")[1]);
                        }
                        req.setParam(param);
                        break;
                    }
                } else if (headerData.get("Content-Type").contains("multipart/form-data")) {
                    Util.makeTempFile(req);
                }
            }

            if (headerData.get("Method").equals("GET")) {
                HttpPieHandler handler = (HttpPieHandler) HttpPieHandlerFactory.getInstance(headerData.get("Url")).getConstructor().newInstance();
                handler.getHandle(req, res);
            }
            if (headerData.get("Method").equals("POST")) {
                HttpPieHandler handler = (HttpPieHandler) HttpPieHandlerFactory.getInstance(headerData.get("Url")).getConstructor().newInstance();
                handler.postHandle(req, res);
            }
            socket.close();
        } catch (IOException | NoSuchMethodException e) {
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
