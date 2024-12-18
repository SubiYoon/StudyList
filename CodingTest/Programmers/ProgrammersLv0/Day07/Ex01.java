package Algorithm.ProgrammersLv0.Day07;

public class Ex01 {
    public int solution(int[] box, int n) {
        int answer = 0;
        
        answer = (box[0]/n)*(box[1]/n)*(box[2]/n);
        
        return answer;
    }
}
