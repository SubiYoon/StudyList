package Algorithm.ProgrammersLv0.Day03;

public class Ex07 {
    public int[] solution(int money) {
        int[] answer = new int[2];
        
        answer[0] = money/5500;
        answer[1] = money%5500;
        
        return answer;
    }
}
