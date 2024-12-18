package Algorithm.ProgrammersLv0.Day09;

import java.util.ArrayList;

public class Ex05 {
    public int[] solution(int n) {
        int[] answer = {};

        ArrayList<Integer> list = new ArrayList<>();

        for(int i=1; i<=n/2; i++){
            if(n%i==0){
                list.add(i);
            }
        }
        list.add(n);

        answer = new int[list.size()];

        for(int i=0; i<list.size(); i++){
            answer[i]=list.get(i);
        }

        return answer;
    }
}
