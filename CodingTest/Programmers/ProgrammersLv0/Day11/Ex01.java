package Algorithm.ProgrammersLv0.Day11;

public class Ex01 {
    public String solution(String my_string) {
        String answer = "";
        answer += my_string.charAt(0);
        int count = 0;
        
        for(int i=1; i<my_string.length(); i++){
            for(int j=0; j<answer.length(); j++){
                if(my_string.charAt(i) == answer.charAt(j)){
                    count++;
                    break;
                }
            }
            if(count == 0){
                answer += my_string.charAt(i);
            }
            count = 0;
        }
        
        return answer;
    }
}
