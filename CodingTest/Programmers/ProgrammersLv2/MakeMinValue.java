package Algorithm.ProgrammersLv2;

import java.util.Arrays;

/*
최솟값 만들기
같은 길이의 배열 A,B
자연수만 담겨져 있음
A,B 배열의 원소를 1:1로 곱하고 더했을 때
최솟값을 만들어 return
 */
public class MakeMinValue {
    public int solution(int[] A, int[] B){
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);
        
        for(int i=0; i<A.length; i++){
            answer += A[i] * B[B.length-1-i];
        }

        return answer;
    }
}
