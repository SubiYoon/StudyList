package Algorithm.ProgrammersLv0.Day11;

import java.util.Arrays;

public class Ex05 {
    public int solution(int[] array, int n) {
        int answer = array[0];
        
        Arrays.sort(array);
        
        for(int i=1; i<array.length; i++){
            int abs = Math.abs(answer-n);
            
            if(array[i]==n){
                answer = array[i];
                break;
            }else if(Math.abs(array[i]-n)==abs){
                answer = array[i]<answer ? array[i] : answer;
            }else if(Math.abs(array[i]-n)<abs){
                answer = array[i];
            }
        }
        
        return answer;
    }
}
