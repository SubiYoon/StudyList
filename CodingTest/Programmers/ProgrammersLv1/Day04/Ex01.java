package Algorithm.ProgrammersLv1.Day04;

public class Ex01 {
	public long solution(long n) {
		long answer = 0;
	        
		if(0<n && n<50000000000000l) {
			for(int i=1; i<7100000; i++) {
				if((long)i*i==n) {
					return (long)(i+1)*(i+1);
				}
			}answer=-1l;
		}
		return answer ;
	}
}