package Algorithm.ProgrammersLv1.Day18;

import java.util.Arrays;

/*
  로또의 최고 순위와 최저 순위
  
  로또 번호 6개 중 모르는 번호가 존재
  모르는 번호는 0으로 표기
  일치 수 - 등수 : 6-1, 5-2, 4-3, 3-2, 2-5, 1-낙첨 
  
 */
public class Ex02 {
	public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        int count = 0;
        int max_count = 0;
        //맞춘 숫자 갯수
        for(int i= 0; i<6; i++) {
        	for(int j=0; j<6; j++) {
        		if(lottos[i]==win_nums[j]) {
        			count++;
        		}
        	}
        }
        //맞춘 숫재 갯수를 최대 맞출 수 있는 숫자 갯수에 대입
        max_count = count;
        
        //모르는 숫자가 맞았을경우 최대 맞출 수 있는 숫자 증가
        for(int i=0; i<6; i++) {
        	if(lottos[i]==0) {
        		max_count++;
        	}
        }
        
        switch(count) {
        case 0 : answer[1]=6; break;
        case 1 : answer[1]=6; break;
        case 2 : answer[1]=5; break;
        case 3 : answer[1]=4; break;
        case 4 : answer[1]=3; break;
        case 5 : answer[1]=2; break;
        case 6 : answer[1]=1; break;
        }
        
        switch(max_count) {
        case 0 : answer[0]=6; break;
        case 1 : answer[0]=6; break;
        case 2 : answer[0]=5; break;
        case 3 : answer[0]=4; break;
        case 4 : answer[0]=3; break;
        case 5 : answer[0]=2; break;
        case 6 : answer[0]=1; break;
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		int[] arr1 = {44,1,0,0,31,25};
		int[] arr2 = {31,10,45,1,6,19};
		System.out.println(Arrays.toString(solution(arr1, arr2)));	//[3,5]
	}

}
