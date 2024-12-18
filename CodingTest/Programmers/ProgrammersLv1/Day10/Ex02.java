package Algorithm.ProgrammersLv1.Day10;

public class Ex02 {
	
	public static int solution(int left, int right) {
		int answer = 0;
		
		//l~r 반복
		for(int i=left; i<=right; i++) {
			//약수 갯수 카운트
			int count = 0;			
			//각수의 약수 갯수가 짝수인지 홀수인지 판단
			for(int j=1; j<=i; j++) {
				//약수가 생기면 count 증가
				if(i%j == 0) {
					count++;
				}
			}
			//약수 갯수가 짝수면 더하기
			if(count%2==0) {
				answer+=i;
			//약수 갯수가 홀수면 빼기
			}else {
				answer-=i;
			}
		}
	    return answer;
	}
	
	//테스트
	public static void main(String[] args) {
		System.out.println(solution(13,17));

	}

}
