package Algorithm.ProgrammersLv1.Day08;
//음양 더하기
public class Ex03 {
	 public static int solution(int[] absolutes, boolean[] signs) {
	        int answer = 0;
	        
	        //음수, 양수 구분
	        for(int i=0; i<signs.length; i++) {
	        	if(signs[i]==false) {
	        		absolutes[i] *= -1;
	        	
	        	}
	        }
	        
	        //더하기
	        for(int i=0; i<absolutes.length; i++) {
	        	answer += absolutes[i];
	        }
	        return answer;
	    }
	 
	 //테스트
	public static void main(String[] args) {
		int[] arr1 = {4,7,12};
		boolean[] arr2 = {true, false, true};
		System.out.println(solution(arr1, arr2));

	}

}
