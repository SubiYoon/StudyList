package Algorithm.ProgrammersLv0.Day05;

public class Ex05 {
    public String solution(String rsp) {
        String answer = "";
        
        for(int i=0; i<rsp.length(); i++){
            if(rsp.charAt(i) == '2'){
                answer+=0;
            }else if(rsp.charAt(i) == '0'){
                answer+=5;
            }else if(rsp.charAt(i) == '5'){
                answer+=2;
            }
        }
        
        return answer;
    }
}
