package Algorithm.ProgrammersLv1.Day09;
//수박수박수박수박수??
public class Ex02 {
	
	 public static String solution(int n) {
	        String answer = "";
	        
	        String su ="수";
	        String park = "박";
	        
	        for(int i=0; i<n; i++) {
	        	if(i%2==0) {
	        		answer+=su;
	        	}else {
	        		answer+=park;
	        	}
	        }
	        
	        return answer;
	    }
	 
	 //테스트
	public static void main(String[] args) {
		System.out.println(solution(3));
	}

}
