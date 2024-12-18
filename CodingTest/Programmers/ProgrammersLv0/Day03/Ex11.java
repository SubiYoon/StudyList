package Algorithm.ProgrammersLv0.Day03;

public class Ex11 {
    public String solution(String my_string, String letter) {
        String answer = "";
        String[] str = my_string.split("");
        
        for(int i=0; i<my_string.length(); i++){
            if(str[i].equals(letter)){
                continue;
            }else answer += str[i];
        }
        
        return answer;
    }
}
