package Algorithm.ProgrammersLv1.Day08;

import java.util.Arrays;
import java.util.TreeSet;

//나누어 떨어지는 숫자 배열

public class Ex01 {
	public static int[] solution(int[] arr, int divisor) {
		int[] answer = {};
        
		//오름차순정렬 트리셋 이용
		TreeSet<Integer> ts = new TreeSet<>();	
      
		//조건식 성립시 데이터 추가
		for(int i=0; i<arr.length; i++) {
        	if(arr[i]%divisor==0) {
        		ts.add(arr[i]);
        	}
        }
            
		int[] su = new int[ts.size()];
        
        //새로운 배열에 추가
        for(int i=0; i<su.length; i++) {
        	su[i]=ts.pollFirst();
        }
        
        //새로운 배열이 비어있으면 -1 반환
        if(su.length==0) {
        	return answer = new int[] {-1};
        }
        
        //새로운 배열이 비어 있지 않으면 새로운배열 answer에 대입하여 반환
        answer=su;
        return answer;
    }
	
	//테스트
	public static void main(String[] args) {
		int[] arr = {5,9,7,10};
		System.out.println(Arrays.toString(solution(arr, 5)));

	}

}
