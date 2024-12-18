package Algorithm.ProgrammersLv0.Day09;

public class Ex02 {
    public int solution(int n) {
        int answer = 0;

        for(int i=6; ;i+=6){
            if(i%n==0){
                answer = i;
                break;
            }
        }

        return answer/6;
    }
}
