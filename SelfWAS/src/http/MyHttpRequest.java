package http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;

public class MyHttpRequest {
    private Socket socket;
    private String method;
    private String url;
    private String protocol;
    private String host;
    private String connection;
    private String contentLength;
    private String cacheControl;
    private String secChUa;
    private String secChUaMobile;
    private String secChUaPlatform;
    private String upgradeInsecureReqeusts;
    private String origin;
    private String contentType;
    private String boundaryName;
    private String userAgnet;
    private String accept;
    private String secFetchSite;
    private String secFetchMode;
    private String secFetchUser;
    private String secFetchDest;
    private String referer;
    private String acceptEncoding;
    private String acceptLanguage;

    public MyHttpRequest(Socket socket) {
        this.socket = socket;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getContentLength() {
        return contentLength;
    }

    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getSecChUa() {
        return secChUa;
    }

    public void setSecChUa(String secChUa) {
        this.secChUa = secChUa;
    }

    public String getSecChUaMobile() {
        return secChUaMobile;
    }

    public void setSecChUaMobile(String secChUaMobile) {
        this.secChUaMobile = secChUaMobile;
    }

    public String getSecChUaPlatform() {
        return secChUaPlatform;
    }

    public void setSecChUaPlatform(String secChUaPlatform) {
        this.secChUaPlatform = secChUaPlatform;
    }

    public String getUpgradeInsecureReqeusts() {
        return upgradeInsecureReqeusts;
    }

    public void setUpgradeInsecureReqeusts(String upgradeInsecureReqeusts) {
        this.upgradeInsecureReqeusts = upgradeInsecureReqeusts;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBoundaryName() {
        return boundaryName;
    }

    public void setBoundaryName(String boundaryName) {
        this.boundaryName = boundaryName;
    }

    public String getUserAgnet() {
        return userAgnet;
    }

    public void setUserAgnet(String userAgnet) {
        this.userAgnet = userAgnet;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getSecFetchSite() {
        return secFetchSite;
    }

    public void setSecFetchSite(String secFetchSite) {
        this.secFetchSite = secFetchSite;
    }

    public String getSecFetchMode() {
        return secFetchMode;
    }

    public void setSecFetchMode(String secFetchMode) {
        this.secFetchMode = secFetchMode;
    }

    public String getSecFetchUser() {
        return secFetchUser;
    }

    public void setSecFetchUser(String secFetchUser) {
        this.secFetchUser = secFetchUser;
    }

    public String getSecFetchDest() {
        return secFetchDest;
    }

    public void setSecFetchDest(String secFetchDest) {
        this.secFetchDest = secFetchDest;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    /**
     * InputStream 객체를 생성하여 반환하는 메서드
     *
     * @return InputStream객체를 반환
     * @throws IOException
     */
    public InputStream getInutStream() throws IOException {
        return socket.getInputStream();
    }

    /**
     * HTTP헤더의 데이터를 파싱하는 메서드
     *
     * @param map 파싱을 할 목록이 담겨있는 map
     */
    public void headerParsing(Map<String, String> map) {
        setMethod(map.get("Method"));
        setUrl(map.get("URL"));
        setProtocol(map.get("Protocol"));
        setHost(map.get("Host"));
        setConnection(map.get("Connection"));
        if (map.get("Content-Length") != null) {
            setContentLength(map.get("Content-Length"));
        }
        setCacheControl(map.get("Cache-Control"));
        setSecChUa(map.get("sec-ch-ua"));
        setSecChUaMobile(map.get("sec-ch-ua-mobile"));
        setSecChUaPlatform(map.get("sec-ch-ua-platform"));
        setUpgradeInsecureReqeusts(map.get("Upgrade-Insecure-Requests"));
        if (map.get("Origin") != null) {
            setOrigin(map.get("Origin"));
        }
        if (map.get("Content-Type") != null) {
            setContentType(map.get("Content-Type"));
        }
        setUserAgnet(map.get("User-Agent"));
        setAccept(map.get("Accept"));
        setSecFetchSite(map.get("Sec-Fetch-Site"));
        setSecFetchMode(map.get("Sec-Fetch-Mode"));
        setSecFetchUser(map.get("Sec-Fetch-User"));
        setSecFetchDest(map.get("Sec-Fetch-Dest"));
        if(map.get("Referer") != null){
            setReferer(map.get("Referer"));
        }
        setAcceptEncoding(map.get("Accept-Encoding"));
        setAcceptLanguage(map.get("Accept-Language"));
    }

    @Override
    public String toString() {
        return  "</Header>=====================\r\n" +
                "\r\nsocket = " + socket +
                "\r\nmethod = " + method +
                "\r\nurl = " + url +
                "\r\nprotocol = " + protocol +
                "\r\nhost = " + host +
                "\r\nconnection = " + connection +
                "\r\ncontentLength = " + contentLength +
                "\r\ncacheControl = " + cacheControl +
                "\r\nsecChUa = " + secChUa +
                "\r\nsecChUaMobile = " + secChUaMobile +
                "\r\nsecChUaPlatform = " + secChUaPlatform +
                "\r\nupgradeInsecureReqeusts = " + upgradeInsecureReqeusts +
                "\r\norigin = " + origin +
                "\r\ncontentType = " + contentType +
                "\r\nboundaryName = " + boundaryName +
                "\r\nuserAgnet = " + userAgnet +
                "\r\naccept = " + accept +
                "\r\nsecFetchSite = " + secFetchSite +
                "\r\nsecFetchMode = " + secFetchMode +
                "\r\nsecFetchUser = " + secFetchUser +
                "\r\nsecFetchDest = " + secFetchDest +
                "\r\nreferer = " + referer +
                "\r\nacceptEncoding = " + acceptEncoding +
                "\r\nacceptLanguage = " + acceptLanguage +
                "\r\n\r\n</Header>=====================";
    }
}
