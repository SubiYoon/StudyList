package Algorithm.ProgrammersLv0.Day07;

import java.util.Arrays;

public class Ex02 {
    public int[] solution(String my_string) {
        int[] answer = {};
        String str = my_string.replaceAll("[a-zA-Z]", "");
        
        answer = new int[str.length()];

        for(int i=0; i<str.length(); i++){
            answer[i] = Integer.parseInt(str.split("")[i]);
        }
        
        Arrays.sort(answer);

        return answer;
    }
}
