package Algorithm.ProgrammersLv0.Day02;

public class Ex01 {
    public int solution(int[] array, int height) {
        int answer = 0;
        
        for(int i=0; i<array.length; i++){
            if(height<array[i]){
                answer++;
            }
        }
        
        return answer;
    }
}
