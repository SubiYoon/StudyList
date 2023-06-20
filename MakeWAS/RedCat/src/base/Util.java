package base;

import http.HttpPieRequest;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Util {
    /**
     * 해당 경로(해당 폴더)에 있는 파일들을 검색해주는 메서드 'ls'와 같은 기능
     * 폴더는 맨뒤에 '/'
     * 파일은 맨뒤에 '*'
     *
     * @param path 검색할 폴더 명
     * @return 해당 폴더에 있는 파일들
     */
    public static String[] folderSearch(String path) {
        System.out.println(HttpPieRequest.httpHeader.get("Url"));
        String searchFoler = "/Users/dongsubyoon/Develop/StudyList/MakeWAS" + path;  //Mac Path
//        String searchFoler = "C:\\Users\\Ulim\\Desktop\\Downloads" + path;  //Window Path
        File file = new File(searchFoler);
        String[] folderList = file.list();
        for (int i = 0; i < folderList.length; i++) {
            String endPoint = new File(searchFoler + "/" + folderList[i]).isDirectory() ? "/" : "*";    //Mac Path
//            String endPoint = new File(searchFoler + "\\" + folderList[i]).isDirectory() ? "/" : "*";   //Window Path
            folderList[i] += endPoint;
        }
        return folderList;
    }

    /**
     * boundaryName을 기준으로 각 바운더리마다 tmp파일을 따로 저장
     *
     * @param req HttpPieRequest
     */
    public static void makeTempFile(HttpPieRequest req) {
        int n;
        String partFileName = "";
        File tempFile = new File("/Users/dongsubyoon/Develop/StudyList/MakeWAS/serverRoot/temp/" + HttpPieRequest.httpHeader.get("BoundaryName") + ".tmp");   //Window Path
//        File tempFile = new File("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\temp\\" + HttpPieRequest.httpHeader.get("BoundaryName") + ".tmp");   //Window Path
        try (
                FileOutputStream fos = new FileOutputStream(tempFile, true);
        ) {
            InputStream in = req.getInputStream();
            int len = 4096;
            byte[] buff = new byte[len];
            //바이너리 코드를 tmp파일로 만들어 저장
            while ((n = in.read(buff, 0, len)) != -1) {
                if (n < len) {
                    fos.write(Arrays.copyOf(buff, n));
                    break;
                }
                fos.write(buff);
            }

            Map<String, String> fileData = new LinkedHashMap<String, String>();
            boolean isParse = false;
            boolean isDirect = false;
            int bdHeaderCheck = 0;
            int bdBodyCheck = 0;
            int readCount = 0;
            int bdBodyOff = 0;
            int index = 0;
            int skip = 0;
            String varName = "";
            byte[] boundaryNameByte = HttpPieRequest.httpHeader.get("BoundaryName").getBytes();
            ttt:
            while (true) {
                bdBodyOff = 0;
                if (Integer.parseInt(HttpPieRequest.httpHeader.get("Content-Length")) - skip <= boundaryNameByte.length + 6) {
                    break;
                }
                try (
                        FileInputStream fis = new FileInputStream(tempFile);
                ) {
                    fis.skip(skip);
                    while ((n = fis.read(buff, 0, len)) != -1) {
                        if (!isParse) {
                            readCount = 0;
                            for (int i = 0; i < buff.length; i++) {
                                if (bdHeaderCheck == 4) {
                                    try (FileInputStream headerParsingFis = new FileInputStream(tempFile);) {
                                        headerParsingFis.skip(skip);
                                        String bdHeader = new String(headerParsingFis.readNBytes(readCount));
                                        String[] rowData = bdHeader.split("\r\n");
                                        String[] firstRow = rowData[1].split("; ");
                                        varName = firstRow[1].split("=")[1].substring(1, firstRow[1].split("=")[1].length() - 1);
                                        // 변수명과 파일명을 key : value로 설정
                                        fileData.put(firstRow[1].split("=")[1].substring(1, firstRow[1].split("=")[1].length() - 1), firstRow[2].split("=")[1].substring(1, firstRow[2].split("=")[1].length() - 1));
                                        fileData.put(firstRow[0].split(": ")[0], firstRow[0].split(": ")[1]);
                                        fileData.put(rowData[2].split(": ")[0], rowData[2].split(": ")[1]);
                                        for (int r = 1; r < 3; r++) {
                                            fileData.put(firstRow[r].split("=")[0], firstRow[r].split("=")[1].substring(1, firstRow[r].split("=")[1].length() - 1));
                                        }

                                        skip += readCount;
                                        isParse = true;
                                        bdHeaderCheck = 0;
                                        break;
                                    }
                                } else if (buff[i] == 10 || buff[i] == 13) {
                                    bdHeaderCheck++;
                                } else {
                                    bdHeaderCheck = 0;
                                }
                                readCount++;
                            }
                            isDirect = true;
                        }
                        if (isParse
                                && !fileData.get(varName).equals("")
                                && !fileData.get(varName).isEmpty()
                                && fileData.get(varName) != null) {
                            if (!isDirect) {
                                for (int i = 0; i < buff.length; i++) {
                                    if (bdBodyCheck == boundaryNameByte.length) {
                                        File file = new File("/Users/dongsubyoon/Develop/StudyList/MakeWAS/serverRoot/temp/" + varName + ".tmp"); //Mac Path
//                                        File file = new File("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\temp\\" + varName + ".tmp"); //Window Path
                                        try (FileInputStream makePartFis = new FileInputStream(tempFile);
                                             FileOutputStream partFos = new FileOutputStream(file);) {
                                            makePartFis.skip(skip);
                                            partFos.write(makePartFis.readNBytes(bdBodyOff - boundaryNameByte.length));

                                            skip += bdBodyOff - boundaryNameByte.length;
                                            varName = "";
                                            bdBodyCheck = 0;
                                            index = 0;
                                            isParse = false;
                                            continue ttt;
                                        }
                                    } else if (buff[i] == boundaryNameByte[index++]) {
                                        bdBodyCheck++;
                                    } else {
                                        bdBodyCheck = 0;
                                        index = 0;
                                    }
                                    bdBodyOff++;
                                }
                            } else {
                                for (int i = readCount; i < buff.length; i++) {
                                    if (bdBodyCheck == boundaryNameByte.length) {
                                        File file = new File("/Users/dongsubyoon/Develop/StudyList/MakeWAS/serverRoot/temp/" + varName + ".tmp"); //Mac Path
//                                        File file = new File("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\temp\\" + varName + ".tmp"); //Window Path
                                        try (FileInputStream makePartFis = new FileInputStream(tempFile);
                                             FileOutputStream partFos = new FileOutputStream(file);) {
                                            makePartFis.skip(skip);
                                            partFos.write(makePartFis.readNBytes(bdBodyOff - boundaryNameByte.length));

                                            skip += bdBodyOff - boundaryNameByte.length;
                                            varName = "";
                                            bdBodyCheck = 0;
                                            index = 0;
                                            isParse = false;
                                            continue ttt;
                                        }
                                    } else if (buff[i] == boundaryNameByte[index++]) {
                                        bdBodyCheck++;
                                    } else {
                                        bdBodyCheck = 0;
                                        index = 0;
                                    }
                                    bdBodyOff++;
                                }
                            }
                        }
                        if (isParse && fileData.get(varName).equals("")) {
                            index = 0;
                            bdBodyCheck = 0;
                            skip += 2;
                            isParse = false;
                            continue ttt;
                        }
                        isDirect = false;
                    }
                }
            }
            HttpPieRequest.setFile(fileData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            tempFile.delete();
        }
    }
}
