package Algorithm.ProgrammersLv0.Day11;

public class Ex02 {
    public int solution(int n) {
        int answer = 0;

        for(int i=1; ; i++){
            if(n<p(i)){
                answer = i-1;
                break;
            }
        }

        return answer;
    }

    public int p(int i){
        if(i==1){
            return 1;
        }
        return i*p(i-1);
    }
}
