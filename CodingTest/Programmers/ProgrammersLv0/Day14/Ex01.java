package Algorithm.ProgrammersLv0.Day14;

import java.util.ArrayList;
import java.util.List;

public class Ex01 {
    public int solution(String s) {
        int answer = 0;

        //공백을 기준으로 배열을 나눔
        String[] str = s.split(" ");

        //Z인덱스와 그 앞 인덱스를 List에 담음
        List<Integer> index = new ArrayList<Integer>();
        for(int i=0; i<str.length; i++){
            if(str[i].equals("Z")){
                index.add(i);
                index.add(i-1);
            }
        }

        //List에 담은 index와 str의 인덱스가 같지 않을때만 더함
        frist:for(int i=0; i<str.length; i++){
            for(int j=0; j<index.size(); j++){
                if(i==index.get(j)){
                    continue frist;
                }
            }
            answer += Integer.parseInt(str[i]);
        }

        return answer;
    }
}
