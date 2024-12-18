package Algorithm.ProgrammersLv2;

import java.util.Stack;

/*
괄호 회전하기
(),{},[]는 올바른 괄호
문자열 s의 크기-1만큼 왼쪽으로 회전 했을 때
각 경우마다 전부 올바른 괄호가 나올 수 있는 경우의 수 x를 return
 */
public class Rocateparenthesis {
    public int solution(String s) {
        int answer = 0;
        
        StringBuilder sb = new StringBuilder(s);

        for(int k=0; k<s.length(); k++){
            Stack<Character> stack = new Stack<>();
            for(int i=0; i<sb.length(); i++){
                if(stack.isEmpty()){
                    stack.push(sb.charAt(i));
                }else {
                    if(stack.peek()=='(' && stack.peek()==sb.charAt(i)-1){
                        stack.pop();
                    }else if(stack.peek()==sb.charAt(i)-2){
                        stack.pop();
                    }else {
                        stack.push(sb.charAt(i));
                    }
                }
                
            }
            if(stack.size()==0){
                answer++;
            }

            char c = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(c);
        }

        return answer;
    }
}
