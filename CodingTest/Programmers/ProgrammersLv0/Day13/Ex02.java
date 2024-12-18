package Algorithm.ProgrammersLv0.Day13;

import java.util.LinkedList;
import java.util.Queue;

public class Ex02 {
    public int solution(int[] numbers, int k) {
        int answer = 0;

        Queue<Integer> q = new LinkedList<Integer>();
        //numbers.length/k >= 2 그냥 계산
        if(numbers.length/k >= 2){
            qAdd(numbers, q);
        }

        //1<= numbers.length/k < 2 큐에 1번더 추가해서 계산
        else if(1 <= numbers.length/k && numbers.length/k < 2){
            qAdd(numbers, q);
            qAdd(numbers, q);
        }


        //numbers.length/k < 1 결과에 역수 번을 큐에 추가해서 계산
        else if(numbers.length/k < 1){
            for(int i=0; i<(2*k/numbers.length)+1; i++){
                qAdd(numbers, q);
            }
        }

        for(int i = 0; i<k-1; i++){
            q.poll();
            q.poll();
        }

        answer = q.peek();

        return answer;
    }

    public void qAdd(int[] numbers, Queue<Integer> q){
        for(int i=0; i<numbers.length; i++){
            q.add(numbers[i]);
        }
    }
}
