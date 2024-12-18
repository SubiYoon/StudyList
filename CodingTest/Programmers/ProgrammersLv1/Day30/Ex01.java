package Algorithm.ProgrammersLv1.Day30;

import java.util.Deque;
import java.util.LinkedList;

/*
카드 뭉치
두개의 영단어 뭉치
원하는 카드뭉치에서 카드를 순서대로 한장씩 사용(재사용 불가)
카드를 사용하지 않고 다음 카드로 넘어갈 수 없음
카드 뭉치 순서변경 불가능
goal의 문장을 만들 수 있으면 Yes를 반환 없으면 No를 반환
 */
public class Ex01 {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        //카드 1,2에서 firstPoll한 녀석들 잠깐 보관할 String
        String poll1 = "";
        String poll2 = "";

        //카드 1,2를 Deque에 넣음
        Deque<String> dq1 = new LinkedList<>();
        Deque<String> dq2 = new LinkedList<>();

        for(int i=0; i<cards1.length; i++){
            dq1.add(cards1[i]);
        }
        for(int i=0; i<cards2.length; i++){
            dq2.add(cards2[i]);
        }

        //Deque를 사용하여 빼고 넣음
        for(int i=0; i<goal.length; i++){
            if(!dq1.isEmpty()){
                poll1 = dq1.pollFirst();
                //같으면 계속
                if(poll1.equals(goal[i])){
                    continue;
                //틀리면 다시 앞에 집어 넣음
                }else {
                    dq1.addFirst(poll1);
                }
            }

            if(!dq2.isEmpty()){
                poll2 = dq2.pollFirst();
                //같으면 계속
                if(poll2.equals(goal[i])){
                    continue;
                }
            }
            //틀리면 No리턴
            return "No";
        }
        return answer;
    }
}
