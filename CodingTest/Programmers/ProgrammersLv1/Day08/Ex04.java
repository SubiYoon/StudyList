package Algorithm.ProgrammersLv1.Day08;
//없는숫자 더하기
public class Ex04 {

	 public static int solution(int[] numbers) {
	        
		 	int answer = 1+2+3+4+5+6+7+8+9;
	        
		 	//1~9 더한 후 배열에서 있는 번호 빼기
	        for(int i=0; i<numbers.length; i++) {
	        	answer -= numbers[i];
	        }
	        
	        return answer;
	    }
	//테스트
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,6,7,8,0};
		
		System.out.println(solution(arr));

	}

}
