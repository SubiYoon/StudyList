package Algorithm.ProgrammersLv0.Day11;

import java.util.Arrays;

public class Ex03 {
    public int solution(String before, String after) {
        int answer = 0;
        char[] c1 = new char[before.length()];
        char[] c2 = new char[after.length()];

        for(int i=0; i<before.length(); i++){
            c1[i] = before.charAt(i);
        }

        for(int i=0; i<after.length(); i++){
            c2[i] = after.charAt(i);
        }
        
        Arrays.sort(c1);
        Arrays.sort(c2);
        
        if(c1.length==c2.length){
            for(int i=0; i<after.length(); i++){
                if(c1[i] != c2[i]){
                    return answer = 0;
                }
            }
            answer = 1;
        }

        return answer;
    }
}
