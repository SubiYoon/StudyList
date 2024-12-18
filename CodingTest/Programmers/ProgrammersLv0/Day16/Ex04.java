package Algorithm.ProgrammersLv0.Day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
특이한 정렬
정수 n으로 부터 가장 가까운 수부터 정렬
두 차이가 같을경우 더 큰수를 앞에 오도록 배치
 */
public class Ex04 {
    public int[] solution(int[] numlist, int n) {
        int[] answer = new int[numlist.length];

        //배열을 정렬
        Arrays.sort(numlist);

        //abs값을 List에 담고 정렬
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<numlist.length; i++){
            list.add(Math.abs(n-numlist[i]));
        }

        Collections.sort(list);

        //역순으로 abs값이 같은 numlist의 값을 answer에 삽입
        int beforeNum = 0;
        for(int i=0; i<numlist.length; i++){
            for(int j=numlist.length-1; j>=0; j--){
                if(list.get(i)==Math.abs(numlist[j]-n)){
                    //answer배열에 삽입할 값이 직전이랑 같으면 continue를 사용하여 같은 절대값의 작은 수를 삽입
                    if(beforeNum==numlist[j]){
                        continue;
                    }
                    
                    answer[i]=numlist[j];
                    beforeNum = numlist[j];
                    
                    break;
                }
            }
        }

        return answer;
    }
}
