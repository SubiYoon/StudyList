package base;

import http.HttpPieRequest;
import http.HttpPieResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class WebServer implements Runnable{
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

            while((n = in.read()) != -1){
                headerByte[off++] = (byte) n;
                if(headerCheck == 3 && n == 10){
                    String headerStr = new String(Arrays.copyOf(headerByte, off));
                    String[] headerRow = headerStr.split("\r\n");
                    for(int i=0; i<headerRow.length; i++){
                        if(i == 0){
                            String[] firstRow = headerRow[i].split(" ");
                            headerData.put("method", firstRow[0]);
                            headerData.put("url", firstRow[1]);
                            headerData.put("protocol", firstRow[2]);
                        } else {
                            String[] row = headerRow[i].split(": ");
                            headerData.put(row[0].toLowerCase(), row[1]);
                            if(row[1].contains("boundary")){
                                headerData.put("boundaryname", "--" + row[1].split("boundary=")[1]);
                            }
                        }
                    }
                    System.out.println(headerStr);
                    System.out.println(headerData);
                    break;
                }else if(n == 13 || n == 10){
                    headerCheck++;
                }else {
                    headerCheck = 0;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
