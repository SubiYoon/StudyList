package Algorithm.ProgrammersLv1.Day12Day13;
//3진법 뒤집기
public class Ex03 {
	public static int solution(int n) {
		int answer = 0;
		String three = "";
		
		 while(n!=0){
	            three+=n%3; 
	            n=n/3;
	        }
		
		answer = Integer.parseInt(three,3);
		
		return answer;
	}
	public static void main(String[] args) {
		System.out.println(solution(125));

	}

}
