package Algorithm.ProgrammersLv0.Day14;

import java.util.Arrays;

public class Ex04 {
    public int solution(int[] sides) {
        int answer = 0;

        Arrays.sort(sides);

        for(int i=sides[1]; i<sides[0]+sides[1]; i++){
            answer++;
        }

        for(int i=sides[1]-sides[0]+1; i<sides[1]; i++){
            answer++;
        }

        return answer;
    }
}
