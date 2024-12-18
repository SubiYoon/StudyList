package Algorithm.ProgrammersLv2;

import java.util.Stack;

/*
짝지어 제거하기
s안에 소문자만 존재
같은 문자가 두번 연속해서 나올 시 해당 문자 제거
제거 후에 같은문자 두번 연속해서 나온게 있으면 제거
위 과정을 반복
모두 제거되면 return 1, 아니면 return 0
 */
public class DeleteCouple {
    public int solution(String s) {
        int answer = 0;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (stack.size() != 0) {
                if (stack.peek() == s.charAt(i)) {
                    stack.pop();
                } else {
                    stack.push(s.charAt(i));
                }
            } else {
                stack.push(s.charAt(i));
            }
        }

        if (stack.size() == 0) {
            return 1;
        }

        return answer;
    }
}