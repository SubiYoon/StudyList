package Algorithm.ProgrammersLv0.Day04;

public class Ex10 {
    public String solution(String my_string) {
        String answer = "";
        String except = "aeiou";
        
        for(char c : except.toCharArray()){
            my_string = my_string.replace(String.valueOf(c),"");
        }
        
        answer = my_string;
        
        return answer;
    }
}
