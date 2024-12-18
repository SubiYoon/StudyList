package Algorithm.ProgrammersLv0.Day13;

import java.util.ArrayList;
import java.util.List;

public class Ex03 {
    public int[] solution(int n) {
        int[] answer = {};

        //소수인 수를 담을 List
        List<Integer> list1 = new ArrayList<Integer>();

        //n까지 소수인 수를 구하기
        for(int i=2; i<=n; i++){
            int count = 0;
            for(int j=2; j<=i; j++){
                if(i%j==0){
                    count++;
                }
                if(count==2){
                    break;
                }
            }
            if(count==1){
                list1.add(i);
            }
        }
        //소인수를 담을 List
        List<Integer> list2 = new ArrayList<Integer>();
        
        //n에 구한 소수들을 나눈 후 나머지가 0인지 확인하여 List에 담기
        for(int i=0; i<list1.size(); i++){
            if(n%list1.get(i)==0){
                list2.add(list1.get(i));
            }
        }
        //담은 List의 수를 배열로 옮김
        answer = new int[list2.size()];

        for(int i=0; i<answer.length; i++){
            answer[i] = list2.get(i);
        }

        return answer;
    }
}
