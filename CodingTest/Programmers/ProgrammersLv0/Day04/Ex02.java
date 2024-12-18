package Algorithm.ProgrammersLv0.Day04;

import java.util.Arrays;

public class Ex02 {
    public int solution(int[] array) {
        int answer = 0;
        
        Arrays.sort(array);
        
        answer = array[array.length/2];
        
        return answer;
    }
}
