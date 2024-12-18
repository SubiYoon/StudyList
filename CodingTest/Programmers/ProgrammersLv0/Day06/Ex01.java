package Algorithm.ProgrammersLv0.Day06;

public class Ex01 {
    public String solution(String cipher, int code) {
        String answer = "";
        String[] str = cipher.split("");
        
        for(int i=code-1; i<cipher.length(); i+=code){
            answer+=str[i];
        }
        
        
        return answer;
    }
}
