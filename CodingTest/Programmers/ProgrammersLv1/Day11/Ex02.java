package Algorithm.ProgrammersLv1.Day11;

import java.util.Arrays;

//최대공약수 최소공배수
public class Ex02 {
	//유클리드 호제법
	public static int gcd(int a, int b) {
		if(a%b==0) {
			return b;
		}else {
			return gcd(b, a%b);
		}
	}
	public static long[] solution(int n, int m) {
		long[] answer = new long[2];
		
		//서로소일때 반환
		answer[1]=(long)n*m;
		
		//삼항연산자
		answer[0] = m >= n ? Ex02.gcd(m, n) : Ex02.gcd(n,m);
		
		//최소공배수 계산
		if(n>m) {
			for(long i=1; ; i++) {
				if((i*m)%n == 0) {
					answer[1]=i*m;
					return answer;
				}
            }
		
		//최소공배수계산
        }else if(m>=n){
			for(long i=1; ; i++) {
				if((i*n)%m == 0) {
					answer[1]=i*n;
					return answer;
				}
            }
        }
        return answer;
    }
	
	//테스트
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(1000000, 999999)));
	}

}
