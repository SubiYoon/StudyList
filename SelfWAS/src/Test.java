public class Test {
    public static void main(String[] args) {
        String str = "abacddd";
        String[] tt = str.split("a");
        for(int i=0; i<tt.length; i++){
            System.out.println(i+":"+tt[i]);
        }
        for(int i=0; i<str.length(); i++) {
            System.out.println(str.getBytes()[i]);
        }
        byte[] tttt = new byte[]{97,98,97,99,100,100,100};
        System.out.println(new String(tttt));
        System.out.println((char)0);
    }
}
//else if (headerData.get("Content-Type").contains("form-data")) {
//        Map<String, String> boundaryHeaderData = new LinkedHashMap<String, String>();
//        String str = new String(bodyByte);
//        String[] boundarys = str.split(headerData.get("boundaryName"));
//        for (int i = 1; i < boundarys.length - 1; i++) {
//        String parseBoundaryHeader = boundarys[i].split("\r\n\r\n")[0];
//        System.out.println("=============boundaryHeader=============" +
//        parseBoundaryHeader +
//        "\r\n=============boundaryHeader=============");
//        // 바운더리 헤더 부분 formData Map에 데이터 삽입
//        String[] strs = parseBoundaryHeader.split("\r\n");
//        for (int k = 1; k < strs.length; k++) {
//        if (k == 1) {
//        String[] str2 = strs[k].split("; ");
//        for (int t = 1; t < str2.length; t++) {
//        String[] str3 = str2[t].split("=");
//        boundaryHeaderData.put(str3[0], str3[1].substring(1, str3[1].length() - 1));
//        }
//        } else {
//        String[] strs2 = strs[k].split(": ");
//        boundaryHeaderData.put(strs2[0], strs2[1]);
//        }
//        }
//        if(!boundaryHeaderData.get("filename").equals("") && !boundaryHeaderData.get("filename").isEmpty() && !boundaryHeaderData.get("filename").isBlank()) {
//        String parseBoundaryBody = boundarys[i].split("\r\n\r\n")[1].substring(0, boundarys[i].split("\r\n\r\n")[1].length()-1);
//        System.out.println("=======================check\r\n" + parseBoundaryBody);
//        byte[] boundaryBodyData = parseBoundaryBody.getBytes();
//        File outFile = new File("/Users/dongsubyoon/Downloads/ServerRoot/download/" + boundaryHeaderData.get("filename"));
//        FileOutputStream fos = new FileOutputStream(outFile);
//        fos.write(boundaryBodyData);
//        fos.close();
//        }
//        }
//        }
