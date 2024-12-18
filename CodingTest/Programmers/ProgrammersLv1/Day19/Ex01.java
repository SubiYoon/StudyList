package Algorithm.ProgrammersLv1.Day19;

import java.util.Arrays;

/*
	완주하지 못한 선수
	
	한명빼고 모두가 완주함
	
	완주하지 못한 놈은?
	
	
 */
public class Ex01 {
	public static String solution(String[] participant, String[] completion) {
       String answer = "";
       
       //정렬
       Arrays.sort(participant);
       Arrays.sort(completion);
       
       System.out.println(Arrays.toString(participant));
       
       //정렬한 데이터 값의 순서가 일치 하지 않으면 완주하지 못한놈
       for(int i=0; i<completion.length; i++) {
    	   if(!completion[i].equals(participant[i])){
    		   answer = participant[i];
    		   break;
    	   }else answer = participant[participant.length-1];
       }
        
        return answer;
    }
	
	//테스트
	public static void main(String[] args) {
		String[] arr1 = {"leo", "kiki", "eden"};
		String[] arr2 = {"eden", "kiki"};
		System.out.println(solution(arr1, arr2));
	}

}
