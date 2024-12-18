package Algorithm.ProgrammersLv2;

/*
올바른 암호
() <- 정상적인 괄호
)( <- 비정상적인 괄호
정상적인 괄호가 있을시 그 괄호를 제거해도 정상적인 괄호만 있는지를 return
 */
public class CollectParenthesis {
    boolean solution(String s) {
        boolean answer = false;
        
        int count = 0;
        
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='('){
                count++;
            }else {
                count--;
                if(count < 0){
                    return false;
                }
            }
        }
        
        if(count==0){
            return true;
        }

        return answer;
    }
}
