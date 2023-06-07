package base;

import http.HttpPieRequest;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Util {
    /**
     * url정보를 토대로 해당 폴더의 데이터를 Searching하는 메서드
     * terminal의 ls기능과 같은 기능
     * 해당 폴더의 하위 목록에 마지막에 붙은 구분자에 따라 폴더인지 파일인지 구분
     * '/' - 폴더
     * '*' - 파일
     *
     * @param folder 해당 url정보를 토대로 local의 폴더를 탐색하기 위한 변수
     * @return 해당 폴더에 있는 list를 String 배열에 담아 return
     */
    public static String[] folderSearch(String folder) {
//        String path = "/Users/dongsubyoon/Downloads" + folder;   //Mac Path
        String path = "C:\\Users\\Ulim\\Desktop\\Downloads" + folder;   //Window Path
        File file = new File(path);
        String[] fileList = file.list();
        for (int i = 0; i < fileList.length; i++) {
            String endPoint = new File(path + "/" + fileList[i]).isDirectory() ? "/" : "*";
            fileList[i] += endPoint;
        }
        return fileList;
    }

    /**
     * Post방식의 데이터가 넘어왔을 때 해당 데이터가 form-data의 파일형태 일경우 임시 파일 생성
     * 생성 후 바운더리 헤더부분을 파싱하여 HttpPieRequest에 정보 저장
     *
     * @param req HTTP의 body데이터를 읽어들일 HttpPieRequrst
     * @throws IOException
     */
    public static synchronized void makeTempFile(HttpPieRequest req) throws IOException {
        int n;
        int lastArr = 0;
        int off = 0;
//        File tempFile = new File("/Users/dongsubyoon/Downloads/ServerRoot/temp/" + req.getBoundaryName() + ".tmp");    // Mac Temp Path
        File tempFile = new File("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\temp\\" + req.getBoundaryName() + ".tmp");    // Mac Temp Path
//        // Stream을 읽어 파일을 작성할 fos
        FileOutputStream fos = new FileOutputStream(tempFile, true);
        byte[] fileByte = new byte[4096];
        while ((n = req.getInputStream().read(fileByte, 0, 4096)) != -1) {
            fos.write(fileByte);
            off += 4096;
            if ((lastArr = Integer.parseInt(req.getContentLength()) - off) < 4096) {
                req.getInputStream().read(fileByte, 0, 4096);
                fos.write(fileByte, 0, lastArr);
                break;
            }
        }
        fos.close();

        Map<String, String> boundaryHeader = new LinkedHashMap<String, String>();
        byte[] boundaryNameByte = req.getBoundaryName().getBytes();
        boolean isParse = false;
        boolean init = false;
        int boundaryHeaderPointCheck = 0;
        int boundaryStartPoint = boundaryNameByte.length;
        int boundaryBodyStartPoint = 0;
        int boundaryBodyEndPoint = 0;
        FileInputStream fis = new FileInputStream(tempFile);

        // tmp파일을 읽어 바이너리별 tmp파일을 만들 FIS
        FileInputStream partFile = null;
        String varName ="";
        int ectbyte = 0;
//        TODO: 파일 이름이 있을 때 해당 바운더리의 body데이터 읽어서 임시파일 생성
        while ((n = fis.read(fileByte, 0, 4096)) != -1) {
            if (!isParse) {
                for (int i = 0; i < fileByte.length; i++) {
                    if (boundaryHeaderPointCheck == 4) {
                        partFile = new FileInputStream(tempFile);
                        partFile.skip(boundaryStartPoint);
                        String header = new String(partFile.readNBytes(i -boundaryNameByte.length + ectbyte));
                        String[] headerRow = header.split("\r\n");
//                        for(int ttt=0; ttt<headerRow.length; ttt++){
//                            System.out.println(headerRow[ttt]);
//                        }
                        String[] firstRow = headerRow[1].split("; ");
                        for(int tt = 0; tt<firstRow.length; tt++){
                            System.out.println(firstRow[tt]);
                        }
                        // file Name매칭하여 추가
                        String[] name = firstRow[1].split("=");
                        String[] fileName = firstRow[2].split("=");
                        varName = name[1].substring(1, name[1].length() - 1);   // html에 name으로 정의되어있는 변수명을 명시 할 변수
                        boundaryHeader.put(name[0], name[1].substring(1, name[1].length() - 1));
                        boundaryHeader.put(name[1].substring(1, name[1].length() - 1), fileName[1].substring(1, fileName[1].length() - 1));
                        // Content-Disposition 추가
                        String[] firstRowFirstData = firstRow[0].split(": ");
                        boundaryHeader.put(firstRowFirstData[0], firstRowFirstData[1]);
                        // Content-Type 추가
                        String[] secondRow = headerRow[2].split(": ");
                        boundaryHeader.put(secondRow[0], secondRow[1]);

                        System.out.println(boundaryHeader);

                        boundaryHeaderPointCheck = 0;
                        boundaryBodyStartPoint += i;
                        isParse = true;
                        partFile.close();
                        break;
                    } else if (fileByte[i] == 13 || fileByte[i] == 10) {
                        boundaryHeaderPointCheck++;
                    } else {
                        boundaryHeaderPointCheck = 0;
                    }
                }
            }
            if(isParse && init){
                if (boundaryHeader.get(varName) != null
                        && !boundaryHeader.get(varName).equals("")
                        && !boundaryHeader.get(varName).isEmpty()
                        && !boundaryHeader.get(varName).isBlank()) {
                    for (int i = 0; i < fileByte.length; i++) {
                        if (fileByte[i] == 45 && fileByte[i + 1] == 45) {
                            boundaryBodyEndPoint += i;
                            ectbyte = 4096 - i;
                            partFile = new FileInputStream(tempFile);
                            partFile.skip(boundaryBodyStartPoint);
                            File boundaryTempFile = new File("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\temp\\" + boundaryHeader.get("name") + ".tmp");
                            FileOutputStream boundaryFos = new FileOutputStream(boundaryTempFile);
                            boundaryFos.write(partFile.readNBytes(boundaryBodyEndPoint - boundaryBodyStartPoint));
                            partFile.close();
                            boundaryFos.close();
                            varName ="";
                            boundaryStartPoint = boundaryBodyEndPoint + boundaryNameByte.length;
                            break;
                        }
                    }
                }
                isParse = false;
            }
            init = true;
            boundaryBodyEndPoint += 4096;
        }
    }
}

