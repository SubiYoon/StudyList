package Algorithm.ProgrammersLv0.Day03;

import java.util.Arrays;

public class Ex12 {
    public int solution(int[] numbers) {
        int answer = 0;
        
        Arrays.sort(numbers);
        
        answer = numbers[numbers.length-1]*numbers[numbers.length-2];
        
        return answer;
    }
}
