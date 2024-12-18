package Algorithm.ProgrammersLv0.Day16;

import java.util.Deque;
import java.util.LinkedList;

//문자열 밀기
public class Ex03 {
    public int solution(String A, String B) {
        //문자열을 답을 Queue
        Deque<Character> dque = new LinkedList<>();
        

        for(int i=0; i<A.length(); i++){
            dque.add(A.charAt(i));
        }

        //A와 B가 같을 경우
        if(A.equals(B)){
            return 0;
        }

        //몇번 반복해야 하는지 카운트
        int result = 0;
        int strLength = A.length();
        String str = A;
        while(true){
            char poll = 'a';
            if(str.equals(B)){
                return result;    
            }else if(strLength==result){
                return -1;
            }

            //오른쪽으로 한칸씩 밀기
            poll = dque.pollLast();
            dque.addFirst(poll);

            //B와 비교할 새로운 str 생성
            str="";

            for(int i=0; i<A.length(); i++){
                poll=dque.pollFirst();
                str+=poll;
                dque.addLast(poll);
            }
            result++;
        }
    }
}
