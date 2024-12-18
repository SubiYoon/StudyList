package Algorithm.ProgrammersLv1.Day18;

import java.util.Arrays;

//체육복 빌려주기

/*
 학생들의 번호는 체격순
 바로 앞 or 바로 뒷번호만 체육복을 빌려줄 수 있음 ex)4번은 3번 or 5번만 가능
  n - 전체학생수(2명이상 30명 이하)
  lost - 도난당한 놈 번호
  reserve - 체육복 빌려주는 놈
  
 체육복이 있는 학생 수 최댓값 리턴
  
 */
public class Ex01 {
	 public static int solution(int n, int[] lost, int[] reserve) {
		 	Arrays.sort(lost);
	        Arrays.sort(reserve);
	    	
	        //수업 들을 수 있는 학생
	        int answer = n-lost.length;
	        
	        //지가 잃어버리고 여벌옷 입는놈
	        for(int i=0; i<lost.length; i++) {
	        	for(int j=0; j<reserve.length; j++) {
	        		if(lost[i]==reserve[j]) {
	        			//학생의 최대 숫자는 30 차후 계산에서 배제되게 100 대입
	        			lost[i]=reserve[j]=50;
	        			answer++;
	        			break;	
	        		}	
	        	}
	        }
	        
	        //지가 잃어버리고 빌려 입는놈
	        for(int i=0; i<lost.length; i++) {
	        	for(int j=0; j<reserve.length; j++) {
	        		if(lost[i]==reserve[j]-1 || lost[i]==reserve[j]+1) {
	        			//차후 계산에서 배제되게 0 대입
	        			lost[i]=reserve[j]=50;	 
	                    
	        			answer++;
	        			break;
	        		}
	        	}
	        	

	        }
	        return answer;
	 }
	
	public static void main(String[] args) {
		int[] arr1 = {2,4};
		int[] arr2 = {1,3,5};
		System.out.println(solution(5, arr1, arr2));

	}

}
