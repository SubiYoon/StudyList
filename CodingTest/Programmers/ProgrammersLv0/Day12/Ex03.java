package Algorithm.ProgrammersLv0.Day12;

import java.util.Arrays;

public class Ex03 {
    public String solution(String s) {
        String answer = "";

        //분리한 String이 두번이상 있는지 카운트
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if(s.charAt(i)==s.charAt(j)){
                    count++;
                }
                //두번 이상이면 break
                if(count==2){
                    break;
                }
            }
            //한번이면 answer에 추가
            if(count==1){
                answer += s.charAt(i);
            }
            count=0;
        }
        
        //사전순으로 정렬
        if(answer.length()>1){
            String[] sort = answer.split("");
            Arrays.sort(sort);
            answer = "";

            for(int i=0; i<sort.length; i++){
                answer += sort[i];
            }
        }
        return answer;
    }
}