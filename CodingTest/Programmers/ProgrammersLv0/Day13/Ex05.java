package Algorithm.ProgrammersLv0.Day13;

import java.util.ArrayList;
import java.util.List;

public class Ex05 {
    public String[] solution(String my_str, int n) {
        String[] answer = {};
        
        List<String> list = new ArrayList<String>();

        while(true){
            String str = "";

            if(my_str.length() <= n){
                list.add(my_str);
                break;
            }
            //n개 만큼 my_str을 자름
            str = my_str.substring(0, n);
            
            //자른 부분을 날림
            my_str = my_str.replaceFirst(str, "");

            list.add(str);
        }

        //배열에 삽입
        answer= new String[list.size()];

        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}
