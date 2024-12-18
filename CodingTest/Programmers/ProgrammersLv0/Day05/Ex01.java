package Algorithm.ProgrammersLv0.Day05;

import java.util.ArrayList;
import java.util.List;

public class Ex01 {
    public int[] solution(int n, int[] numlist) {
        int[] answer = {};
        
        List<Integer> list = new ArrayList<>();
        
        for(int i=0; i<numlist.length; i++){
            if(numlist[i]%n==0){
                list.add(numlist[i]);
            }
        }
        
        answer = new int[list.size()];
        
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
