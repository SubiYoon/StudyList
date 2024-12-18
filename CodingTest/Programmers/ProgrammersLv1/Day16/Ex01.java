package Algorithm.ProgrammersLv1.Day16;

import java.util.ArrayList;
//배열의 숫자 3개를 더해 소수의 갯수를 구하라
public class Ex01 {
	public static int solution(int[] nums) {
		int answer = 0;
		int[] sosu = new int[3000];
		
		//소수구하기
        for(int i=1; i<=3000; i++) {
        	sosu[i-1]=i;
        }
        
        sosu[0]=0;
        for(int i=0; i<3000; i++) {
        	for(int j=(i+2)+(i+1); j<3000; j+=(i+2)) {
        		sosu[j]=0;
        	}
        }
        
        //3가지 숫자 합친값 담기
        ArrayList<Integer> al = new ArrayList<>();
        for(int i=0; i<nums.length-2; i++) {
        	for(int j=i+1; j<nums.length-1; j++) {
        		for(int k=j+1; k<nums.length; k++) {
        			al.add(nums[i]+nums[j]+nums[k]);
        		}
        	}
        }
        
        //소수와 비교하기
        for(int i=0; i<3000; i++) {
        	for(int j=0; j<al.size(); j++) {
        		if(al.get(j)==sosu[i]) {
        			
        			//합한 숫자가 소수면 1증가
        			answer++;
        		}
        	}
        }
        return answer;
    }
	
	//테스트
	public static void main(String[] args) {
		int[] arr = {1,2,3,4};
		System.out.println(solution(arr));

	}

}
