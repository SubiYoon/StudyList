package Algorithm.ProgrammersLv2;

import java.util.LinkedList;
import java.util.Queue;

/*
N개의 최소공배수
arr에 있는 수들의 최소공배수를 구하여 return
 */
public class LCM {
    public int solution(int[] arr) {
        int answer = 1;
        int check = 0;
        int num = 0;

        //공약수를 담을 queue
        Queue<Integer> q = new LinkedList<>();
        for(int i=2; i<100; i++){
            for(int j=0; j<arr.length; j++){
                //공약수가 최소 하나라도 있을 경우 해당 수를 q에 넣기
                if(arr[j]%i==0){
                    check++;
                    arr[j] /= i;
                    num = i;
                }
            }
            if(check>0){
                q.offer(num);
                check = 0;
                i=1;    //공약수를 넣었으면 다시 1부터 공약수 다시 찾기
            }
        }
        
        while(!q.isEmpty()){
            answer *= q.poll();
        }
        
        return answer;
    }
}
