package Algorithm.ProgrammersLv1.Day03;

public class Ex02 {
    public int solution(int n) {
        int answer = 0;
		int save1 = 0;
		int save2 = 0;
		
		
		if(n<=100000000 && n>0) {
			for(int i=100000000; i>=1; i/=10) {
			
				save1 = n/i;
				answer += save1;
				save2 = n-save1*i;
				n = save2;
 	 	    }
		}
        return answer;
    }
}