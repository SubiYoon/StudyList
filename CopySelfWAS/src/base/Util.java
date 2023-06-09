package base;

import http.HttpPieRequest;

import java.io.*;
import java.nio.file.Files;
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

        /*boundary를 줄여 bd라고 작성*/
        Map<String, String> bdHeader = new LinkedHashMap<String, String>();
        byte[] bdNameByte = req.getBoundaryName().getBytes();
        byte[] buff = new byte[4096];
        boolean isParse = false;
        boolean isDirect = false;
        int skipPoint = 0;
        int bdHeaderEndPoint = 0;
        int readCount = 0;
        int bdNameCheckCount = 0;
        int off2 = 0;
        int skipPointAdd = 0;
        int writeLength = 0;
        long LoofEndCheck = tempFile.length();
        String varName = "";
        ttt:
        while (true) {
            if (LoofEndCheck - skipPoint < bdNameByte.length + 10) {
                break;
            }
            try (FileInputStream fis = new FileInputStream(tempFile);) {
                if (skipPoint != 0) {
                    fis.skip(skipPoint);
                }
                while ((n = fis.read(buff, 0, 4096)) != -1) {
                    if (!isParse) {
                        readCount = 0;
                        for (int i = 0; i < buff.length; i++) {
                            if (bdHeaderEndPoint == 4) {
                                try (FileInputStream headerFis = new FileInputStream(tempFile);) {
                                    headerFis.skip(skipPoint);
                                    String headerStr = new String(headerFis.readNBytes(readCount));

                                    System.out.println(headerStr);

                                    String[] bdHeaderRow = headerStr.split("\r\n");
                                    String[] firstRow = bdHeaderRow[1].split("; ");
                                    varName = firstRow[1].split("=")[1].substring(1, firstRow[1].split("=")[1].length() - 1);
                                    bdHeader.put(varName + "-" + firstRow[0].split(": ")[0], firstRow[0].split(": ")[1]);
                                    bdHeader.put(firstRow[1].split("=")[0], firstRow[1].split("=")[1].substring(1, firstRow[1].split("=")[1].length() - 1));
                                    bdHeader.put(firstRow[1].split("=")[1].substring(1, firstRow[1].split("=")[1].length() - 1), firstRow[2].split("=")[1].substring(1, firstRow[2].split("=")[1].length() - 1));
                                    bdHeader.put(varName + "-" + bdHeaderRow[2].split(": ")[0], bdHeaderRow[2].split(": ")[1]);

                                    skipPoint += skipPointAdd;
                                    skipPointAdd = 0;
                                    bdHeaderEndPoint = 0;
                                    isParse = true;
                                }
                                break;
                            } else if (buff[i] == 13 || buff[i] == 10) {
                                bdHeaderEndPoint++;
                            } else {
                                bdHeaderEndPoint = 0;
                            }
                            readCount++;
                            skipPointAdd++;
                        }
                        isDirect = true;
                    }
                    if (isParse
                            && bdHeader.get(bdHeader.get("name")) != null
                            && !bdHeader.get(bdHeader.get("name")).equals("")
                            && !bdHeader.get(bdHeader.get("name")).isEmpty()) {
                        if (isDirect) {
                            for (int i = readCount; i < buff.length; i++) {
                                writeLength++;
                                if (bdNameCheckCount == bdNameByte.length) {
                                    File partFile = new File("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\temp\\" + bdHeader.get("name") + ".tmp");
                                    try (FileInputStream partFis = new FileInputStream(tempFile);
                                    FileOutputStream partFos = new FileOutputStream(partFile);) {
                                        partFis.skip(skipPoint);
                                        partFos.write(partFis.readNBytes(writeLength - bdNameByte.length - 1));

                                        skipPoint += writeLength - bdNameByte.length;
                                        isParse = false;
                                        bdNameCheckCount = 0;
                                        off2 = 0;
                                        writeLength = 0;

                                    }
                                    continue ttt;
                                } else if (buff[i] == bdNameByte[off2++]) {
                                    bdNameCheckCount++;
                                } else {
                                    off2 = 0;
                                    bdNameCheckCount = 0;
                                }
                            }
                        } else {
                            for (int i = 0; i < buff.length; i++) {
                                writeLength++;
                                if (bdNameCheckCount == bdNameByte.length) {
                                    File partFile = new File("C:\\Users\\Ulim\\Desktop\\Downloads\\ServerRoot\\temp\\" + bdHeader.get("name") + ".tmp");
                                    try (FileInputStream partFis = new FileInputStream(tempFile);
                                    FileOutputStream partFos = new FileOutputStream(partFile);) {
                                        partFis.skip(skipPoint);
                                        partFos.write(partFis.readNBytes(writeLength - bdNameByte.length - 1));

                                        skipPoint += writeLength - bdNameByte.length;
                                        isParse = false;
                                        bdNameCheckCount = 0;
                                        off2 = 0;
                                        writeLength = 0;
                                    }
                                    continue ttt;
                                } else if (buff[i] == bdNameByte[off2++]) {
                                    bdNameCheckCount++;
                                } else {
                                    off2 = 0;
                                    bdNameCheckCount = 0;
                                }
                            }
                        }
                    }
                    if (isParse
                            && (bdHeader.get(bdHeader.get("name")).equals("")
                            || bdHeader.get(bdHeader.get("name")).isEmpty()
                            || bdHeader.get(bdHeader.get("name")) == null)) {
                        isParse = false;
                        bdNameCheckCount = 0;
                        off2 = 0;
                        skipPoint += 2;
                        continue ttt;
                    }
                    isDirect = false;
                }
            }
        }
        req.setRequestFile(bdHeader);
        tempFile.delete();
    }
}

