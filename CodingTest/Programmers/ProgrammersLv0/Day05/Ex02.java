package Algorithm.ProgrammersLv0.Day05;

public class Ex02 {
    public int solution(int n, int t) {
        int answer = n;
        
        for(int i=0; i<t; i++){
            answer*=2;   
        }
        
        return answer;
    }
}