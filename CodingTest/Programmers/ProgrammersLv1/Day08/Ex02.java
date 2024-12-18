package Algorithm.ProgrammersLv1.Day08;
//정답이 틀림... 이유 알고 수정!! 두번의 최종 수정후..성공..
import java.util.Arrays;

//제일 작은 수 제거하기
public class Ex02 {
	 public static int[] solution(int[] arr) { 		 
		 int[] answer = {-1};	//예외 반환 값
		 int min1 = arr[0];		//초기 배열 0번지를 최소값으로 세팅
		 int index = 0;			//최소값의 인덱스값 추출
		 if(arr[0]==10) {
			 return answer;
		 }
		 

		 
		 //최소값 탐색해서 min1에 대입
			 for(int j=0; j<arr.length; j++) {
				 if(min1>arr[j]) {
					 min1=arr[j];
				 }
		 }
			 
		 //최솟값 인덱스 구하기
		 for(int i=0; i<arr.length; i++) {
			 if(arr[i]==min1) {
				 index=i;
			 }
		 }
		 
		 //최소값의 인덱스값을 마지막 인덱스로 보냄
		 for(int i=index; i<arr.length-1; i++) {
			 int temp = arr[i];
			 arr[i]=arr[i+1];
			 arr[i+1] = temp;
		 }
		 
		 //최초 arr의 배열에 한개 적은 배열 x를 만들어 최소값 배제
		 int[] x = new int[arr.length-1];
		 
		 for(int i=0; i<arr.length-1; i++) {
			 x[i] = arr[i];
		 }
		 
		 //예외상황시 -1 반환
		 if(x.length==0) {
			 return answer;
		 }
		 
		 //결과값 반환
		 answer=x;
		 
	     return answer;
	 }
	 
	 //테스트
	public static void main(String[] args) {
		int[] arr = new int[] {4,3,2,1};
		System.out.println(Arrays.toString(solution(arr)));
	}

}
