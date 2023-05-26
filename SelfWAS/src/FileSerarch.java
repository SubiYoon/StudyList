import java.io.File;

public class FileSerarch {
    public String[] searchAllFolderList(String folder) {
        //String path = "/Users/dongsubyoon/Downloads" + folder;  //Mac Path
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
}
