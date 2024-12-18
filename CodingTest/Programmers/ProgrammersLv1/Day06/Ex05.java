package Algorithm.ProgrammersLv1.Day06;
//두 정수 a,b사이의 합
public class Ex05 {
	public static long solution(int a, int b) {
		long answer =0;
			//두 수가 같을때 반환되는 값
			if(a==b) answer = a;
			//제한 조건
			else if(-10000000<=a && a<=10000000 && -10000000<=b && b<=10000000) {
				//a,b사이의 합
				if(a<b) {
					for(int i=a; i<=b; i++) {
						answer+=i;
					}
				}else {
					for(int i=b; i<=a; i++) {
						answer+=i;
					}
				}
			}
		//반환
		return answer;
	}
	//테스트
	public static void main(String[] args) {
		System.out.println(solution(3,5));
	}

}
