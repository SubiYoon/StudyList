package Algorithm.ProgrammersLv1.Day06;
//x부터 x간격으로 더한 n개의 데이터 배열
import java.util.Arrays;

public class Ex03 {
	
	public static long[] solution(int x, int n) {
		//계속 더해줄 x의 초기값 설정
		long num=x;
		//n개의 배열을 생성
		long[] answer = new long[n];
		//제한 조건
		if(-10000000<=x && x<=10000000 && 1<=n && n<=1000) {
			//x씩 더해줄 변수 long형으로 생성
			long num2=x;
			for(int i=0; i<n; i++) {
				answer[i] = num2;
				num2+=num;
			
			}
			// 결과값 반환
			return answer;
			
		//제한조건 벗어날 시
		}else return null;
	}
	
	//테스트
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(2, 5)));
	}

}
