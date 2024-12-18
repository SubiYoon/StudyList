package Algorithm.ProgrammersLv1.Day09;
public class Ex03 {
	public static int solution(int[] a, int[] b) {
		 int answer = 0;
		 
		 //연산
		 for(int i=0; i<a.length; i++) {
			 answer += a[i]*b[i];
		 }
		 
		 return answer;
	 }
	
	//테스트
	public static void main(String[] args) {
		int[] arr1 = {1,2,3,4};
		int[] arr2 = {-3, -1, 0, 2};
		System.out.println(solution(arr1, arr2));

	}

}
