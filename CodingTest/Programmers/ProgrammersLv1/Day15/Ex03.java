package Algorithm.ProgrammersLv1.Day15;

//소수찾기
public class Ex03 {
	public static int solution(int n) {
		int answer = 0;
		
		//총 n개의 배열 생성
		int[] arr = new int[n];
        
		//배열에 숫자 삽입
		for(int i=0; i<n; i++) {
			arr[i] = i+1;
		}
        
		//숫자 1 0처리
		arr[0]=0;
		
		//숫자 0의 갯수를 셀 count
		int count = 0;
		
		//인덱스 기준 초기값을 제외한 나머지 배수번째 인덱스값 0으로 치환
		for(int k=0; k<n; k++) {
			for(int i=(1+k)+(2+k); i<n; i+=(2+k)) {
				arr[i]=0;
			}

		}
		
		//0으로 치환한 값들의 갯수를 count에 삽입
		for(int i=0; i<arr.length; i++) {
			if(arr[i]==0) {
				count++;
			}
		}
		
		//배열 총 갯수 - 0의 갯수
		answer=arr.length-count;
		
		return answer;
	}
	
	//테스트
	public static void main(String[] args) {
		System.out.println(solution(10));
		System.out.println(solution(5));
		System.out.println(solution(1000));

	}

}
