package Algorithm.ProgrammersLv1.Day06;
//n을 나누었을 때 나머지가 1이되는 가장 작은 수
public class Ex04 {
	
	public static int solution(int n) {
		int answer = 0;
		//제한 사항
		if( 3<=n && n<=1000000) {
			for(int i=1; i<n; i++) {
				if(n%i==1) {
					return i;
				}
			}
		}return answer;
	}

	public static void main(String[] args) {
		System.out.println(solution(12));
	}

}
