package Algorithm.ProgrammersLv0.Day03;

import java.util.ArrayList;
import java.util.List;

public class Ex14 {
    public int[] solution(int n) {
        int[] answer = {};
        
        List<Integer> list = new ArrayList<>();
        for(int i=1; i<=n; i++){
            if(i%2 != 0){
                list.add(i);
            }
        }
        
        answer = new int[list.size()];
        
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
