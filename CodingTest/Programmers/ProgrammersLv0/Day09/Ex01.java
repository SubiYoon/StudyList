package Algorithm.ProgrammersLv0.Day09;

public class Ex01 {
    public String solution(int age) {
        String answer = "";
        String num = "";
        num += age;
        
        for(int i=0; i<num.length(); i++){
            answer += (char)(((int)num.charAt(i))+49);
        }

        return answer;
    }
}
