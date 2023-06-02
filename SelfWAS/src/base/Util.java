package base;

import java.io.File;
import java.util.Map;

public class Util {

    /**
     * Get 방식 폴더 조회시 해당 폴더의 파일을 가져오는 메서드
     * terminal에서 ls 명령어와 같은 기능
     * @param folder 폴더명
     * @return 해당 폴더의 List
     */
    public static String[] searchAllFolderList(String folder) {
//        String path = "/Users/dongsubyoon/Downloads" + folder;  //Mac Path
        String path = "C:\\Users\\Ulim\\Desktop\\Downloads" + folder;  //Window Path
        File file = new File(path);
        String[] strs = file.list();

        for (int i = 0; i < strs.length; i++) {
            // 리눅스의 경우 /를 사용하지만 윈도우의 경우 \\로 명시해 주어야 함(path + "/ or \\" + str)
            String separator = new File(path + "/" + strs[i]).isDirectory() ? "/" : "*";
            strs[i] += separator;
        }
        return strs;
    }

    /**
     * @param values Body에서 날라온 데이터를 해석한 String들
     * @param map    해석한 String의 데이터를 삽입해줄 Map
     */
    public static void parameterPut(String[] values, Map map) {
        for (int i = 0; i < values.length; i++) {
            String[] getParameter = values[i].split("=");
            if (getParameter.length > 1) {
                for (int j = 0; j < getParameter.length; j++) {
                    map.put(getParameter[0], getParameter[1]);
                }
            }
        }
    }
}
