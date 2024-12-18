package Algorithm.ProgrammersLv0.Day03;

public class Ex03 {
    public int solution(int slice, int n) {
        int answer = 0;
        int i = 0;
        end:while(true){
            if(slice*i>=n){
                answer = i;
                break end;
            }
            i++;
        }
        
        return answer;
    }
}
