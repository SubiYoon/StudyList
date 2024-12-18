package Algorithm.ProgrammersLv0.Day03;

public class Ex08 {
    public int solution(int n) {
        int answer = 0;
        
        while(true){
            if(n<=7*answer){
                break;
            }
            answer++;
        }
        
        return answer;
    }
}
