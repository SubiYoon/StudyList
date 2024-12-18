package Algorithm.ProgrammersLv0.Day10;

import java.util.Arrays;

public class Ex03 {
    public String solution(String my_string) {
        String answer = "";
        char[] c = new char[my_string.length()];

        for(int i=0; i<my_string.length(); i++){
            c[i]=my_string.toLowerCase().charAt(i);
        }

        Arrays.sort(c);

        for(int i=0; i<my_string.length(); i++){
            answer += c[i];
        }

        return answer;
    }
}
