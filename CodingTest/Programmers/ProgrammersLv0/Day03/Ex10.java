package Algorithm.ProgrammersLv0.Day03;

import java.util.Arrays;

public class Ex10 {
    public int solution(int[] sides) {
        int answer = 0;
        
        Arrays.sort(sides);

        if(sides[2]<sides[1]+sides[0]){
            answer = 1;
        }else answer = 2;
        
        
        return answer;
    }
}
