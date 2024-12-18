package Algorithm.ProgrammersLv0.Day12;

import java.util.Arrays;

public class Ex02 {
    public int[] solution(int[] emergency) {
        int[] answer = new int[emergency.length];
        int[][] num = new int[emergency.length][1];
        int rank = 1;

        for(int i=0; i<emergency.length; i++){
            num[i][0] = emergency[i];
        }
        
        Arrays.sort(emergency);

        for(int i=emergency.length-1 ; i>=0; i--){
            for(int j=0; j<emergency.length; j++){
                if(emergency[i]==num[j][0]){
                    answer[j]=rank++;
                }
            }
        }

        return answer;
    }
}
