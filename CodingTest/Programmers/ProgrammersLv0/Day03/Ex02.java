package Algorithm.ProgrammersLv0.Day03;

public class Ex02 {
    public int[] solution(String[] strlist) {
        int[] answer = new int[strlist.length];
        
        for(int i=0; i<strlist.length; i++){
            answer[i] = strlist[i].length();
        }
        
        return answer;
    }
}
