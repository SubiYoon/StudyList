package Algorithm.ProgrammersLv0.Day08;

public class Ex02 {
    public int[] solution(int[] numbers, String direction) {
        int[] answer = new int[numbers.length];
        
        if(direction.equals("right")){
            for(int i=numbers.length-2; i>=0; i--){
                answer[i+1]=numbers[i];
            }
            answer[0]=numbers[numbers.length-1];
        }else {
            for(int i=0; i<numbers.length-1; i++){
                answer[i]=numbers[i+1];
            }
            answer[numbers.length-1]=numbers[0];
        }
        
        return answer;
    }
}
