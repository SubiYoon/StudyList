package Algorithm.ProgrammersLv0.Day02;

public class Ex02 {
    public int solution(int n, int k) {
        int answer = 0;
        
        answer = 12000*n + 2000*k;
        
        if(n>=10){
            answer -= (n/10)*2000;
        }
        
        return answer;
    }
}
