package Algorithm.ProgrammersLv2;

import java.util.Arrays;

/*
구명보트
최대 2명씩, 무게제한 있음
필요한 구명보트의 개수의 최솟값을 return
 */
public class Lifeboat {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int i = 0;
        //오름차순 정렬
        Arrays.sort(people);
        
        for(int j=people.length-1; j>=i; j--){
            if(people[i]+people[j]<=limit){
                answer++;
                i++;
            }
            answer++;
        }

        return answer;
    }
}
