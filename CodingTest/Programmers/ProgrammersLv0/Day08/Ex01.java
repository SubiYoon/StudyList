package Algorithm.ProgrammersLv0.Day08;

import java.util.Iterator;
import java.util.TreeSet;

public class Ex01 {
    public int[] solution(int[] array) {
        int[] answer = new int[2];
        
        TreeSet<Integer> set = new TreeSet<>();
        for(int i=0; i<array.length; i++){
            set.add(array[i]);
        }
        
        Iterator<Integer> it = set.iterator();
        while(it.hasNext()){
            answer[0] = it.next();
            
        }

        for(int i=0; i<array.length; i++){
            if(answer[0]==array[i]){
                answer[1]=i;
            }
        }

        return answer;
    }
}
