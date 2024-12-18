package Algorithm.ProgrammersLv0.Day04;

public class Ex07 {
    public int solution(int n) {
        int answer = 2;
        
        if(Math.sqrt(n)%1==0){
            answer = 1;
        }
        
        return answer;
    }
}
