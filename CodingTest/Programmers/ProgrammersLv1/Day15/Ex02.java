package Algorithm.ProgrammersLv1.Day15;

import java.util.HashSet;

//포켓몬

/*
 	홍박사가 N/2마리 가져가도 좋다~
 	같은종류의 포켓몬 같은 번호
 	최대한 많은 종류의 포켓몬 몇종류 가질 수 있냐?
 */
public class Ex02 {
	public static int solution(int[] nums) {
        int answer = 0;
        
        //중복 제거를 위한 트리셋
        HashSet<Integer> hs = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
        	hs.add(nums[i]);
        }
        
        //크기가 같거나 작으면 ts 반환
        if(hs.size()<=nums.length/2) {
        	answer = hs.size();
        	
        //크기가 크면 nums의 길이만큼만 반환
        }else if(hs.size()>nums.length/2) {
        	answer=nums.length/2;
        }
        
        
        return answer;
    }
	
	//테스트
	public static void main(String[] args) {
		int[] arr = {3,1,2,3};
		System.out.println(solution(arr));

	}

}